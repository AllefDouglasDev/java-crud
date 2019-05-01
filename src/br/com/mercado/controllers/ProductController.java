package br.com.mercado.controllers;

import java.util.List;

import br.com.mercado.database.interfaces.Database;
import br.com.mercado.models.bean.Category;
import br.com.mercado.models.bean.Product;
import br.com.mercado.models.dao.ProductDAO;
import br.com.mercado.models.interfaces.IProductDAO;

public class ProductController {

    private IProductDAO pdao;

    public ProductController(Database database) {
        pdao = ProductDAO.getInstance(database);
    }

    public void store(Product product) {
        this.pdao.store(product);
    }

    public List<Product> findAll() {
        return this.pdao.findAll();
    }

    public Product findOne(long id) {
        return this.pdao.findOne(id);
    }

    public void update(long id, Product product) {
        this.pdao.update(id, product);
    }

    public void delete(long id) {
        this.pdao.delete(id);
    }

    public Category category(long id) {
        return this.pdao.category(id);
    }
}
