package org.claumann.service;

import org.claumann.dao.ProductDao;
import org.claumann.model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {

    private final ProductDao productDao;

    public ProductService(final ProductDao productDao) {
        this.productDao = productDao;
    }

    public boolean saveProduct(final Product product) {
        boolean success;
        try {
            success = productDao.insert(product);
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting product", e);
        }
        return success;
    }

    public void updateProduct(final String code, final int quantity) {
        if (quantity < 0)
            throw new IllegalArgumentException("Quantity cannot be negative");

        try {
            productDao.update(code, quantity);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating product", e);
        }
    }

    public List<Product> findLowStock(final int quantity) {
        List<Product> lowStock;
        try {
            lowStock = productDao.findLowStock(quantity);
        } catch (SQLException e) {
            throw new RuntimeException("Error finding low stock products", e);
        }
        return lowStock;
    }

}
