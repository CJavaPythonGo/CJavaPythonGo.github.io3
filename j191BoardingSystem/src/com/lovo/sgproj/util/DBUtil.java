package com.lovo.sgproj.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    private static Properties dbProps;

    static{
        dbProps = new Properties();
        try {
            dbProps.load(new FileInputStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName(dbProps.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection con = null;
        con = DriverManager.getConnection(dbProps.getProperty("url"),
                dbProps.getProperty("username"),dbProps.getProperty("password"));
        return con;
    }

}

