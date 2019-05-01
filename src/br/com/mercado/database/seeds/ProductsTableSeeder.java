package br.com.mercado.database.seeds;

import java.util.Arrays;
import java.util.List;

import br.com.mercado.controllers.ProductController;
import br.com.mercado.database.DBSQLiteConnection;
import br.com.mercado.models.bean.Product;

public class ProductsTableSeeder implements Seeder {

    @Override
    public void run() {
        ProductController pc = new ProductController(new DBSQLiteConnection());

        List<Product> ps = Arrays.asList(
                new Product("Sabão", 3.5f, 1l),
                new Product("Sabonete", 1.5f, 1l)
        );

        ps.forEach(p -> pc.store(p));
    }

}
