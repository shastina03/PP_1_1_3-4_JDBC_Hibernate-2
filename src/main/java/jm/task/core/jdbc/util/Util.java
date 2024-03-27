package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.engine.jdbc.dialect.spi.DatabaseMetaDataDialectResolutionInfoAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/newbd";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    static Connection connection;

    public static Connection getConnection() {

        try {
            connection = DriverManager.getConnection(URL, USERNAME,PASSWORD);
            System.out.println("Соединение прошло успешно");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
