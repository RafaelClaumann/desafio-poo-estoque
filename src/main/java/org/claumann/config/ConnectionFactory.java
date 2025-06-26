package org.claumann.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/estoque";
    private static final String DATABASE_USER = "admin";
    private static final String DATABASE_PASSWORD = "admin123";

    private static DatabaseConnection instance;
    private Connection connection;

    public static Connection ConnectionFactory() {
        try {
            return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }

}
