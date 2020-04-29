package app;

import entity.AlbumsEntity;
import entity.ArtistsEntity;
import entity.ChartsEntity;
import repo.AlbumRepository;
import repo.ArtistRepositoryJdbc;
import repo.ArtistRepositoryJpa;
import repo.ChartsRepository;
import util.ArtistRepositoryFactory;
import util.PersistenceUtil;

import java.util.List;

public class TestRepos {
    PersistenceUtil persistenceUtil;

    public TestRepos(PersistenceUtil persistenceUtil) {
        this.persistenceUtil = persistenceUtil;
    }

    public void testArtists() {
        ArtistRepositoryJpa artistRepository = new ArtistRepositoryJpa(persistenceUtil);

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

        ArtistRepositoryJpa artistRepository = new ArtistRepositoryJpa(persistenceUtil);
        System.out.println("Artist's (with id=15) albums:");
        albums = albumRepository.findByArtist(artistRepository.findById(15));
        for (AlbumsEntity album : albums)
            System.out.println(album);

        albumRepository.closeEm();
    }

    public void testCharts() {
        ChartsRepository chartsRepository = new ChartsRepository(persistenceUtil);

        System.out.println("Chart with id=1: " + chartsRepository.findById(1).getName());

//        chartsRepository.create(new ChartsEntity("Top Hits"));
//        chartsRepository.createChartsAlbum(new ChartsAlbumsEntity(6, 1, 1));
//        chartsRepository.createChartsAlbum(new ChartsAlbumsEntity(6, 2, 2));
//        chartsRepository.createChartsAlbum(new ChartsAlbumsEntity(6, 3, 3));

        System.out.println("Charts named 'Top Hits':");
        List<ChartsEntity> charts = chartsRepository.findByName("Top Hits");
        for (ChartsEntity chart : charts)
            System.out.println(chart);

        chartsRepository.closeEm();
    }

    public void testArtistRepoJpa(ArtistRepositoryFactory artistRepositoryFactory) {
        ArtistRepositoryJpa artistRepositoryJpa = (ArtistRepositoryJpa) artistRepositoryFactory.create("jpa");
        List<ArtistsEntity> artists = artistRepositoryJpa.findByName("Durer");
        for (ArtistsEntity artist : artists)
            System.out.println(artist);
    }

    public void testArtistRepoJdbc(ArtistRepositoryFactory artistRepositoryFactory) {
        ArtistRepositoryJdbc artistRepositoryJdbc = (ArtistRepositoryJdbc) artistRepositoryFactory.create("jdbc");
        List<ArtistsEntity> artists = artistRepositoryJdbc.findByName("Durer");
        for (ArtistsEntity artist : artists)
            System.out.println(artist);
    }
}
