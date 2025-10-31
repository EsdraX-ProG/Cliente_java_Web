package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/gestao_cliente";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnector() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);

    }

    public static void closeConnection(Connection connection){
    try {
        connection.close();
    }catch (SQLException e) {
        System.out.println("Ocorreu um erro ao encerrar a connection com o banco");
        throw new RuntimeException(e);
    }
    }
}
