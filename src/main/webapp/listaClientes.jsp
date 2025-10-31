<%@ page language="java" contentType="text/html; charset-UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Lista de Clientes</title>
    <link rel="stylesheet" href="/css/styles.css"/>
</head>
<bod>
    <div class="container">
        <h1> Lista de Clientes </h1>

    <div class="header-actions">
        <a class="btn btn-primary" href="clientes?acao=criar"> Cadastro Cliente</a>
    </div>
    <div>
        <c:choose>
            <c:when test="${empty clientes}">
                <p> nehum cliente cadastrado, clique em "Novo cliente" para adicionar
            </c:when>

            <c:otherwise>


        <table>
            <tr>
                <th>ID</th>

                <th>NOME</th>

                <th>EMAIL</th>

                <th>TELEFONE</th>

                <th>CPF</th>

                <th>ENDEREÇO</th>

                <th>Acoes</th>
            </tr>
            <tbody>
            <c:forEach var = "cliente" items="${clientes}">
                <tr>
                <td>${cliente.id}</td>
                <td>${cliente.nome}</td>
                <td>${cliente.email}</td>
                <td>${cliente.telefone}</td>
                <td>${cliente.cpf}</td>
                <td>${cliente.endereco}</td>

                <td class="actions">
                <a class="btn btn-edit" href="/clientes?acao=editar&id=${cliente.id}">EDITAR</a>

                <a class="btn btn-delete" href="/clientes?acao=deletar&id=${cliente.id}"
                onclick="return confirm('Tem certeza que deseja excluir esse cliente?')"
                >DELETE</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </c:otherwise>
        </c:choose>
    </div>

</body>




</html>
