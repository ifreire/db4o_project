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
	public void storeFone(Fone fone)
	{
		store(fone);
	}
	
	public boolean existsFone(String idFone)
	{
		Fone fone = loadFone(idFone);
		
		if (fone == null)
			return false;
		else
			return true;
	}
	
	public List<Fone> list()
	{
		ObjectSet<Fone> fonesFromBD;
		List<Fone> list;
		int tamanho;

		try
		{
			fonesFromBD = container.query(Fone.class);
			list = new ArrayList<Fone>();
			tamanho = fonesFromBD.size();
			
			for (int x = 0; x < tamanho; x++)
			{
				list.add((Fone) fonesFromBD.get(x));
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		
		return list;
	}
	
	public void update(Fone fone)
	{
		Fone f = loadFoneByID(fone.getId());
		
		f = fone;
		
		store(f);
	}

	public void remove(String idFone)
	{
		delete(loadFoneByID(idFone));
	}
	
	public Fone loadFoneByID(final String id)
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
		}
		catch (Exception e)
		{
			throw e;
		}
		
		for (Fone fone : listFonesFromDB)
		{
			foneFromDB = fone;
		}
		
		return foneFromDB;
	}
	
	public Fone loadFone(final String id)
	{
		ObjectSet<Fone> listFonesFromDB;
		Fone f = null;
		
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
		}
		catch (Exception e)
		{
			throw e;
		}
		
		for (Fone fone : listFonesFromDB)
		{
			f = fone;
			break;
		}
		
		return f;
	}

	public List<Fone> loadFonesByAgenda(final String idAgenda)
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
		}
		catch (Exception e)
		{
			throw e;
		}
		
		for (Fone fone : listFonesFromDB)
		{
			fones.add(fone);
		}
		
		return fones;
	}
}