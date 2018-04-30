package br.com.ifreire.daos;

import java.util.List;

import br.com.ifreire.models.Agenda;

public interface IFacAgenda
{
	void store(Agenda agenda);
	void update(Agenda agenda);
	void remove(String idAgenda);
	void remove(Agenda agenda);
	Agenda loadById(String id);
	List<Agenda> list();
	boolean existContato(String email);
	List<Agenda> searchContatoByJSON(String q);
}