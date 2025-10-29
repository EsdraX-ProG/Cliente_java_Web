<%@ page language="java" contentType="text/html; charset-UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>

<!doctype html>
<html lang="pt-br"></html>
<meta charset="UTF-8">
<meta name="viewport"content="width=device-width, initial-acale=1">
<title> Criar Cliente</title>

</head>
<body>
    <div>
        <h1>Novo Cliente</h1>

        <form action="/clientes"method="post">
        <div>
            <label>Nome:</label>
                                                              <%--   if   else --%>
                                                              <%--   v      v --%>
            <input name = "nome" type="text" value="${cliente== null ? null : cliente.nome}">
        </div>

        <div>
            <label>Email:</label>
            <input name = "email" type="email" value="${cliente== null ? null : cliente.email}">
        <div>

            <label>Telefone:</label>
            <input name = "telefone" type="tel" value="${cliente== null ? null : cliente.telefone}">
        </div>

        <div>
            <label>Endereço:</label>
            <input name = "endereco" type="text" value="${cliente== null ? null : cliente.endereco}">
        </div>

        <div>
            <label>cpf:</label>
            <input name = "cpf" type="text" value="${cliente== null ? null : cliente.cpf}">
        </div>

        <button type = "submit">salvar</button>


        </form>
    </div>
</body>

