package br.com.mercado.views;

import br.com.mercado.database.DBSQLiteConnection;
import br.com.mercado.database.interfaces.Database;
import br.com.mercado.database.migrations.MigrationBuilder;
import br.com.mercado.database.seeds.SeederBuilder;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
        normalizeDBConnection();

        System.out.println("Aplicação inicializada com sucesso");
        JOptionPane.showMessageDialog(null, "Aplicação inicializada com sucesso", "Aplicação", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void normalizeDBConnection() {
        Database database = new DBSQLiteConnection();
        database.getConnection();

        if (database.hasBeenRecreated()) {
            MigrationBuilder.migrate();
            SeederBuilder.seed();
            JOptionPane.showMessageDialog(null, "Banco de dados criado e migrations & seeder feitas", "Banco de dados", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}