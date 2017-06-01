				
				<div id="listaContato">
					<table>
						<thead>
							<tr>
								<th width="15px"></th>
								<th width="500px">Nome</th>
								<th width="100px">Detalhes</th>
							</tr>
						</thead>
						<tbody>
							<% int x = 0; %>
							<c:forEach items="${agendaList}" var="agenda">
								<tr>
									<td>
										<input type="checkbox" id="agenda_id" name="agenda[<%= x %>]" value="${agenda.id}" />
									</td>
									
									<td>
										<a href="<c:url value="/agenda/${agenda.id}"/>">${agenda.contato.nome}</a>
									</td>
									
									<td>
										<a href="<c:url value="/agenda/form/foneForm/${agenda.id}"/>">Add Fone</a>
										
										<a href="<c:url value="/agenda/delete/${agenda.id}"/>">Remover</a>
										<!--
										<form action="<c:url value="/agenda/delete/${agenda.id}"/>" method="POST">
											<button class="link" name="_method" value="DELETE">Remover</button>
										</form>
										-->
									</td>
								</tr>
								
								<% x++; %>
							</c:forEach>
						</tbody>
					</table>
				
					<div>
						<!--
						<div>
							<a href="<c:url value="/agenda/report/${agenda.id}"/>">Gerar Relatório</a>
						</div>
						
						<div>
							<a href="<c:url value="/agenda/deleteSelected/${agendaList}"/>">Remover Selecionados</a>
						</div>
						-->
						
						<!--
						<form action="<c:url value="/agenda/report/${agenda.id}"/>" method="POST">
							<button type="submit">Gerar Relatório</button>
						</form>
						-->
					</div>
				</div>