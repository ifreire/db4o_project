				<form id="ContatoForm" action="<c:url value="/agenda"/>" method="POST">
					<fieldset>
						
						<legend>Novo Contato</legend>
						
						<table>
							<tr>
								<td colspan="3"></td>
							</tr>
							
							<%@ include file="_contatoForm.jsp" %>
								
							<tr>
								<td>
									<label for="fone_tipoFone">Tipo Telefone</label>
								</td>
								
								<td>
									<label for="fone_ddd">DDD</label>
								</td>
								
								<td>
									<label for="fone_numeroFone">Número do Telefone</label>
								</td>
							</tr>
								
							<%@ include file="_foneForm.jsp" %>
							
							<tr></tr>
							
							<tr>
								<td>
									<button type="reset">Limpar</button>
								</td>
								
								<td>
									<button type="submit">Salvar</button>
								</td>
							</tr>
						
						</table>
						
					</fieldset>
				</form>

				<script type="text/javascript">
					$('#ContatoForm').validate();
				</script>
				