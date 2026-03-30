package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() throws SQLException{
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Conexao com PostgreSQL estabelecida.");
        } catch (ClassNotFoundException e){
            throw new SQLException("Driver PostgreSQL não encontrado: " + e.getMessage());
        }
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()){
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()){
                connection.close();
                System.out.println("Conexao encerrada.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexao: " + e.getMessage());
        }
    }

}

