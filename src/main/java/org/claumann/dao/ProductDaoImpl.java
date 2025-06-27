package org.claumann.dao;

import org.claumann.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private final Connection connection;

    public ProductDaoImpl(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public Product findByCode(final String code) throws SQLException {
        final String sql = "SELECT * FROM product WHERE code = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, code);

            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Product(
                        resultSet.getString("code"),
                        resultSet.getString("name"),
                        resultSet.getInt("quantity")
                );
            }
        }
        return null;
    }

    @Override
    public boolean insert(final Product product) throws SQLException {
        String sql = "INSERT INTO product (code, name, quantity) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getCode());
            statement.setString(2, product.getNome());
            statement.setInt(3, product.getQuantity());

            final int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public boolean update(final String code, final int quantity) throws SQLException {
        final String sql = "UPDATE product SET code = ?, quantity = ? WHERE code = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, code);
            statement.setInt(2, quantity);

            final int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public int delete(final String code) throws SQLException {
        final String sql = "DELETE FROM product WHERE code = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, code);
            return statement.executeUpdate();
        }
    }

    @Override
    public List<Product> findLowStock(final int stockQuantity) throws SQLException {
        final List<Product> products = new ArrayList<>();
        final String sql = "SELECT * FROM product WHERE quantity < ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, stockQuantity);

            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                final Product product = new Product(
                        resultSet.getString("code"),
                        resultSet.getString("name"),
                        resultSet.getInt("quantity")
                );
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public List<Product> listAll() throws SQLException {
        final List<Product> products = new ArrayList<>();
        final String sql = "SELECT * FROM product";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                final Product product = new Product(
                        resultSet.getString("code"),
                        resultSet.getString("name"),
                        resultSet.getInt("quantity")
                );
                products.add(product);
            }
        }
        return products;
    }

}
