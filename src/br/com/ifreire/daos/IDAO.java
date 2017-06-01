package br.com.ifreire.daos;

public interface IDAO
{
	void openContainer();
	void closeContainer();
	void store(Object obj);
	void delete(Object obj);
}