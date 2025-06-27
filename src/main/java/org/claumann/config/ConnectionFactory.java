package org.claumann.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String DATABASE_NAME = "estoque";
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/" + DATABASE_NAME;
    private static final String DATABASE_USER = "admin";
    private static final String DATABASE_PASSWORD = "admin123";

    private static ConnectionFactory instance;
    private final Connection connection;

    private ConnectionFactory() {
        try {
            this.connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }

    public static ConnectionFactory getInstance() {
        if (instance == null) {
            ConnectionFactory.instance = new ConnectionFactory();
            return instance;
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (this.connection != null) {
            try {
                this.connection.close();
                System.out.println("Conexão fechada!");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

}
