package org.claumann.dao;

import org.claumann.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    Product findByCode(final String code) throws SQLException;

    boolean insert(final Product product) throws SQLException;

    boolean update(final String code, final int quantity) throws SQLException;

    int delete(final String code) throws SQLException;

    List<Product> findLowStock(final int stockQuantity) throws SQLException;

    List<Product> listAll() throws SQLException;

}
