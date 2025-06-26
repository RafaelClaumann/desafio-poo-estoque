package org.claumann;

import java.util.List;

public interface ProductDao {

    Product findByCode(final String code);

    void insert(final Product product);

    void update(final String code, final int quantity);

    void delete(final String code);

    List<Product> findLowStockProducts();

    List<Product> listAll();

}
