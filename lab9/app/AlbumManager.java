package app;

import util.*;

public class AlbumManager {
    PersistenceUtil persistenceUtil = PersistenceUtil.getInstance();

    public void insertData() {
        RandomData randomData = new RandomData(persistenceUtil);
        randomData.insertArtists(100);
        randomData.insertAlbums(200);
        randomData.insertGenres(100);
    }

    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);
        AlbumManager main = new AlbumManager();

//        main.insertData();

        LargestMatching largestMatching = new LargestMatching();
        largestMatching.test(main.persistenceUtil);

        main.persistenceUtil.closeEmf();
    }
}
