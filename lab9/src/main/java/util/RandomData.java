package util;

import com.github.javafaker.Faker;
import entity.AlbumsEntity;
import entity.ArtistsEntity;
import entity.GenresAlbumsEntity;
import entity.GenresEntity;
import repo.AlbumRepository;
import repo.ArtistRepositoryJpa;
import repo.GenreRepository;

import java.util.*;

public class RandomData {
    PersistenceUtil persistenceUtil;
    private final List<Integer> artistIds = new ArrayList<>();
    private final List<Integer> albumIds = new ArrayList<>();

    public RandomData(PersistenceUtil persistenceUtil) {
        this.persistenceUtil = persistenceUtil;
    }

    public void insertArtists(int noOfData) {
        ArtistRepositoryJpa artistRepositoryJpa = new ArtistRepositoryJpa(persistenceUtil);
        Faker faker = new Faker();

        for (int i = 0; i < noOfData; i++) {
            int id = artistRepositoryJpa.create(new ArtistsEntity(faker.artist().name(), faker.address().country()));
            artistIds.add(id);
        }

        System.out.println(noOfData + " artists inserted!");
    }

    public void insertAlbums(int noOfData) {
        AlbumRepository albumRepository = new AlbumRepository(persistenceUtil);
        Faker faker = new Faker();

        int noOfArtists = artistIds.size();
        for (int i = 0; i < noOfData; i++) {
            int artistId = artistIds.get(faker.number().numberBetween(0, noOfArtists - 1));
            int id = albumRepository.create(new AlbumsEntity(faker.book().title(), faker.number().numberBetween(1950, 2020), artistId));
            albumIds.add(id);
        }
        System.out.println(noOfData + " albums inserted!");
    }

    public void insertGenres(int noOfData) {
        GenreRepository genreRepository = new GenreRepository(persistenceUtil);
        Faker faker = new Faker();

        // inserted genres
        for (int i = 0; i < noOfData; i++) {
            genreRepository.create(new GenresEntity(faker.music().genre()));
        }
        System.out.println(noOfData + " genres inserted!");

        // insert each album into a genre
        for (int albumId : albumIds) {
            int genreId = faker.number().numberBetween(1, noOfData + 1);
            genreRepository.createGenresAlbum(new GenresAlbumsEntity(genreId, albumId));
        }
        System.out.println("Albums inserted into genres!");
    }
}
