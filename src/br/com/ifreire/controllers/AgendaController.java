package br.com.ifreire.controllers;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.ifreire.daos.FacAgenda;
import br.com.ifreire.daos.FacFone;
import br.com.ifreire.models.Agenda;
import br.com.ifreire.models.contato.Contato;
import br.com.ifreire.models.contato.Endereco;
import br.com.ifreire.models.contato.Fone;

@Resource
@SuppressWarnings("unused")
public class AgendaController
{
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
		Agenda agenda = FacAgenda.Instance().loadById(id);
		
		List<Fone> fones = new ArrayList<Fone>();
		fones = FacFone.Instance().loadByAgenda(id);
		
		result.include("agenda", agenda);
		result.include("contato", agenda.getContato());
		result.include("endereco", agenda.getEndereco());
		result.include("fones", fones);
	}
	
	@Get("/agenda/form/foneForm/{id}")
	public void foneForm(String id)
	{
		Agenda agenda = FacAgenda.Instance().loadById(id);
		
		List<Fone> fones = new ArrayList<Fone>();
		fones = FacFone.Instance().loadByAgenda(id);
		
		result.include("agenda", agenda);
		result.include("contato", agenda.getContato());
		result.include("fones", fones);
	}
	
	@Post("/agenda")
	public void add(Contato contato, Endereco endereco, Fone fone)
	{
		if (!FacAgenda.Instance().existContato(contato.getEmail()))
		{
			System.out.println("Adicionando um novo contato...");
			
			Agenda ag = new Agenda(null);
			
			ag.setContato(contato);
			ag.setEndereco(endereco);
			
			FacAgenda.Instance().store(ag);
			
			fone.setIdAgenda(ag.getId());
			
			FacFone.Instance().store(fone);
			
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
		FacFone.Instance().store(fone);
		result.redirectTo(this).foneForm(agenda.getId());
	}
	
	@Put("/agenda/{agenda.id}")
	public void alter(Agenda agenda, Contato contato, Endereco endereco, List<Fone> fones)
	{
		Agenda ag = FacAgenda.Instance().loadById(agenda.getId());
		boolean isDiferente = false;
		
		ag.setContato(contato);
		ag.setEndereco(endereco);
		FacAgenda.Instance().update(ag);
		
		for (Fone fone : fones)
		{
			fone.setIdAgenda(ag.getId());
			
			Fone foneFromDB = FacFone.Instance().loadById(fone.getId());
			
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
				FacFone.Instance().update(foneFromDB);
		}
		
		result.redirectTo(this).list();
	}
	
	//@Delete("/agenda/delete/{id}")
	@Get("/agenda/delete/{id}")
	public void delete(String id)
	{
		FacAgenda.Instance().remove(FacAgenda.Instance().loadById(id));
		result.redirectTo(this).list();
	}
	
	//@Delete("/agenda/deleteSelected")
	@Get("/agenda/deleteSelected")
	public void deleteSelected(List<String> idsAgenda)
	{
		for(String id : idsAgenda)
		{
			FacAgenda.Instance().remove(FacAgenda.Instance().loadById(id));
		}
		
		result.redirectTo(this).list();
	}
	
	//@Delete("/agenda/deleteFone/{id}")
	@Get("/agenda/deleteFone/{id}")
	public void deleteFone(String id)
	{
		Fone fone = FacFone.Instance().loadById(id);
		FacFone.Instance().remove(fone);
		result.redirectTo(this).foneForm(fone.getIdAgenda());
	}
	
	@Get("/agenda")
	public List<Agenda> list()
	{
		return FacAgenda.Instance().list();
	}
	
	@Get("/agenda/search/search")
	public List<Agenda> search(String nome)
	{
		result.include("nome", nome);
		return FacAgenda.Instance().searchContatoByJSON(nome);
	}
	
	@Get("/agenda/json/search.json")
	public void searchJson(String q)
	{
		List<Contato> listaContatos = new ArrayList<Contato>();
		
		for(Agenda agenda : FacAgenda.Instance().searchContatoByJSON(q))
			listaContatos.add(agenda.getContato());
		
		result.use(json()).withoutRoot().from(listaContatos).exclude("dtNascimento", "descricao", "foto").serialize();
	}
}