package br.com.ifreire.models.contato;

import br.com.ifreire.models.IDHolder;

public class Contato extends IDHolder
{
	private String nome;
	private String dtNascimento;
	private String email;
	private String descricao;
	private String foto;

	public Contato(String nome, String dtNascimento, String email, String descricao, String foto)
	{
		super("");
		this.nome = nome;
		this.dtNascimento = dtNascimento;
		this.email = email;
		this.descricao = descricao;
		this.foto = foto;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	
	public String getDtNascimento()
	{
		return dtNascimento;
	}
	
	public void setDtNascimento(String dtNascimento)
	{
		this.dtNascimento = dtNascimento;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getDescricao()
	{
		return descricao;
	}
	
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}
	
	public String getFoto()
	{
		return foto;
	}
	
	public void setFoto(String foto)
	{
		this.foto = foto;
	}
}