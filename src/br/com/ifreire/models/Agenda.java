package br.com.ifreire.models;

import java.util.List;

import br.com.ifreire.models.contato.Contato;
import br.com.ifreire.models.contato.Endereco;
import br.com.ifreire.models.contato.Fone;

public class Agenda extends IDHolder
{
	private Contato contato;
	private Endereco endereco;
	private List<Fone> foneList;
	
	public Agenda(String id)
	{
		super(id);
	}
	
	public Contato getContato()
	{
		return contato;
	}

	public void setContato(Contato contato)
	{
		this.contato = contato;
	}
	
	public Endereco getEndereco()
	{
		return endereco;
	}

	public void setEndereco(Endereco endereco)
	{
		this.endereco = endereco;
	}
	
	public List<Fone> getFoneList()
	{
		return foneList;
	}
	
	public void setFoneList(List<Fone> foneList)
	{
		this.foneList = foneList;
	}
}