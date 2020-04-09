package util;

import dao.AlbumController;
import main.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlbumControllerImpl implements AlbumController {
    private Database database;

    public AlbumControllerImpl(Database database) {
        this.database = database;
    }

    @Override
    public void create(String name, int artistId, int releaseYear) {
        try {
            String sql = "INSERT INTO albums(NAME, ARTIST_ID, RELEASE_YEAR) VALUES(?, ?, ?)";
            PreparedStatement pstmt = database.getCon().prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, artistId);
            pstmt.setInt(3, releaseYear);
            pstmt.executeUpdate();
            pstmt.close();
            database.commit();
            System.out.println("util.Album " + name + " created!");
        } catch (Exception e) {
            System.out.println("Cannot create album into DB: " + e);
        }
    }

    @Override
    public List<Album> findByArtist(int artistId) {
        List<Album> albums = new ArrayList<>();

        try {
            String sql = "SELECT * FROM albums WHERE ARTIST_ID = ?";
            PreparedStatement pstmt = database.getCon().prepareStatement(sql);
            pstmt.setInt(1, artistId);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                albums.add(new Album(rs.getInt("id"), rs.getString("name"),
                        rs.getInt("artist_id"), rs.getInt("release_year")));
            }
            pstmt.close();
            rs.close();

            return albums;
        } catch (Exception e) {
            System.out.println("Cannot get artist from DB: " + e);
        }

        return null;
    }
}
