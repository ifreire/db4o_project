package br.com.ifreire.daos;

import org.apache.commons.mail.*;

import br.com.caelum.vraptor.ioc.Component;
import br.com.ifreire.models.Mensagem;

@Component
public class ContactDAO extends DAO
{
	public boolean sendMail(Mensagem mensagem)
	{
		boolean enviou = false;
		SimpleEmail email = new SimpleEmail();
		
		String hostName = "smtps.bol.com.br";
		String nameTo = "Iuri Freire";
		String mailTo = "iuricostafreire@gmail.com";
		String mailFrom = "freire-iuri@bol.com.br";
		String loginAutentication = "freire-iuri@bol.com.br";
		String passAutentication = "Wj9M0rTHz78y";
		int port = 587;
		
//		String hostName = "mail.google.com";
//		String nameTo = "Iuri Freire";
//		String mailTo = "iuricostafreire@gmail.com";
//		String mailFrom = "iuricostafreire@gmail.com";
//		String loginAutentication = "iuri.freire@gmail.com";
//		String passAutentication = "icfkdc226512";
//		int port = 465;
//		boolean ssl = true;
//		boolean tsl = ssl;
		
		try
		{
			email.setHostName(hostName);
			email.setAuthentication(loginAutentication, passAutentication);
			email.setSmtpPort(port);
			//email.setSSL(ssl);
			//email.setTLS(tsl); //remover isso se enviar email pelo google.
			
			email.addTo(mailTo, nameTo);
			email.setFrom(mailFrom, (mensagem.getNome() + " - " + mensagem.getEmailFrom()));
			email.setSubject(mensagem.getSubject());
			email.setMsg(mensagem.getMsg());
			email.send();
			enviou = true;
		}
		catch (EmailException e)
		{
			enviou = false;
			e.printStackTrace();
		}

		return enviou;
	}
}