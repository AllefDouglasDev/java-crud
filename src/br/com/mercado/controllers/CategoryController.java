package br.com.mercado.controllers;

import java.util.List;

import br.com.mercado.database.interfaces.Database;
import br.com.mercado.models.bean.Category;
import br.com.mercado.models.bean.Product;
import br.com.mercado.models.dao.CategoryDAO;
import br.com.mercado.models.interfaces.ICategoryDAO;

public class CategoryController {

    private ICategoryDAO cdao;

    public CategoryController(Database database) {
        cdao = CategoryDAO.getInstance(database);
    }

    public void store(Category category) {
        this.cdao.store(category);
    }

    public Category findOne(long id) {
        return this.cdao.findOne(id);
    }

    public List<Category> findAll() {
        return this.cdao.findAll();
    }

    public void update(long id, Category category) {
        this.cdao.update(id, category);
    }

    public void delete(long id) {
        this.cdao.delete(id);
    }

    public List<Product> products(long id){
        return this.cdao.products(id);
    }
}
