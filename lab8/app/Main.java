package app;

import database.DBCPDataSource;
import database.Database;
import entity.Artist;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import dao.ArtistControllerDb;
import dao.ChartControllerDb;
import util.RandomData;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Main {

    public void insertData(Database database) {
        RandomData randomData = new RandomData(database);
        randomData.insertArtists(25);
        randomData.insertAlbums(50);
        randomData.insertCharts(5, 15);
    }

    public void printTopArtists(Map<Integer, Integer> artistsScore, Database database) {
        ArtistControllerDb artistController = new ArtistControllerDb(database);
        int rank = 1;
        System.out.println("Top artists: ");
        for (Integer id : artistsScore.keySet()) {
            Artist artist = artistController.findById(id);
            System.out.println(rank + ". " + artist + " with score " + artistsScore.get(id));
            rank++;
        }
    }

    public void generateHtmlReport(Map<Integer, Integer> artistsScore, Database database) throws IOException, TemplateException {
        ArtistControllerDb artistController = new ArtistControllerDb(database);
        List<Artist> artists = new ArrayList<>();
        for (Integer id : artistsScore.keySet()) {
            artists.add(artistController.findById(id));

            Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
            cfg.setDirectoryForTemplateLoading(new File("D:\\Code\\Facultate\\an II\\sem II\\Java\\lab8\\src\\main\\java\\templates"));

            /* Create a data-model */
            Map<Object, Object> root = new HashMap<>();
            root.put("artists", artists);

            /* Get the template (uses cache internally) */
            Template temp = cfg.getTemplate("template.ftlh");

            /* Merge data-model with template */
            Writer writer = new FileWriter("top_artists.html");
            temp.process(root, writer);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();

        /* connect to database */
        Database database = Database.getInstance();
        database.connect("jdbc:mysql://localhost:3306/MusicAlbums", "dba", "sql");

        /* generate random artists, albums, charts */
//        main.insertData(database);

        /* get top artists */
        ChartControllerDb chartController = new ChartControllerDb(database);
        Map<Integer, Integer> artistsScore = chartController.getArtistsScore(chartController.getAllCharts());
        main.printTopArtists(artistsScore, database);

        /* generate html for top artists */
        try {
            main.generateHtmlReport(artistsScore, database);
        } catch (IOException | TemplateException e) {
            System.out.println(e.getMessage());
        }

        /* wait 5 seconds before analyze, using Visual VM */
        System.out.println("Waiting to analyze...");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long startTime, endTime;

        ConcurrentTasks concurrentTasksConPool = new ConcurrentTasks(DBCPDataSource.getConnection());
        startTime = System.nanoTime();
        concurrentTasksConPool.executeTasks(100);
        endTime = System.nanoTime();
        System.out.println("Connection Pool time(ms):" + (endTime - startTime) / 1000000);

        ConcurrentTasks concurrentTasksSingleton = new ConcurrentTasks(Database.getInstance().getCon());
        startTime = System.nanoTime();
        concurrentTasksSingleton.executeTasks(100);
        endTime = System.nanoTime();
        System.out.println("Singleton time(ms):" + (endTime - startTime) / 1000000);

        database.close();
    }
}
