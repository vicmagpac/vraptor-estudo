<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de produtos</title>
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css">
</head>
<body>
	<div  class="container">
	<h1>Listagem de Produtos do ${usuarioLogado.usuario.nome}</h1>
		<a href="<c:url value='/produto/formulario' />">Adicionar mais produtos</a> | 
		<a target="_blank" href="<c:url value='/produto/listaXML' />">Lista em XML</a> | 
		<a target="_blank" href="<c:url value='/produto/listaJSON' />">Lista em JSON</a> | 
		<table class="table table-stripped table-hover table-bordered">
			<thead>
				<tr>
					<th>Nome</th>
					<th>Valor</th>
					<th>Quantidade</th>
					<th>Ações</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${produtoList}" var="produto">
					<tr>
						<td>${produto.nome}</td>
						<td>${produto.valor}</td>
						<td>${produto.quantidade}</td>
						<td>
							<a href="<c:url value='/produto/remove?produto.id=${produto.id}'/>">Remover</a> | 
							<a href="<c:url value='/produto/enviaPedidoDeNovosItens?produto.nome=${produto.nome}' />">Pedir mais itens!</a>
						</td>
					</tr>
				</c:forEach>		
			</tbody>
		</table>
		<c:if test="${not empty mensagem}">
			<div class="alert alert-success">${mensagem}</div>
		</c:if>
	</div>
</body>
</html>