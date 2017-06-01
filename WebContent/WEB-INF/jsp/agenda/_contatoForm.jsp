
							<tr>
								<td colspan="3">
									<label for="contato_nome">Nome</label>
								</td>
							</tr>
							
							<tr>
								<td colspan="3">
									<input id="contato_nome" type="text" class="required" name="contato.nome" value="${contato.nome}" style="width: 463px" />
								</td>
							</tr>
							
							<tr>
								<td>
									<label for="contato_dtNascimento">Data de Nascimento</label>
								</td>
								
								<td>
									<label for="contato_email">EMail</label>
								</td>
								
								<td>
									<label for="contato_foto">Foto</label>
								</td>
							</tr>
							
							<tr>
								<td>
									<input id="contato_dtNascimento" type="text" class="required, dateBR" name="contato.dtNascimento" value="${contato.dtNascimento}"/>
								</td>
								
								<td>
									<input id="contato_email" type="text" class="required" name="contato.email" value="${contato.email}"/>
								</td>
								
								<td>
									<input id="contato_foto" type="text" name="contato.foto" value="${contato.foto}" />
								</td>
							</tr>
							
							<tr>
								<td colspan="3">
									<label for="contato_descricao">Descricao</label>
								</td>
							</tr>
							
							<tr>
								<td colspan="3">
									<textarea id="contato_descricao" name="contato.descricao">${contato.descricao}</textarea>
								</td>
							</tr>
							
							<tr>
								<td>
									<label for="endereco_tipoLog">Tipo Logradouro</label>
								</td>
								
								<td>
									<label for="endereco_logradouro">Logradouro</label>
								</td>
								
								<td>
									<label for="endereco_numeroEndereco">Número</label>
								</td>
							</tr>
							
							<tr>
								<td>
									<input id="endereco_tipoLog" type="text" class="required" name="endereco.tipoLog" value="${endereco.tipoLog}" />
								</td>
								
								<td>
									<input id="endereco_logradouro" type="text" class="required" name="endereco.logradouro" value="${endereco.logradouro}" />
								</td>
								
								<td>
									<input id="endereco_numeroEndereco" type="text" class="required" name="endereco.numeroEndereco" value="${endereco.numeroEndereco}" />
								</td>
							</tr>
							
							<tr>
								<td>
									<label for="endereco_complemento">Complemento</label>
								</td>
								
								<td>
									<label for="endereco_bairro">Bairro</label>
								</td>
								
								<td>
									<label for="endereco_cidade">Cidade</label>
								</td>
							</tr>
							
							<tr>
								<td>
									<input id="endereco_complemento" type="text" name="endereco.complemento" value="${endereco.complemento}" />
								</td>
								
								<td>
									<input id="endereco_bairro" type="text" class="required" name="endereco.bairro" value="${endereco.bairro}" />
								</td>
								
								<td>
									<input id="endereco_cidade" type="text" class="required" name="endereco.cidade" value="${endereco.cidade}" />
								</td>
							</tr>
							
							<tr>
								<td>
									<label for="endereco_uf">UF</label>
								</td>
								
								<td>
									<label for="endereco_cep">CEP</label>
								</td>
							</tr>
							
							<tr>
								<td>
									<%--
									<c:forEach items="${listaUf}" var="uf">    
									    <option value="<c:out value="${uf}" />">${endereco.uf}</option>    
									</c:forEach>
									--%>
									
									<input id="endereco_uf" type="text" class="required" name="endereco.uf" value="${endereco.uf}" />
									
								</td>
								
								<td>
									<input id="endereco_cep" type="text" class="required" name="endereco.cep" value="${endereco.cep}" />
								</td>
							</tr>
							
							