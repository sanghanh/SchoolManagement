package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

    private final String serverName = "localhost";
    private final String dbName = "SchoolManagementSystem";
    private final String userID = "sa";
    private final String password = "1234567890";
    private Connection conn = null;
    private static DBConnection instance = null;

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    private DBConnection() {
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://" + serverName + ";databaseName=" + dbName;
        conn = DriverManager.getConnection(url, userID, password);
//        conn = DriverManager.getConnection(url);
        return conn;
    }

    public ResultSet selectAll(String table) throws ClassNotFoundException, SQLException {
        try {
            String select = "select * from " + table;
            conn = getConnection();
            if (conn == null) {
                return null;
            }
            PreparedStatement ps = conn.prepareStatement(select);
            return ps.executeQuery();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public ResultSet selectWithCondition(String sql, Object... args) throws ClassNotFoundException, SQLException {
        try {
            conn = getConnection();
            if (conn == null) {
                return null;
            }
            PreparedStatement ps = getStmt(sql, args);
            return ps.executeQuery();
        } catch (Exception e) {
        }
        return null;
    }

    private PreparedStatement getStmt(String sql, Object... args) throws SQLException {
        try {
            conn = getConnection();
            if (conn == null) {
                return null;
            }
            PreparedStatement stmt = conn.prepareStatement(sql);
            if (args == null || args.length <= 0) {
                return stmt;
            }
            for (int i = 0; i < args.length; i++) {
                stmt.setObject(i + 1, args[i]);
            }
            return stmt;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public int insert(String table, Object... args) throws ClassNotFoundException, SQLException {
        try {
            String insert = "insert into " + table + " values(";
            for (int i = 0; i < args.length; i++) {
                insert += "?";
                if (i != args.length - 1) {
                    insert += ",";
                }
            }
            insert += ")";
            conn = getConnection();
            if (conn == null) {
                return -1;
            }
            PreparedStatement ps = getStmt(insert, args);
            return ps.executeUpdate();
        } catch (Exception e) {

        }
        return -1;
    }

    public int excuteSql(String sql, Object... args) throws ClassNotFoundException, SQLException {
        try {
            conn = getConnection();
            if (conn == null) {
                return -1;
            }
            PreparedStatement ps = getStmt(sql, args);
            return ps.executeUpdate();
        } catch (Exception e) {
        }
        return -1;
    }

    public void closeConnection() {
        if (conn != null)
			try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
