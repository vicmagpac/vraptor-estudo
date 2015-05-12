<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css">
</head>
<body>
	<div class="container">
		<h1>Adicionar Produto</h1>
		<form action="<c:url value='/produto/adiciona'/>" method="post">
	          Nome: <input class="form-control"  type="text" name="produto.nome" />
	          Valor: <input class="form-control"  type="text" name="produto.valor"/>
	          Quantidade: <input class="form-control"  type="text" name="produto.quantidade" />
	         <input type="submit" value="Adicionar" />
	      </form>
	      <c:if test="${not empty errors}">
	      	 <br />
	          <div class="alert alert-danger">
	     		  <c:forEach items="${errors}" var="error">
	     		      ${error.category} - ${error.message}<br />
	     		  </c:forEach>     	
	          </div>
	      </c:if>
      </div>
</body>
</html>