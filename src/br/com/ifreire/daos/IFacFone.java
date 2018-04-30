package br.com.ifreire.daos;

import java.util.List;

import br.com.ifreire.models.contato.Fone;

public interface IFacFone
{
	void store(Fone fone);
	void update(Fone fone);
	void remove(Fone fone);
	Fone loadById(String id);
	List<Fone> loadByAgenda(String idAgenda);
	boolean existsFone(String idFone);
}