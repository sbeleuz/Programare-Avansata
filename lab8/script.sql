create DATABASE MusicAlbums;
USE MusicAlbums;

create USER 'dba'@'localhost' IDENTIFIED BY 'sql';
grant all privileges on MusicAlbums. * TO 'dba'@'localhost';

create table artists
(
  id      int          not null AUTO_INCREMENT,
  name    varchar(100) not null,
  country varchar(100),
    CONSTRAINT id_pk PRIMARY KEY(id)
);

create table albums
(
    id           int          not null AUTO_INCREMENT,
    name         varchar(100) not null,
    artist_id    int          not null,
    release_year int,
    CONSTRAINT id_album_pk PRIMARY KEY (id),
    CONSTRAINT id_artist_fk
        FOREIGN KEY(artist_id)
            REFERENCES artists(id)
);

create table charts
(
  id     int not null,
  name   varchar(64),
  CONSTRAINT id_charts_pk PRIMARY KEY(id)
);

create table charts_albums
(
  id        int not null,
  chart_id  int not null,
  album_id  int not null,
  rank      int not null
  CONSTRAINT id_chart_fk
    FOREIGN KEY(chart_id)
        REFERENCES charts(id),
  CONSTRAINT id_album_fk
    FOREIGN KEY(album_id)
        REFERENCES albums(id)
);