package servlet;

import database.dao.ClienteDAO;
import model.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/clientes")
public class ClienteServelet extends HttpServlet {

    private int id = 1;

    private ClienteDAO clienteDAO = new ClienteDAO();


    //Lista com valor dinamico
    private List<Cliente> clientes = new ArrayList<Cliente>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String acao = req.getParameter("acao");

        System.out.println("Chegou aqui");

                switch(acao){
                    case "listar":
                        try {

                            List<Cliente> clienteDB = clienteDAO.listaTodos();
                            req.setAttribute("clientes", clienteDB);
                        }catch (SQLException e){
                            System.out.println("Erro ao listar clientes");
                            throw new  RuntimeException(e);

                        }
                        req.getRequestDispatcher("/listaClientes.jsp").forward(req, resp);
                        break;
                    case "criar":
                        req.getRequestDispatcher("/criarCliente.jsp").forward(req, resp);
                        break;
                    case "editar":
                        editarCliente(req, resp);
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
            case "atualizar":
                atualizarCliente(req, resp);
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
        Cliente cliente = new Cliente ( nome, email, telefone, cpf, endereco);
        try {

            clienteDAO.inserir(cliente);
            resp.sendRedirect("/clientes?acao=listar");

        }catch(SQLException e){
            System.out.println("Erro ao inserir cliente");
            throw new RuntimeException(e);

        }

    }

    private void deletarCliente(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idCliente = req.getParameter("id");
        int id = Integer.parseInt(idCliente);
        //procura na lista um elemento que atende a condição , quando achar remove ele
        clientes.removeIf(cliente -> cliente.getId() == id);

        resp.sendRedirect("/clientes?acao=listar");

    }
    private void editarCliente(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
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
            return;
        }

        req.setAttribute("cliente", cliente);
        req.getRequestDispatcher("/criarCliente.jsp").forward(req, resp);
    }
        private void atualizarCliente(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            String nome = req.getParameter("nome");
            String email = req.getParameter("email");
            String telefone = req.getParameter("telefone");
            String cpf = req.getParameter("cpf");
            String endereco = req.getParameter("endereco");
            String IdCliente = req.getParameter("id");

            int id = Integer.parseInt(IdCliente);

            for (int i = 0; i < clientes.size(); i++){
                if(clientes.get(i).getId() == id){
                    Cliente atualizado = new Cliente(id, nome, email, telefone, cpf, endereco);
                    clientes.set(i, atualizado);
                    break;// parar o for quando atulizar o cliente
                }
            }
            resp.sendRedirect("/clientes?acao=listar");
        }

}