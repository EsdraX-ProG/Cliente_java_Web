package servlet;

import model.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/clientes")
public class ClienteServelet extends HttpServlet {

    private int id = 1;


    //Lista com valor dinamico
    private List<Cliente> clientes = new ArrayList<Cliente>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String acao = req.getParameter("acao");

        System.out.println("Chegou aqui");

                switch(acao){
                    case "listar":
                        req.setAttribute("clientes", clientes);

                        req.getRequestDispatcher("/listaClientes.jsp").forward(req, resp);
                        break;
                    case "criar":
                        req.getRequestDispatcher("/criarCliente.jsp").forward(req, resp);
                        break;

                    case "deletar":
                    deletarCliente(req, resp);
                        break;
                }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");

        if (acao == null){
            acao = "inserir";
        }

        switch (acao){
            case "inserir":

                inserirCliente(req, resp);
                break;

            case "atualiozar":
                break;

        }
    }

    private  void inserirCliente(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        //pega os valores enviados do formulario
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String telefone = req.getParameter("telefone");
        String cpf = req.getParameter("cpf");
        String endereco = req.getParameter("endereco");

        // Cria o cliente
        Cliente cliente = new Cliente (id, nome, email, telefone, cpf, endereco);
        id++;
        // Salva o cliente na lista
        clientes.add(cliente);

        resp.sendRedirect("/clientes?acao=listar");

    }

    private void deletarCliente(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idCliente = req.getParameter("id");
        int id = Integer.parseInt(idCliente);
        //procura na lista um elemento que atende a condição , quando achar remove ele
        clientes.removeIf(cliente -> cliente.getId() == id);

        resp.sendRedirect("/clientes?acao=listar");

    }
    private void editarCliente(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idCliente = req.getParameter("id");
        int id = Integer.parseInt(idCliente);

        //buscar o cliente na lista
        Cliente cliente = clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
        if (cliente == null) {
            //se nao encontrar o cliente retorna para pagina da listagem
            resp.sendRedirect("/cliente?acao=listar");
        }
        req.setAttribute("cliente" , cliente);
        req.getRequestDispatcher("/criarCliente.jsp");

        }
}