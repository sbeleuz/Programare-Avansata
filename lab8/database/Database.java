package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static Connection con;

    private static final Database instance = new Database();

    private Database() {
    }

    public Connection getCon() {
        return con;
    }

    public static Database getInstance() {
        return instance;
    }

    public void connect(String url, String user, String password) {
        try {
            con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(false);
            System.out.println("Connection established!");
        } catch (Exception e) {
            System.out.println("Cannot connect to DB: " + e);
        }
    }

    public void close() {
        try {
            con.close();
            System.out.println("Connection closed!");
        } catch (Exception e) {
            System.out.println("Cannot close DB: " + e);
        }
    }
}