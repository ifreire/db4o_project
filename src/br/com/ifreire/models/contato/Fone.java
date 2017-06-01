package br.com.ifreire.models.contato;

import br.com.ifreire.models.IDHolder;

public class Fone extends IDHolder
{
	private String idAgenda;
	private String tipoFone;
	private int ddd;
	private String numeroFone;
	
	public Fone(String idFone, String tipoFone, int ddd, String numeroFone)
	{
		super(idFone);
		this.tipoFone = tipoFone;
		this.ddd = ddd;
		this.numeroFone = numeroFone;
	}
	
	public String getIdAgenda()
	{
		return idAgenda;
	}
	
	public void setIdAgenda(String idAgenda)
	{
		this.idAgenda = idAgenda;
	}
	
	public String getTipoFone()
	{
		return tipoFone;
	}
	
	public void setTipoFone(String tipoFone)
	{
		this.tipoFone = tipoFone;
	}
	
	public int getDdd()
	{
		return ddd;
	}
	
	public void setDdd(int ddd)
	{
		this.ddd = ddd;
	}
	
	public String getNumeroFone()
	{
		return numeroFone;
	}
	
	public void setNumeroFone(String numeroFone)
	{
		this.numeroFone = numeroFone;
	}
}