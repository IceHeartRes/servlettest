package com.test.utils;

import com.test.utils.Constants;
import com.test.utils.PropertiesReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * класс подключения к БД
 */

public class JdbcConnector {
    protected static Statement getStatement() throws SQLException, ClassNotFoundException {
        Connection connect = getConnection();
        return connect.createStatement();
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        final Properties properties = PropertiesReader.getProperties();
        Class.forName(properties.getProperty(Constants.JDBC_DRIVER_CLASS_NAME));
        return DriverManager.getConnection(
                properties.getProperty(Constants.JDBC_URL),
                properties.getProperty(Constants.JDBC_USERNAME),
                properties.getProperty(Constants.JDBC_PASSWORD));
    }

}
