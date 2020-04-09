package main;

import dao.AlbumController;
import dao.ArtistController;
import util.Album;
import util.AlbumControllerImpl;
import util.ArtistControllerImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        database.connect("jdbc:mysql://localhost:3306/MusicAlbums","dba","sql");

        ArtistController artistController = new ArtistControllerImpl(database);
//        artistController.create("Post Malone", "USA");
//        artistController.create("Future", "USA");
        System.out.println(artistController.findByName("Post Malone"));

        AlbumController albumController = new AlbumControllerImpl(database);
//        albumController.create("Hollywood''s Bleeding", 3, 2019);
//        albumController.create("Beerbongs & Bentleys", 3, 2018);
//        albumController.create("Stoney", 3, 2016);
        List<Album> albums = albumController.findByArtist(1);
        for (Album album : albums) System.out.println(album);

        database.close();
    }
}
