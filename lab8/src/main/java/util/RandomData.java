package util;

import com.github.javafaker.Faker;
import dao.AlbumControllerDb;
import dao.ArtistControllerDb;
import dao.ChartControllerDb;
import database.Database;

import java.util.*;

public class RandomData {
    private final Database database;
    private final List<Integer> artistIds = new ArrayList<>();
    private final List<Integer> albumIds = new ArrayList<>();

    public RandomData(Database database) {
        this.database = database;
    }

    public void insertArtists(int maxNoOfData) {
        ArtistControllerDb artistController = new ArtistControllerDb(database);
        Faker faker = new Faker();

        maxNoOfData = new Random().nextInt(maxNoOfData) + 1;
        for (int i = 0; i < maxNoOfData; i++) {
            int id = artistController.create(faker.artist().name(), faker.country().name());
            artistIds.add(id);
        }
    }

    public void insertAlbums(int maxNoOfData) {
        AlbumControllerDb albumController = new AlbumControllerDb(database);
        Faker faker = new Faker();

        maxNoOfData = new Random().nextInt(maxNoOfData) + 1;
        int noOfArtists = artistIds.size();
        for (int i = 0; i < maxNoOfData; i++) {
            int artistId = artistIds.get(faker.number().numberBetween(0, noOfArtists - 1));
            int id = albumController.create(faker.book().title(), artistId, faker.number().numberBetween(1950, 2020));
            albumIds.add(id);
        }
    }

    public void insertCharts(int maxNoOfData, int maxNoOfAlbumsPerChart) {
        ChartControllerDb chartController = new ChartControllerDb(database);
        Faker faker = new Faker();

        maxNoOfData = new Random().nextInt(maxNoOfData) + 1;
        int noOfAlbums = albumIds.size();
        for (int i = 0; i < maxNoOfData; i++) {
            int chartId = chartController.createChart(faker.rockBand().name());
            Map<Integer, Boolean> insertedIntoChart = new HashMap<>(); // check if an album was already inserted in current chart
            maxNoOfAlbumsPerChart = new Random().nextInt(maxNoOfAlbumsPerChart) + 1;
            for (int j = 0; j < maxNoOfAlbumsPerChart; j++) {
                int albumId = albumIds.get(faker.number().numberBetween(0, noOfAlbums - 1));
                if (!insertedIntoChart.containsKey(albumId)) {
                    chartController.insertAlbumIntoChart(chartId, albumId, j + 1);
                    insertedIntoChart.put(albumId, true);
                } else j--;
            }
        }
    }

}
