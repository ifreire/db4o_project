				<form id="formContact" action="<c:url value="/sendMail"/>" method="POST">
					<fieldset>
						<legend>Contato</legend>
						
						<label for="nome">Nome:</label>
						<input id="nome" type="text" name="mensagem.nome" value="${mensagem.nome}"/>

						<label for="email">EMail:</label>
						<input id="emailFrom" type="text" name="mensagem.emailFrom" value="${mensagem.emailFrom}"/>

						<label for="subject">Assunto:</label>
						<input id="subject" type="text" name="mensagem.subject" value="${mensagem.subject}" />

						<label for="msg">Mensagem:</label>
						<textarea id="msg" name="mensagem.msg" rows="5" cols="22">${mensagem.msg}</textarea>
						
						<button type="submit">Enviar</button>
					</fieldset>
				</form>