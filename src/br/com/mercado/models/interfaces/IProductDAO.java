package br.com.mercado.models.interfaces;

import java.util.List;

import br.com.mercado.models.bean.Category;
import br.com.mercado.models.bean.Product;

public interface IProductDAO {
    void store(Product product);
    List<Product> findAll();
    Product findOne(long id);
    void update(long id, Product product);
    void delete(long id);
    // Relacionamento n -> 1
    Category category(long id);
}
