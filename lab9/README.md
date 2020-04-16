Lab 9:

Compulsory (week 9):
- created a persistence unit MusicAlbumsPU", using Hibernate;
- package entity, with classes ArtistsEntity and AlbumsEntity, generated from MusicAlbums databse;
- package util, with class PersistenceUtil (singleton) which manages an EntityManagerFactory;
- package repo, with classes ArtistRepository(create, findById, findByName) and AlbumRepository(create, findById, findByName, findByArtist);
- package app, with the main class AlbumManager, with a few tests for artists and albums repos.
