package br.com.ifreire.models;

import java.util.UUID;

public abstract class IDHolder
{
	private String id;
	
	public IDHolder(String id)
	{
		if (id != null)
			this.id = id;
		else
			this.id = UUID.randomUUID().toString();
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		if(id == null)
			this.id = UUID.randomUUID().toString();
		else
			this.id = id;
	}
}