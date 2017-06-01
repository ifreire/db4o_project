package br.com.ifreire.controllers;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.ArrayList;
import java.util.List;

//import sun.org.mozilla.javascript.internal.json.JsonParser;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.ifreire.daos.AgendaDAO;
import br.com.ifreire.daos.FoneDAO;
import br.com.ifreire.models.Agenda;
import br.com.ifreire.models.contato.Contato;
import br.com.ifreire.models.contato.Endereco;
import br.com.ifreire.models.contato.Fone;

@Resource
@SuppressWarnings("unused")
public class AgendaController
{
	private final AgendaDAO agendaDAO;
	private final FoneDAO foneDAO;
	private final Result result;
	private final Validator validator;
	
	public String[] estados()
	{       
        String[] estados =
        	{"--  Selecione um estado  --",
        		"AC - Acre", "AL - Alagoas", "AP - Amapá", "AM - Amazonas", "BA - Bahia", "CE - Ceará",
				"DF - Distrito Federal", "ES - Espírito Santo", "GO - Goiás", "MA - Maranhão", "MG - Minas Gerais",
				"MT - Mato Grosso", "MS - Mato Grosso do Sul", "PA - Pará", "PB - Paraíba", "PR - Paraná",
				"PE - Pernambuco", "PI - Piauí", "RJ - Rio de Janeiro", "RO - Rondonia", "RN - Rio Grande do Norte",
				"RS - Rio Grando do Sul", "RR - Roraima", "SC - Santa Catarina", "SE - Sergipe", "SP - São Paulo",
				"TO - Tocantins"};
        
        return estados;    
    }
	
	public AgendaController(Result result, Validator validator)
	{
		this.agendaDAO = new AgendaDAO();
		this.foneDAO = new FoneDAO();
		this.result = result;
		this.validator = validator;
	}
	
	@Get("/agenda/novo/contato")
	public void novo()
	{
		this.result.include("listaUf", estados());
	}
	
	@Get("/agenda/{id}")
	public void editarContato(String id)
	{
		Agenda agenda = loadById(id);
		
		List<Fone> fones = new ArrayList<Fone>();
		fones = loadFonesByAgenda(id);
		
		result.include("agenda", agenda);
		result.include("contato", agenda.getContato());
		result.include("endereco", agenda.getEndereco());
		result.include("fones", fones);
	}
	
	@Get("/agenda/form/foneForm/{id}")
	public void foneForm(String id)
	{
		Agenda agenda = loadById(id);
		
		List<Fone> fones = new ArrayList<Fone>();
		fones = loadFonesByAgenda(id);
		
		result.include("agenda", agenda);
		result.include("contato", agenda.getContato());
		result.include("fones", fones);
	}
	
	@Post("/agenda")
	public void add(Contato contato, Endereco endereco, Fone fone)
	{
		if (!existContato(contato.getEmail()))
		{
			System.out.println("Adicionando um novo contato...");
			
			Agenda ag = new Agenda(null);
			
			ag.setContato(contato);
			ag.setEndereco(endereco);
			
			store(ag);
			
			fone.setIdAgenda(ag.getId());
			
			storeFone(fone);
			
			result.redirectTo(this).list();
		}
		else
		{
			validator.add(new ValidationMessage("Email de contato já cadastrado: ", "agenda.contato.email"));
		}
	}
	
	@Post("/agenda/addFone/{agenda.id}")
	public void addFone(Agenda agenda, Contato contato, Fone fone)
	{
		fone.setIdAgenda(agenda.getId());
		storeFone(fone);
		result.redirectTo(this).foneForm(agenda.getId());
	}
	
	@Put("/agenda/{agenda.id}")
	public void alter(Agenda agenda, Contato contato, Endereco endereco, List<Fone> fones)
	{
		Agenda ag = loadById(agenda.getId());
		boolean isDiferente = false;
		
		ag.setContato(contato);
		ag.setEndereco(endereco);
		update(ag);
		
		for (Fone fone : fones)
		{
			fone.setIdAgenda(ag.getId());
			
			Fone foneFromDB = loadFone(fone.getId());
			
			if (foneFromDB.getTipoFone() != fone.getTipoFone())
			{
				isDiferente = true;
				foneFromDB.setTipoFone(fone.getTipoFone());
			}
			
			if (foneFromDB.getDdd() != fone.getDdd())
			{
				isDiferente = true;
				foneFromDB.setDdd(fone.getDdd());
			}
			
			if(foneFromDB.getNumeroFone() != fone.getNumeroFone())
			{
				isDiferente = true;
				foneFromDB.setNumeroFone(fone.getNumeroFone());
			}
			
			if (isDiferente)
				updateFone(foneFromDB);
		}
		
		result.redirectTo(this).list();
	}
	
	//@Delete("/agenda/delete/{id}")
	@Get("/agenda/delete/{id}")
	public void delete(String id)
	{
		remove(loadById(id));
		result.redirectTo(this).list();
	}
	
	//@Delete("/agenda/deleteSelected")
	@Get("/agenda/deleteSelected")
	public void deleteSelected(List<String> idsAgenda)
	{
		for(String id : idsAgenda)
		{
			remove(loadById(id));
		}
		
		result.redirectTo(this).list();
	}
	
	//@Delete("/agenda/deleteFone/{id}")
	@Get("/agenda/deleteFone/{id}")
	public void deleteFone(String id)
	{
		Fone fone = loadFone(id);
		removeFone(fone);
		result.redirectTo(this).foneForm(fone.getIdAgenda());
	}
	
	@Get("/agenda")
	public List<Agenda> list()
	{
		return agendaDAO.list();
	}
	
	@Get("/agenda/search/search")
	public List<Agenda> search(String nome)
	{
		result.include("nome", nome);
		return searchContatoByJSON(nome);
	}
	
	@Get("/agenda/json/search.json")
	public void searchJson(String q)
	{
		List<Contato> listaContatos = new ArrayList<Contato>();
		
		for(Agenda agenda : searchContatoByJSON(q))
			listaContatos.add(agenda.getContato());
		
		result.
		use(json()).
		withoutRoot().
		from(listaContatos).
		exclude("dtNascimento", "descricao", "foto").
		serialize();
	}
	
	private boolean existContato(String email)
	{
		return agendaDAO.existContato(email);
	}
	
	private List<Agenda> searchContatoByJSON(String q)
	{
		return agendaDAO.search(q);
	}
	
	private void store(Agenda agenda)
	{
		agendaDAO.store(agenda);
	}
	
	private boolean existsFone(String idFone)
	{
		return foneDAO.existsFone(idFone);
	}
	
	private void storeFone(Fone fone)
	{
		foneDAO.store(fone);
	}
	
	private void update(Agenda agenda)
	{
		agendaDAO.update(agenda);
	}
	
	private void updateFone(Fone fone)
	{
		foneDAO.update(fone);
	}
	
	private void remove(String idAgenda)
	{
		agendaDAO.delete(idAgenda);
	}
	
	private void removeFone(Fone fone)
	{
		foneDAO.delete(fone);
	}
	
	private void remove(Agenda agenda)
	{
		agendaDAO.delete(agenda);
	}
	
	private Fone loadFone(String id)
	{
		return foneDAO.loadFone(id);
	}
	
	private List<Fone> loadFonesByAgenda(String idAgenda)
	{
		return foneDAO.loadFonesByAgenda(idAgenda);
	}
	
	private Agenda loadById(String id)
	{
		return agendaDAO.loadById(id);
	}
}