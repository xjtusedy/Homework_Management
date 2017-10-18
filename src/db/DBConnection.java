package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String userneme = "root";
    private static final String password = "123456";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://127.0.0.1:3306/stuhomework";
    private Connection con;
    private static DBConnection dbcon = new DBConnection();
    private DBConnection() {
        try {
            con = DriverManager.getConnection(url, userneme, password);
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new InternalError();
        }
    }

    public static DBConnection getInstance() {
        return dbcon;
    }

    Connection getConnection() {
        return con;
    }
}
