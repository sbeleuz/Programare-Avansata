Lab 9:

Compulsory (week 9):
- created a persistence unit MusicAlbumsPU", using Hibernate;
- package entity, with classes ArtistsEntity and AlbumsEntity, generated from MusicAlbums databse;
- package util, with class PersistenceUtil (singleton) which manages an EntityManagerFactory;
- package repo, with classes ArtistRepository(create, findById, findByName) and AlbumRepository(create, findById, findByName, findByArtist);
- package app, with the main class AlbumManager, with a few tests for artists and albums repos.

Optional (week 10):
- added support for charts: \
  entity: ChartsEntity (describing a chart: id and name), ChartsAlbumsEntity (describing a album inside a chart: chart_id, album_id and rank); \
  repo: ChartsRepository (create, add album in a chart, find by dd, find by name).
- class AbstractRepository<E extends AbstractEntity> for creating repository classes
- class AbstractFactory for creating Repositories and implemented in class ArtistRepositoryFactory, allowing to create both jdbc and jpa artist repository implementations.
  
Bonus (week 10):
- added support for music genres:
  entity: GenresEntity (describing a genre: id and name), GenresAlbumsEntity (describing a album, having a genre : genre_id, album_id); \
  repo: GenresRepository (create, add album in a genre, find by dd, find by name, find by album).
- populate database with a large number of albums: class RandomData;
- algorithm that returns the largest set of albums such that no two albums have the same artist or belong to the same genre: class LargestMatching - I used a flow network built from a directed bipartite graph (where a part has artists and the other has genres, where an edge from note u to node v is an album with artist u and genre v) + source and sink, with the help of JGraphT library. Then my algorithm tries to find a path from source to sink (I used DFS) and when a path is found, I reverse the edges on current path. The final matching solution is the set of reversed edges between artists and genres (https://saco-evaluator.org.za/presentations/camp2_2017/bipartitematching-robin.pdf);
- using JUnit and JGraphT implemented algorithm for the largest matching in a bipartite graph, I tested my algorithm; 
