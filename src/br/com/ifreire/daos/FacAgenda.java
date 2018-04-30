package br.com.ifreire.daos;

import java.util.List;

import br.com.ifreire.daos.IFacAgenda;
import br.com.ifreire.models.Agenda;

public class FacAgenda implements IFacAgenda
{
	private static IFacAgenda instance;
	private static AgendaDAO agendaDAO;
	
	public static IFacAgenda Instance()
	{
		//if (instance == null)
			instance = new FacAgenda();
		
		return instance;
	}
	
	private FacAgenda()
	{
		agendaDAO = new AgendaDAO();
	}
	
	public void store(Agenda agenda)
	{
		agendaDAO.store(agenda);
	}
	
	public void update(Agenda agenda)
	{
		agendaDAO.update(agenda);
	}
	
	public void remove(String idAgenda)
	{
		agendaDAO.delete(idAgenda);
	}
	
	public void remove(Agenda agenda)
	{
		agendaDAO.delete(agenda);
	}
	
	public Agenda loadById(String id)
	{
		return agendaDAO.loadById(id);
	}
	
	public List<Agenda> list()
	{
		return agendaDAO.list();
	}
	
	public boolean existContato(String email)
	{
		return agendaDAO.existContato(email);
	}
	
	public List<Agenda> searchContatoByJSON(String q)
	{
		return agendaDAO.search(q);
	}
}