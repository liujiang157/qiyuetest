package com.qiyue.server.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:derby://localhost:1527/file_test;create=true";
    private static final String DRIVER_CLASS_NAME = "org.apache.derby.jdbc.ClientDriver";
    private static Connection conn = null;

    //创建连接
    static {
        try {
            //加载驱动
            Class.forName(DRIVER_CLASS_NAME);
            //获取连接
            conn = DriverManager.getConnection(URL);
            conn.setAutoCommit(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单例模式
     * @return 数据库连接
     */
    public static Connection getConnection() {
        return conn;
    }
}
