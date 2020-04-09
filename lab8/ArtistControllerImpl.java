package util;

import dao.ArtistController;
import main.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ArtistControllerImpl implements ArtistController {
    private Database database;

    public ArtistControllerImpl(Database database) {
        this.database = database;
    }

    @Override
    public void create(String name, String country) {
        try {
            String sql = "INSERT INTO artists(NAME, COUNTRY) VALUES(?, ?)";
            PreparedStatement pstmt = database.getCon().prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, country);
            pstmt.executeUpdate();
            pstmt.close();
            database.commit();
            System.out.println("util.Artist " + name + " created!");
        } catch (Exception e) {
            System.out.println("Cannot create artist into DB: " + e);
        }
    }

    @Override
    public Artist findByName(String name) {
        try {
            String sql = "SELECT * FROM artists WHERE NAME = ?";
            PreparedStatement pstmt = database.getCon().prepareStatement(sql);
            pstmt.setString(1, name);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Artist(rs.getInt("id"), rs.getString("name"), rs.getString("country"));
            }
            pstmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Cannot get artist from DB: " + e);
        }
        return null;
    }
}
