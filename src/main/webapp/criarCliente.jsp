<%@ page language="java" contentType="text/html; charset-UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>

<!doctype html>
<html lang="pt-br"></html>
<meta charset="UTF-8">
<meta name="viewport"content="width=device-width, initial-acale=1">
<title> Criar Cliente</title>
<link rel="stylesheet" href="/css/styles.css"/>
</head>
<body>

    <div class="container">

    <c:choose>

        <c:when test="${not empty cliente}">
            <h1> Editar cliente </h1>

        </c:when>

        <c:otherwise>
             <h1>Novo cliente</h1>
        </c:otherwise>
    </c:choose>

        <form action="/clientes"method="post">
        <c:choose>
                 <c:when test="${not empty cliente}">
                    <input type = "hidden" name="acao" value="atualizar">
                    <input type = "hidden" name="id" value="${cliente.id}">
                 </c:when>

               <c:otherwise>

                <input type="hidden" name="acao" value="inserir">
              </c:otherwise>
           </c:choose>

        <div>
            <label>Nome:</label>

            <input name = "nome" type="text" value="${cliente.nome}">
        </div>

        <div>
        <%-- Comentario --%>
            <label>Email:</label>
            <input name = "email" type="email" value="${cliente.email}">
        <div>

            <label>Telefone:</label>
            <input name = "telefone" type="tel" value="${cliente.telefone}">
        </div>

        <div>
            <label>Endereço:</label>
            <input name = "endereco" type="text" value="${cliente.endereco}">
        </div>

        <div>
            <label>Cpf:</label>
            <input name = "cpf" type="text" value="${cliente.cpf}">
        </div>
        <div class="form-actions">
        <button class="btn btn-primary" type = "submit">Salvar</button>
        <a class="btn btn-secondary" href="/clientes?acao=listar">Cancelar</a>
        </div>

        </form>
    </div>
</body>

