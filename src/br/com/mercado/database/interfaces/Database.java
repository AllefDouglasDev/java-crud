package br.com.mercado.database.interfaces;

import java.sql.Connection;

public interface Database {
    Connection getConnection();
    boolean hasBeenRecreated();
    boolean hasConnection();
}
