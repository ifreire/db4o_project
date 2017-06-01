package br.com.ifreire.controllers;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class IndexController
{
	private final Result result;

	public IndexController(Result result)
	{
		this.result = result;
	}

	@Path("/")
	public void index()
	{
		result.include("variable", "VRaptor!");
		//result.redirectTo(AgendaController.class).list();
	}
}