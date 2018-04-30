package br.com.ifreire.daos;

import java.util.ArrayList;
import java.util.List;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import br.com.caelum.vraptor.ioc.Component;
import br.com.ifreire.models.Agenda;

@Component
@SuppressWarnings("serial")
public class AgendaDAO extends DAO
{
	public AgendaDAO()
	{
		super();
	}
	
	public boolean existContato(final String email)
	{
		ObjectSet<Agenda> result = null;
		boolean exist = true;
		
		try
		{
			result = loadExistContato(email);
	        
			if (!result.hasNext() || result.isEmpty() || result.size() == 0 || result == null)
	        {
	        	exist = false;
	        }
		}
		catch (Exception e)
		{
			throw e;
		}
		
		return exist;
	}

	public List<Agenda> list()
	{
		ObjectSet<Agenda> agendaFromBD;
		List<Agenda> list;

		try
		{
			agendaFromBD = super.container.query(Agenda.class);
			list = new ArrayList<Agenda>();
			list.addAll(agendaFromBD);
		}
		catch(Exception e)
		{
			throw e;
		}
		
		return list;
	}
	
	public List<Agenda> search(final String nome)
	{
		ObjectSet<Agenda> agendaFromBD;
		List<Agenda> list;
		
		try
		{
			agendaFromBD = super.container.
						   query(new Predicate<Agenda>()
									 {
							   		     @Override
										 public boolean match(Agenda a)
										 {
											 return a.getContato().getNome().toLowerCase().contains(nome.toLowerCase());
										 }
									 }
								);
			
			list = new ArrayList<Agenda>();
			
			list.addAll(agendaFromBD);
		}
		catch(Exception e)
		{
			throw e;
		}
		
		return list;
	}
	
	private ObjectSet<Agenda> loadExistContato(final String email)
	{
		ObjectSet<Agenda> listFromDB;
		
		try
		{
			listFromDB = super.container.
						 query(new Predicate<Agenda>()
						 		   {
							 	       @Override
									   public boolean match(Agenda a)
									   {
										   return a.getContato().getEmail().equals(email);
									   }
								   }
					          );
		}
		catch (Exception e)
		{
			throw e;
		}

		return listFromDB;
	}
	
	public void update(Agenda agenda)
	{
		Agenda ag = loadById(agenda.getId());
		ag = agenda;
		store(ag);
	}
	
	public void deleteAgenda(String idAgenda)
	{
		delete(loadById(idAgenda));
	}
	
	public Agenda loadById(final String id)
	{
		ObjectSet<Agenda> listFromDB;
		Agenda agFromBD = new Agenda(null);
		
		try
		{
			listFromDB = super.container.
						 query(new Predicate<Agenda>()
						 		   {
							 	       @Override
									   public boolean match(Agenda a)
									   {
										   return a.getId().equals(id);
									   }
								   }
					          );
			
			if (listFromDB.size() > 0)
				return listFromDB.get(0);
		}
		catch (Exception e)
		{
			throw e;
		}
		
		return agFromBD;
	}
}