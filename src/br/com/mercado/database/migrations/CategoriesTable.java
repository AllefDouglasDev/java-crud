package br.com.mercado.database.migrations;

public class CategoriesTable implements Migration {

    @Override
    public void create(Table table) {
        table.create("CREATE TABLE IF NOT EXISTS categories ("
                + " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                + " name VARCHAR(70) NOT NULL,"
                + " deleted INTEGER DEFAULT 0,"
                + "	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                + " updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                + ")", "categories");
    }

}
