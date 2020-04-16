package dao;

import entity.Album;
import database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlbumControllerDb implements AlbumController {
    private final Connection connection;

    public AlbumControllerDb(Connection connection) {
        this.connection = connection;
    }

    public AlbumControllerDb(Database database) {
        this.connection = database.getCon();
    }

    @Override
    public int create(String name, int artistId, int releaseYear) {
        int key = -1;
        try {
            String sql = "INSERT INTO albums(NAME, ARTIST_ID, RELEASE_YEAR) VALUES(?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, name);
            pstmt.setInt(2, artistId);
            pstmt.setInt(3, releaseYear);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                key = rs.getInt(1);
            }

            pstmt.close();
            connection.commit();
            System.out.println("Album " + name + " created!");
        } catch (Exception e) {
            System.out.println("Cannot create album into DB: " + e);
        }

        return key;
    }

    @Override
    public List<Album> findByArtist(int artistId) {
        List<Album> albums = new ArrayList<>();

        try {
            String sql = "SELECT * FROM albums WHERE ARTIST_ID = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, artistId);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                albums.add(new Album(rs.getInt("id"), rs.getString("name"),
                        rs.getInt("artist_id"), rs.getInt("release_year")));
            }

            pstmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Cannot get artist from DB: " + e);
        }

        return albums;
    }
}
