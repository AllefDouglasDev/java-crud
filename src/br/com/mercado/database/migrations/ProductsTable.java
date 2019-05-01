package br.com.mercado.database.migrations;

public class ProductsTable implements Migration {

    @Override
    public void create(Table table) {
        table.create("CREATE TABLE IF NOT EXISTS products ("
                + " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                + " category_id INTEGER,"
                + " name VARCHAR(255) NOT NULL,"
                + " price FLOAT DEFAULT 0.0,"
                + " deleted INTEGER DEFAULT 0,"
                + "	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                + " updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                + " FOREIGN KEY (category_id) REFERENCES categories (id)"
                + ")", "products");
    }

}
