						<form id="formFone" action="<c:url value="/agenda/addFone/${agenda.id}"/>" method="POST">
							<fieldset>
								<legend>${contato.nome}</legend>
								
								<input type="hidden" id="id" name="agenda.id" value="${agenda.id}" />
								<input type="hidden" id="idFone" name="fone.idFone" />
								
								<label for="tipoFone">Tipo</label>
								<input type="text" id="tipoFone" name="fone.tipoFone" class="required" value="${fone.tipoFone}" />
								
								<label for="ddd">DDD</label>
								<input type="text" id="fone_ddd" name="fone.ddd" class="required" value="${fone.ddd}" />
								
								<label for="numeroFone">Telefone</label>
								<input type="text" id="fone_numeroFone" name="fone.numeroFone" class="required fone_numero" value="${fone.numeroFone}" />

								<button type="submit">Adicionar</button>
							</fieldset>
						</form>
						
						<script type="text/javascript">
							$('#formFone').validate();
						</script>
						
						
						<table>
							<thead>
							<tr>
								<th width="100px">Tipo</th>
								<th width="150px">Fone</th>
								<th width="150px">Remover</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${fones}" var="fone">
								<tr>
									<td>
										${fone.tipoFone}
									</td>
									
									<td>
										(${fone.ddd})&nbsp;${fone.numeroFone}
									</td>
									
									<td>
										<a href="<c:url value="/agenda/deleteFone/${fone.id}"/>">Remover</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
							
						</table>