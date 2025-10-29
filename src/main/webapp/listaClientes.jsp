<%@ page language="java" contentType="text/html; charset-UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de Clientes</title>
</head>
<bod>
    <div>
        <h1> LIsta de Clientes </h1>

    <div>
        <a href="clientes?acao=criar"> Cadastro Cliente</a>
    </div>
    <div>
        <c:choose>
            <c:when test="${empty clientes}">
                <p> nehum cliente cadastrado, clique em "Novo cliente" para adicionar
            </c:when>

            <c:otherwise>


        <table>
            <tr>
                <td>ID</td>

                <td>NOME</td>

                <td>EMAIL</td>

                <td>TELEFONE</td>

                <td>CPF</td>

                <td>ENDEREÇO</td>

                <td>Acoes</td>
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

                <td>
                <a href"/clente?acao=editar&id=${cliente.id}">EDITAR</a>

                <td><a href="/clientes?acao=deletar&id=${cliente.id}"
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
