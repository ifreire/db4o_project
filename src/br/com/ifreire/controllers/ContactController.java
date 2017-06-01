package br.com.ifreire.controllers;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.ifreire.daos.ContactDAO;
import br.com.ifreire.models.Mensagem;

@Resource
public class ContactController
{
	private final ContactDAO contactDAO;
	private final Result result;
	private final Validator validator;

	//  Construtor do controlador.
	//  Esse m�todo � executado pelo VRaptor.
	public ContactController(Result result, Validator validator)
	{
		this.contactDAO = new ContactDAO();
		this.result = result;
		this.validator = validator;
	}

	//  Abre a p�gina para um usu�rio cadastrado ou n�o entre em contato com os administradores do site.
	@Get("/contato")
	public void newContactForm() { }

	//  Abre a p�gina de confirma��o de envio da mensagem.
	@Get("/contato/send")
	public void mensagemEnviada() { }

	//  M�todo respons�vel por enviar a mensagem para os administradores do site.
	@Post("/sendMail")
	public void send(Mensagem mensagem)
	{
		if (contactDAO.sendMail(mensagem))
		{
			result.redirectTo(this).mensagemEnviada();
		}
		else
		{
			validator.add(new ValidationMessage("Mensagem enviada com sucesso!", null));
			validator.onErrorUsePageOf(this).newContactForm();
		}
	}
}