package dao;

import entity.Album;
import entity.Chart;
import database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class ChartControllerDb {
    private final Connection connection;

    public ChartControllerDb(Connection connection) {
        this.connection = connection;
    }

    public ChartControllerDb(Database database) {
        this.connection = database.getCon();
    }

    public int createChart(String name) {
        int key = -1;
        try {
            String sql = "INSERT INTO charts(NAME) VALUES(?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, name);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                key = rs.getInt(1);
            }

            pstmt.close();
            connection.commit();
            System.out.println("Chart " + name + " created!");
        } catch (Exception e) {
            System.out.println("Cannot create chart into DB: " + e);
        }

        return key;
    }

    public void insertAlbumIntoChart(int chartId, int albumId, int rank) {
        try {
            String sql = "INSERT INTO charts_albums(CHART_ID, ALBUM_ID, RANK) VALUES(?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, chartId);
            pstmt.setInt(2, albumId);
            pstmt.setInt(3, rank);
            pstmt.executeUpdate();

            pstmt.close();
            connection.commit();
            System.out.println("Album " + albumId + " inserted in chart " + chartId + " on rank " + rank);
        } catch (Exception e) {
            System.out.println("Cannot insert album into chart: " + e);
        }
    }

    public Chart getChartById(int id) {
        List<Album> albums = new ArrayList<>();
        String chartName = null;

        try {
            String sql =
                    "SELECT * FROM charts c JOIN charts_albums ca ON c.id = ca.chart_id JOIN albums a ON ca.album_id = a.id " +
                            "WHERE c.id = ? ORDER BY ca.rank";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                albums.add(new Album(rs.getInt("a.id"), rs.getString("a.name"),
                        rs.getInt("a.artist_id"), rs.getInt("a.release_year")));
                chartName = rs.getString("c.name");
            }

            pstmt.close();
            rs.close();

        } catch (Exception e) {
            System.out.println("Cannot get chart by id from DB: " + e);
        }

        return new Chart(id, chartName, albums);
    }

    public List<Chart> getAllCharts() {
        List<Chart> charts = new ArrayList<>();
        try {
            String sql = "SELECT id FROM charts";
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                charts.add(getChartById(rs.getInt("id")));
            }

            stmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Cannot get charts from DB: " + e);
        }

        return charts;
    }

    public Map<Integer, Integer> getArtistsScore(List<Chart> charts) {
        // for each artist, the score is the sum of
        // differences between the total number of albums in a chart and the rank in that chart
        Map<Integer, Integer> artistsScore = new HashMap<>();

        for (Chart chart : charts) {
            List<Album> albums = chart.getAlbums();
            int maxRank = albums.size() + 1;
            int rank = 1; // albums are already sorted by rank (from sql query)
            for (Album album : albums) {
                // compute for each artist sum of their albums' ranks in all charts
                int artistId = album.getArtistId();
                if (!artistsScore.containsKey(artistId)) {
                    artistsScore.put(artistId, maxRank - rank);
                } else {
                    artistsScore.put(artistId, artistsScore.get(artistId) + (maxRank - rank));
                }
                rank++;
            }
        }
        // sort artist scores by value
        List<Map.Entry<Integer, Integer>> scores = new ArrayList<>(artistsScore.entrySet());
        scores.sort(Map.Entry.<Integer, Integer>comparingByValue().reversed());

        Map<Integer, Integer> result = new LinkedHashMap<>(); // preserve the order of the elements in the resulting map
        for (Map.Entry<Integer, Integer> entry : scores) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
