package br.com.mercado.database.migrations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Table {

    private Connection conn;

    public Table(Connection conn) {
        this.conn = conn;
    }

    /**
     * Método criador de tabelas
     *
     * @param query, table
     */
    public void create(String query, String table) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(query);
            stmt.executeUpdate();

            System.out.println("Table " + table + " created");

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
