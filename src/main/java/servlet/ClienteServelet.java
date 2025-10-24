package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/clientes")
public class ClienteServelet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String acao = req.getParameter("acao");

        System.out.println("Chegou aqui");

                switch(acao){
                    case "listar":
                        req.getRequestDispatcher("/listaClientes.jsp").forward(req, resp);
                        break;
                    case "criar":
                        req.getRequestDispatcher("/criarCliente.jsp").forward(req, resp);
                        break;
                }
    }
}