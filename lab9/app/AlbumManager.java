package app;

import entity.AlbumsEntity;
import entity.ArtistsEntity;
import repo.AlbumRepository;
import repo.ArtistRepository;
import util.PersistenceUtil;

import java.util.List;

public class AlbumManager {
    PersistenceUtil persistenceUtil = PersistenceUtil.getInstance();

    public void testArtists() {
        ArtistRepository artistRepository = new ArtistRepository(persistenceUtil);

        System.out.println("Artist with id=1: " + artistRepository.findById(1).getName());

//        artistRepository.create(new ArtistsEntity("Durer", "Romania"));

        System.out.println("Artists named Durer:");
        List<ArtistsEntity> artists = artistRepository.findByName("Durer");
        for (ArtistsEntity artist : artists)
            System.out.println(artist);

        artistRepository.closeEm();
    }

    public void testAlbums() {
        AlbumRepository albumRepository = new AlbumRepository(persistenceUtil);

        System.out.println("Album with id=1: " + albumRepository.findById(1).getName());

//        albumRepository.create(new AlbumsEntity("A rock album", 2020, 15));

        System.out.println("Albums named 'Ego Dominus Tuus':");
        List<AlbumsEntity> albums = albumRepository.findByName("Ego Dominus Tuus");
        for (AlbumsEntity album : albums)
            System.out.println(album);

        ArtistRepository artistRepository = new ArtistRepository(persistenceUtil);
        System.out.println("Artist's (with id=15) albums:");
        albums = albumRepository.findByArtist(artistRepository.findById(15));
        for (AlbumsEntity album : albums)
            System.out.println(album);

        albumRepository.closeEm();
    }

    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);

        AlbumManager main = new AlbumManager();
        main.testArtists();
        System.out.println("--------------------------------------------------------------");
        main.testAlbums();

        main.persistenceUtil.closeEmf();
    }
}
