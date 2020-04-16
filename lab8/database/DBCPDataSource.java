package database;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/* https://www.baeldung.com/java-connection-pooling */
public class DBCPDataSource {
    private static final BasicDataSource ds = new BasicDataSource();

    static {
        ds.setUrl("jdbc:mysql://localhost:3306/MusicAlbums");
        ds.setUsername("dba");
        ds.setPassword("sql");
        ds.setInitialSize(100);
    }

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private DBCPDataSource() {
    }
}
