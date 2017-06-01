package br.com.ifreire.models;

public class Mensagem
{
	private String nome;
	private String emailFrom;
	private String subject;
	private String msg;
	
	public String getNome()
	{
		return nome;
	}
	
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	
	public String getEmailFrom()
	{
		return emailFrom;
	}
	
	public void setEmailFrom(String emailFrom)
	{
		this.emailFrom = emailFrom;
	}
	
	public String getSubject()
	{
		return subject;
	}
	
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	
	public String getMsg()
	{
		return msg;
	}
	
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
}