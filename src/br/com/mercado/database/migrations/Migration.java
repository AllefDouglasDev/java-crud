package br.com.mercado.database.migrations;

public interface Migration {
    void create(Table table);
}
