package br.com.mercado.models.interfaces;

import java.util.List;

import br.com.mercado.models.bean.Category;
import br.com.mercado.models.bean.Product;

public interface ICategoryDAO {
    void store(Category category);
    List<Category> findAll();
    Category findOne(long id);
    void update(long id, Category category);
    void delete(long id);
    // Relacionamento 1 -> n
    List<Product> products(long id);
}
