package br.com.ifreire.models;

import java.util.UUID;

public abstract class IDHolder
{
	private String id;
	private String idEntity;
	
	public IDHolder(String id)
	{
		if (id != null)
			this.id = id;
		else
			this.id = newId();
		
		this.idEntity = this.id;
	}
	
	private static String newId()
    {
    	return UUID.randomUUID().toString();
    }
	
	public String getId()
    {
    	String result = "";
        
        if (id == "")
            result = idEntity;
        else
            result = id;
        
        return result;
    }
	
	public void setId(String id)
	{
		if(id == null)
			this.id = newId();
		else
			this.id = id;
	}
	
	public String IdEntity()
    {
        return idEntity;
    }
	
	public boolean isNew()
    {
        return this.id == "";
    }
	
	@Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        
        if (!(obj instanceof IDHolder))
            return false;
        
        if (!obj.getClass().equals(this.getClass()))
            return false;
        
        if (this != obj)
            return false;
        
        if (this.id != ((IDHolder)obj).id)
            return false;
        
        return true;
    }
	
	@Override
    public int hashCode()
    {
        return (this.getClass().getName() + ":" + this.id).hashCode();
    }
	
	@Override
    public String toString()
    {
        return String.format("[ %s ] - [ Id: %s ] - [ %d ]", this.getClass().getSimpleName(), this.id, hashCode());
    }
}
