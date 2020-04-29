package repo;

import entity.ArtistsEntity;
import util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepositoryJdbc extends AbstractRepository<ArtistsEntity> {
    private final Connection connection;

    public ArtistRepositoryJdbc(Connection connection) {
        this.connection = connection;
    }

    public ArtistRepositoryJdbc(Database database) {
        this.connection = database.getCon();
    }

    public int create(String name, String country) {
        int key = -1;
        try {
            String sql = "INSERT INTO artists(NAME, COUNTRY) VALUES(?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, name);
            pstmt.setString(2, country);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                key = rs.getInt(1);
            }

            pstmt.close();
            connection.commit();
            System.out.println("Artist " + name + " created!");
        } catch (Exception e) {
            System.out.println("Cannot create artist into DB: " + e);
        }

        return key;
    }

    public List<ArtistsEntity> findByName(String name) {
        List<ArtistsEntity> artists = new ArrayList<>();
        try {
            String sql = "SELECT * FROM artists WHERE NAME = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                artists.add(new ArtistsEntity(rs.getInt("id"), rs.getString("name"), rs.getString("country")));
            }

            pstmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Cannot get artist from DB: " + e);
        }

        return artists;
    }

    public ArtistsEntity findById(int id) {
        ArtistsEntity artist = null;
        try {
            String sql = "SELECT * FROM artists WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                artist = new ArtistsEntity(rs.getInt("id"), rs.getString("name"), rs.getString("country"));
            }

            pstmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Cannot get artist from DB: " + e);
        }

        return artist;
    }
}
