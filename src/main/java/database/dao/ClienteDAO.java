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
        }finally {
            if(stmt!= null){
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
        try{
            connection = DatabaseConnector.getConnector();
            stmt = connection.prepareStatement(sql);
            res = stmt.executeQuery();

            while(res.next()){
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

        }finally {
            if(res != null){
                res.close();

            }
            if(stmt != null){
                stmt.close();
            }
            DatabaseConnector.closeConnection(connection);

        }
    }
}