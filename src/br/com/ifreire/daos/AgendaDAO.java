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
		int tamanho;

		try
		{
			agendaFromBD = super.container.query(Agenda.class);
			list = new ArrayList<Agenda>();
			tamanho = agendaFromBD.size();
			
			for (int x = 0; x < tamanho; x++)
			{
				list.add((Agenda) agendaFromBD.get(x));
			}
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
			
			for (Agenda ag : agendaFromBD)
			{
				list.add(ag);
			}
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
		Agenda agenda = loadById(idAgenda);
		
		delete(agenda);
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
		}
		catch (Exception e)
		{
			throw e;
		}
		
		for (Agenda agenda : listFromDB)
		{
			agFromBD = agenda;
		}
		
		return agFromBD;
	}
}