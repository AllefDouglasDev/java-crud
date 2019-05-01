package br.com.mercado.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.mercado.database.interfaces.Database;

public class DBSQLiteConnection implements Database {

    private boolean hasConnection;

    private boolean hasBeenRecreated;

    @Override
    public Connection getConnection() {
        Connection conn = null;

        try {
            verifyDatabaseExists();

            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:database/mercado.db";

            conn = DriverManager.getConnection(url);

            hasConnection = true;
        } catch (ClassNotFoundException e) {
            hasConnection = false;
            System.out.println("Classe de banco de dados não encontrada");
            e.printStackTrace();
        } catch (SQLException sqle) {
            hasConnection = false;
            System.out.println("Erro ao se conectar ao banco mercado.db");
            sqle.printStackTrace();
        } catch (Exception e) {
            hasConnection = false;
            System.out.println("Erro ao criar arquivo de banco de dados em database/mercado.db");
            e.printStackTrace();
        }

        return conn;
    }

    private void verifyDatabaseExists() throws Exception {
        verifyDBDirectoryExists();
        verifyDBFileExists();
    }

    private void verifyDBDirectoryExists() {
        File directory = new File("database");

        if (!directory.exists())
            createDatabaseDirectory(directory);
    }

    private void verifyDBFileExists() throws Exception {
        File db = new File("database\\mercado.db");

        if (!db.exists()) {
            createDatabaseFile(db);
            hasBeenRecreated = true;
        }
    }

    private void createDatabaseFile(File db) throws Exception {
        db.createNewFile();
    }

    private void createDatabaseDirectory(File directory) {
        directory.mkdir();
    }

    @Override
    public boolean hasBeenRecreated() {
        return hasBeenRecreated;
    }

    @Override
    public boolean hasConnection() {
        return hasConnection;
    }
}
