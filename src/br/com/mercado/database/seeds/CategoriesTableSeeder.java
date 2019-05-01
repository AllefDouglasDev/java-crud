package br.com.mercado.database.seeds;

import br.com.mercado.controllers.CategoryController;
import br.com.mercado.database.DBSQLiteConnection;
import br.com.mercado.models.bean.Category;

public class CategoriesTableSeeder implements Seeder {

    @Override
    public void run() {
        CategoryController cc = new CategoryController(new DBSQLiteConnection());

        Category category = new Category("Limpeza");

        cc.store(category);
    }

}
