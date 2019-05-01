package br.com.mercado.database.migrations;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import br.com.mercado.database.DBSQLiteConnection;

public class MigrationBuilder {

    private static Connection conn;

    private static List<Migration> migrations;

    /**
     * Cria tabelas por ordem de adição de migration
     */
    public static void migrate() {
        conn = new DBSQLiteConnection().getConnection();

        add(new CategoriesTable());
        add(new ProductsTable());

        run();
    }

    private static void add(Migration m) {
        if (null == migrations)
            migrations = new ArrayList<>();

        migrations.add(m);
    }

    private static void run() {
        if (null == migrations)
            return;

        Table table = new Table(conn);

        migrations.forEach(m -> m.create(table));
    }

}
