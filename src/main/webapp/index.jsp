<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.entidade.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>CRUD</title>

<link rel="stylesheet" href="style.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
</head>
<body onload="verifica()">
		
	
	<%
		String msgErro = (String) request.getAttribute("msgErro");
	
		String msgCadastrado = (String) request.getAttribute("msgCadastrado");
		
		String msgDeletado = (String) request.getAttribute("msgDeletado");
		
		String msgEditado = (String) request.getAttribute("msgEditado");
	
		ArrayList<Pessoa> listaPessoas = (ArrayList<Pessoa>) request.getSession().getAttribute("lista");
		
		Pessoa pc = (Pessoa) request.getAttribute("pessoaCadastro");
		
		Pessoa pe = (Pessoa) request.getAttribute("pessoaEdicao");
		
		if(listaPessoas == null){
	%>
			<div class="container p-5">
				<button type="button" class="btn btn-secondary mb-3" onclick="$('#btLocalizar').click()">Iniciar Programa</button>
				<div class="form-text">Clique no botão para entrar.</div>
			</div>
			
			<form hidden="">
					<input type="submit" id="btLocalizar"
						formaction="SvPessoaLocalizar" formmethod="post"/>
                    <input type="text" class="form-control" name="nomeProcura" placeholder="Procure pelo nome">
             </form>
	<%
		} else {
	%>	

	<div class="principal">
	
		<input type="text" hidden id="pc" value="<%= pc == null ? "naoC" : "simC" %>"/>
		
		<input type="text" hidden id="pe" value="<%= pe == null ? "naoE" : "simE" %>"/>
		
		<form hidden="">
			<input type="submit" id="reiniciaVariaveis" formaction="SvPessoaReiniciar" formmethod="post"/>
			<input type="text" id="reinicia" value="<%= pc != null || pe != null ? "sim" : "nao" %>"/>
		</form>

		<div class="row mb-3 header">
			<div class="col-md-6">
				<button type="button" id="abrirModalCadastro" class="btn btn-primary mb-3"
					data-bs-toggle="modal" data-bs-target="#modalCadastro">Cadastrar
					pessoa</button>
			</div>

			<div class="w-50 input-group col-md-6" id="inputProcura">
				<form style="display: inherit;">
					<input type="submit" id="btLocalizar" hidden
						formaction="SvPessoaLocalizar" formmethod="post"/>
                    <input type="text" class="form-control" name="nomeProcura" placeholder="Faça uma pesquisa">
                    <button class="btn btn-outline-secondary" type="button" onclick="$('#btLocalizar').click()">
                        <i class="bi bi-search"></i>
                    </button>
                </form>
                <div class="form-text">Pode procurar pelo nome ou por uma letra do nome</div>
			</div>
		</div>

		<div class="alert alert-success" style="display: <%= msgDeletado == null ? "none" : "block" %>" id="msgExcluido" role="alert">
			Pessoa excluída com sucesso!
		</div>

		<div class="alert alert-success" style="display: <%= msgCadastrado == null ? "none" : "block" %>" id="msgCadastrado" role="alert">
			Pessoa cadastrada com sucesso!
		</div>
		
		<div class="alert alert-success" style="display: <%= msgEditado == null ? "none" : "block" %>" id="msgEditado" role="alert">
			Pessoa editada com sucesso!
		</div>

		<div class="modal fade" id="modalCadastro" data-bs-backdrop="static"
			data-bs-keyboard="false" tabindex="-1"
			aria-labelledby="modalCadastroLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="modalCadastroLabel">Formulário de Cadastro</h5>
						
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					
					<div class="modal-body">
						<form id="formularioCadastro">
							<div class="mb-3">
								<label for="nome" class="form-label">Nome</label> 
								<input type="text" id="nomeCadastro" name="nome" class="form-control"
									maxlength="20" required value = '<%= pc == null?"": pc.getNome() %>'>
								<div id="erroNomeCadastro" class="form-text text-danger">Digite no
									mínimo 4 letras, no máximo 20 e sem acentos.</div>
							</div>

							<div class="mb-3">
								<label for="cpf" class="form-label">CPF</label> <input
									type="text" id="cpfCadastro" name="cpf" class="form-control"
									maxlength="14" required value = '<%= pc == null?"": pc.getCpf() %>'>
								<div id="erroCpfCadastro" class="form-text text-danger">Digite o
									cpf corretamente.</div>
							</div>

							<div class="row mb-3">
								<div class="col-md-6 mb-3">
									<label for="email" class="form-label">E-mail</label> <input
										type="email" id="emailCadastro" name="email"
										class="form-control" required value = '<%= pc == null?"": pc.getEmail() %>'>
									<div id="erroEmailCadastro" class="form-text text-danger">Digite
										o email corretamente.</div>
								</div>

								<div class="col-md-6">
									<label for="emailConfirm" class="form-label">Digite seu
										e-mail novamente</label> <input type="email" id="emailConfirmCadastro"
										class="form-control" required>
									<div id="erroEmailConfirmCadastro" class="form-text text-danger">Digite
										a confirmação do email corretamente</div>
								</div>
							</div>

							<div class="alert alert-danger" style="display: <%= msgErro == null ? "none" : "block" %>"
							 id="msgErro" role="alert">
								<%= msgErro == null ? "" : msgErro %>
							</div>

							<input type="submit" value="Cadastrar"
								formaction="SvPessoaCadastrar" formmethod="post"
								class="btn btn-success" />
						</form>
					</div>
				</div>
			</div>
		</div>

		<button type="button" hidden="" id="abrirModalEditar"
			data-bs-toggle="modal" data-bs-target="#modalEditar">Editar
			pessoa</button>

		<div class="modal fade" id="modalEditar" data-bs-backdrop="static"
			data-bs-keyboard="false" tabindex="-1"
			aria-labelledby="modalEditarLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="modalEditarLabel">Editar Dados</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form id="formularioEditar">

							<input type="text" value = '<%= pe == null?"": pe.getId() %>'
							 hidden id="id" name="id"  class="form-control">

							<div class="mb-3">
								<label for="nome" class="form-label">Nome</label> 
								<input type="text" id="nomeEdicao" name="nome" class="form-control"
									maxlength="20" required value = '<%= pe == null?"": pe.getNome() %>'/>
								<div id="erroNomeEdicao" class="form-text text-danger">Digite no
									mínimo 4 letras, no máximo 20 e sem acentos.</div>
							</div>

							<div class="mb-3">
								<label for="cpf" class="form-label">CPF</label> <input
									type="text" id="cpfEdicao" name="cpf" class="form-control"
									maxlength="14" required value = '<%= pe == null?"": pe.getCpf() %>'>
								<div id="erroCpfEdicao" class="form-text text-danger">Digite o
									cpf corretamente.</div>
							</div>

							<div class="row mb-3">
								<div class="col-md-6 mb-3">
									<label for="email" class="form-label">E-mail</label> <input
										type="email" id="emailEdicao" name="email"
										class="form-control" required value = '<%= pe == null?"": pe.getEmail() %>'>
									<div id="erroEmailEdicao" class="form-text text-danger">Digite
										o email corretamente.</div>
								</div>

								<div class="col-md-6">
									<label for="emailConfirm" class="form-label">Digite seu
										e-mail novamente</label>
										 <input type="email" id="emailConfirmEdicao"
										class="form-control" required>
									<div id="erroEmailConfirmEdicao" class="form-text text-danger">Digite
										a confirmação do email corretamente</div>
								</div>
							</div>


							<div class="alert alert-danger" style="display: <%= msgErro == null ? "none" : "block" %>"
							 id="msgErro" role="alert">
								<%= msgErro == null ? "" : msgErro %>
							</div>

							<input type="submit" value="Editar" formaction="SvPessoaEditar"
								formmethod="post" class="btn btn-success" />
						</form>
					</div>
				</div>
			</div>
		</div>

		<button type="button" hidden id="abrirModalExcluir"
			data-bs-toggle="modal" data-bs-target="#modalExcluir">Excluir
			pessoa</button>

		<div class="modal fade" id="modalExcluir" data-bs-backdrop="static"
			data-bs-keyboard="false" tabindex="-1"
			aria-labelledby="modalExcluirLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="modalExcluirLabel">Deseja
							realmente excluir?</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form id="formularioExcluir">

							<input type="text" hidden id="id" name="id">

							<button type="button" class="btn btn-danger"
								data-bs-dismiss="modal">Não</button>
							<input type="submit" value="Sim" formaction="SvPessoaDeletar"
								formmethod="post" class="btn btn-success">

						</form>
					</div>
				</div>
			</div>
		</div>


		<div>
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">Nome</th>
						<th scope="col">CPF</th>
						<th scope="col">E-mail</th>
						<th scope="col">Ações</th>
					</tr>
				</thead>
				<tbody>
				
				<%
				if(listaPessoas.isEmpty()){
				%>
					<tr>
						<td class='text-danger' colspan="4">Nenhuma pessoa encontrada nos cadastros.</td>
					</tr>	
				<%
				} else {
					
					for(Pessoa p : listaPessoas){	
				%>
						<tr id="<%=p.getId()%>">
		                   	<td data-nome="<%= p.getNome() %>"> <%= p.getNome() %> </td>
		                    <td data-cpf="<%= p.getCpf() %>"> <%= p.getCpf() %> </td>
		                    <td data-email="<%= p.getEmail() %>"> <%= p.getEmail() %> </td>
		                    <td>
		                       <button type='button' onclick='pegarIdEditar(<%= p.getId() %>)' class='btn btn-primary'>Editar</button>
		                       <button type='button' onclick='pegarIdExcluir(<%= p.getId() %>)' class='btn btn-danger'>Excluir</button>
		                    </td>
	                	</tr>	
				<%
					}
				}
				%>
					
				</tbody>
			</table>
		</div>
	</div>
	
	<%} %>


<script src="main.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"
integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ"
	crossorigin="anonymous"></script>
</body>
</html>