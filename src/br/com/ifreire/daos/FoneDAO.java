package br.com.ifreire.daos;

import java.util.ArrayList;
import java.util.List;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import br.com.caelum.vraptor.ioc.Component;
import br.com.ifreire.models.contato.Fone;

@Component
//@SuppressWarnings({/*"unchecked",*/ "serial"})
@SuppressWarnings("serial")
public class FoneDAO extends DAO
{
	public FoneDAO()
	{
		super();
	}
	
	public void storeFone(Fone fone)
	{
		store(fone);
	}
	
	public boolean existsFone(String idFone)
	{
		return loadByID(idFone) != null;
	}
	
	public List<Fone> list()
	{
		List<Fone> list;
		
		try
		{
			list = new ArrayList<Fone>(container.query(Fone.class));
		}
		catch(Exception e)
		{
			throw e;
		}
		
		return list;
	}
	
	public void update(Fone fone)
	{
		Fone f = loadByID(fone.getId());
		f = fone;
		store(f);
	}

	public void remove(String idFone)
	{
		delete(loadByID(idFone));
	}
	
	public Fone loadByID(final String id)
	{
		ObjectSet<Fone> listFonesFromDB;
		Fone foneFromDB = null;
		
		try
		{
			listFonesFromDB = container.
							  query(new Predicate<Fone>()
						 		   {
							 	       @Override
									   public boolean match(Fone f)
									   {
										   return f.getId().equals(id);
									   }
								   }
					          );
			
			if (listFonesFromDB.size() > 0)
				foneFromDB = listFonesFromDB.get(0);
		}
		catch (Exception e)
		{
			throw e;
		}
		
		return foneFromDB;
	}

	public List<Fone> loadByAgenda(final String idAgenda)
	{
		ObjectSet<Fone> listFonesFromDB;
		List<Fone> fones = new ArrayList<Fone>();
		
		try
		{
			listFonesFromDB = container.
							  query(new Predicate<Fone>()
						 		   {
							 	       @Override
									   public boolean match(Fone f)
									   {
										   return f.getIdAgenda().equals(idAgenda);
									   }
								   }
					          );
			
			fones.addAll(listFonesFromDB);
		}
		catch (Exception e)
		{
			throw e;
		}
		
		return fones;
	}
}