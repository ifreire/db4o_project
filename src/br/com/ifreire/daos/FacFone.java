package br.com.ifreire.daos;

import java.util.List;

import br.com.ifreire.daos.IFacFone;
import br.com.ifreire.models.contato.Fone;

public class FacFone implements IFacFone
{
	private static IFacFone instance;
	private static FoneDAO foneDAO;
	
	public static IFacFone Instance()
	{
		//if (instance == null)
			instance = new FacFone();
		
		return instance;
	}
	
	private FacFone()
	{
		foneDAO = new FoneDAO();
	}
	
	public void store(Fone fone)
	{
		foneDAO.store(fone);
	}
	
	public void update(Fone fone)
	{
		foneDAO.update(fone);
	}
	
	public void remove(Fone fone)
	{
		foneDAO.delete(fone);
	}
	
	public Fone loadById(String id)
	{
		return foneDAO.loadByID(id);
	}
	
	public List<Fone> loadByAgenda(String idAgenda)
	{
		return foneDAO.loadByAgenda(idAgenda);
	}
	
	public boolean existsFone(String idFone)
	{
		return foneDAO.existsFone(idFone);
	}
}