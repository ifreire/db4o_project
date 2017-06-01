package br.com.ifreire.models.contato;

//import br.com.ifreire.models.IDHolder;

public class Endereco //extends IDHolder
{
	private String tipoLog;
	private String logradouro;
	private int numeroEndereco;
	private String complemento;
	private String bairro;
	private String cidade;
	private String uf;
	private String cep;
	
	public Endereco(String tipoLog, String logradouro, int numeroEndereco, String complemento, String bairro, String cidade, String uf, String cep)
	{
		//supre(id);
		this.tipoLog = tipoLog;
		this.logradouro = logradouro;
		this.numeroEndereco = numeroEndereco;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.cep = cep;
	}
	
	public String getTipoLog()
	{
		return tipoLog;
	}
	
	public void setTipoLog(String tipoLog)
	{
		this.tipoLog = tipoLog;
	}
	
	public String getLogradouro()
	{
		return logradouro;
	}
	
	public void setLogradouro(String logradouro)
	{
		this.logradouro = logradouro;
	}
	
	public int getNumeroEndereco()
	{
		return numeroEndereco;
	}
	
	public void setNumeroEndereco(int numeroEndereco)
	{
		this.numeroEndereco = numeroEndereco;
	}
	
	public String getComplemento()
	{
		return complemento;
	}
	
	public void setComplemento(String complemento)
	{
		this.complemento = complemento;
	}
	
	public String getBairro()
	{
		return bairro;
	}
	
	public void setBairro(String bairro)
	{
		this.bairro = bairro;
	}
	
	public String getCidade()
	{
		return cidade;
	}
	
	public void setCidade(String cidade)
	{
		this.cidade = cidade;
	}
	
	public String getUf()
	{
		return uf;
	}
	
	public void setUf(String uf)
	{
		this.uf = uf;
	}
	
	public String getCep()
	{
		return cep;
	}
	
	public void setCep(String cep)
	{
		this.cep = cep;
	}
}