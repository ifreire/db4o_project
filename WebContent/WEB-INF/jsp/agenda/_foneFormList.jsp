							
							
							<% int x = 0; %>
							<c:forEach items="${fones}" var="fones">
								<tr>
									<td>
										<input id="fone_tipoFone" type="text" class="required" name="fones[<%= x %>].tipoFone" value="${fones.tipoFone}" />
									</td>
									
									<td>
										<input id="fone_ddd" type="text" class="required" name="fones[<%= x %>].ddd" value="${fones.ddd}" />
									</td>
									
									<td>
										<input id="fone_numeroFone" type="text" class="required fone_numero" name="fones[<%= x %>].numeroFone" value="${fones.numeroFone}" />
										<input id="fone_idFone" type="hidden" name="fones[<%= x %>].idFone" value="${fones.id}" />
									</td>
								</tr>
								<% x++;  %>
							</c:forEach>