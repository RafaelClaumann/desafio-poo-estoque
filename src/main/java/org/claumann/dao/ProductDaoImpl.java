package org.claumann;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductDaoImpl implements ProductDao {

    private final List<Product> products = new ArrayList<Product>();


    @Override
    public Product findByCode(final String code) {
        return products.stream()
                .filter(item -> item.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("Product with code %s not found", code)));
    }

    @Override
    public void insert(final Product product) {
        final int index = products.indexOf(product);
        if (index != -1)
            throw new RuntimeException("Product already exists");

        products.add(product);
    }

    @Override
    public void update(final String code, final int quantity) {
        products.stream()
                .filter(product -> product.getCode().equals(code))
                .findFirst()
                .ifPresent(item -> item.setQuantity(quantity));
    }

    @Override
    public void delete(final String code) {
        products.stream()
                .filter(product -> product.getCode().equals(code))
                .findFirst()
                .ifPresentOrElse(
                        products::remove,
                        () -> {
                            throw new NoSuchElementException("Product not found with code: " + code);
                        }
                );
    }

    @Override
    public List<Product> findLowStockProducts() {
        return products.stream()
                .filter(product -> product.getQuantity() < 10)
                .toList();
    }

    @Override
    public List<Product> listAll() {
        return this.products;
    }

}
