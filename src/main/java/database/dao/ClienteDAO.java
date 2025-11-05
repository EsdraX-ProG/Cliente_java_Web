package database.dao;

import database.DatabaseConnector;
import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {


    public void inserir(Cliente cliente) throws SQLException {
        String sql = "insert INTO cliente (nome, email, telefone, cpf, endereco) values (?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DatabaseConnector.getConnector();
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getEndereco());

            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }

            DatabaseConnector.closeConnection(connection);

        }
    }

    public List<Cliente> listaTodos() throws SQLException {
        String sql = "select * from cliente order by id";
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        List<Cliente> clientes = new ArrayList<Cliente>();
        try {
            connection = DatabaseConnector.getConnector();
            stmt = connection.prepareStatement(sql);
            res = stmt.executeQuery();

            while (res.next()) {
                Cliente cliente = new Cliente();

                cliente.setNome(res.getString("nome"));
                cliente.setId(res.getInt("id"));
                cliente.setEmail(res.getString("email"));
                cliente.setTelefone(res.getString("telefone"));
                cliente.setCpf(res.getString("cpf"));
                cliente.setEndereco(res.getString("endereco"));

                clientes.add(cliente);
            }
            return clientes;

        } finally {
            if (res != null) {
                res.close();

            }
            if (stmt != null) {
                stmt.close();
            }
            DatabaseConnector.closeConnection(connection);

        }
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM cliente where id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnector.getConnector();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;


        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao excluir o usuario");
            e.printStackTrace();
            return false;
        }finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

     public boolean atualizar(Cliente cliente) {
         String sql = "UPDATE cliente SET nome = ?, email = ?, telefone = ?, cpf = ?, endereco = ? where id = ?";

         Connection conn = null;
         PreparedStatement stmt = null;
     try {
         conn = DatabaseConnector.getConnector();
         stmt = conn.prepareStatement(sql);
         stmt.setString(1, cliente.getNome());
         stmt.setString(2, cliente.getEmail());
         stmt.setString(3, cliente.getTelefone());
         stmt.setString(4, cliente.getCpf());
         stmt.setString(5, cliente.getEndereco());
         stmt.setInt(6, cliente.getId());
         //Executa a query no banco de dados
         int linhasAfetadas = stmt.executeUpdate();
         //precisa ser maior que zero para de fato confirmar a atualização
         //Se for igual a zero nao conseguiu atualizar
         return linhasAfetadas > 0;
     } catch (SQLException e) {
         System.out.println("Ocorreu um erro ao excluir o usuario");
         e.printStackTrace();
         return false;
     }finally {
         try {
             if (stmt != null) stmt.close();
             if (conn != null) conn.close();
         }catch (SQLException e){
             e.printStackTrace();
         }
     }

     }
     public Cliente buscarPorId(int id){
        String sql = "SELECT * from cliente WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;//O retorno vai do tipo result set
        Cliente cliente = null;

        try{
            conn = DatabaseConnector.getConnector();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()){
                cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setCpf(rs.getString("cpf"));
            }

        }catch(SQLException e){
            System.out.println("Cliente nao encontrado!");
            e.printStackTrace();
        }finally {
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if (conn != null) conn.close();

            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        return cliente;
     }
}


