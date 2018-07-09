package com.test.utils;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public interface Constants {
    String JDBC_DRIVER_CLASS_NAME = "jdbc.driverClassName";
    String JDBC_URL = "jdbc.url";
    String JDBC_USERNAME = "jdbc.username";
    String JDBC_PASSWORD = "jdbc.password";

    DateTimeFormatter FORMATER = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.US);
    String SELECT_SQL = "SELECT * FROM parts";
    String DEFAULT_DIRECTION = "asc";
}
