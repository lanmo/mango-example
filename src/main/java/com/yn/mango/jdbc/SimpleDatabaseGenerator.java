package com.yn.mango.jdbc;

import com.yn.mango.binding.InvocationContext;

/**
 * Created by yangnan on 16/11/8.
 */
public class SimpleDatabaseGenerator implements DatabaseGenerator {
    private final String database;

    public SimpleDatabaseGenerator(String database) {
        this.database = database;
    }

    public String getDatabase() {
        return database;
    }

    public String getDatabase(InvocationContext context) {
        return database;
    }
}
