				<form id="ContatoForm" action="<c:url value="/agenda/${agenda.id }"/>" method="POST">
					<fieldset>
					
						<legend>Editar Contato</legend>
						
						<table>
							<tr>
								<td>
									<input id="agenda.id" type="hidden" name="agenda.id" value="${agenda.id}" />
								</td>
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
							
							
							<%@ include file="_foneFormList.jsp" %>
							
							<tr></tr>
							
							<tr>
								<td>
									<button type="reset">Limpar</button>
								</td>
							
								<td>
									<button type="submit" name="_method" value="PUT">Salvar</button>
								</td>
							</tr>
						
						</table>
						
					</fieldset>
				</form>

				<script type="text/javascript">
					$('#ContatoForm').validate();
				</script>
				