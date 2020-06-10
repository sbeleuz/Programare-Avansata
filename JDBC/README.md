Laboratorul 8:

Compulsory (week 8):
- having a database MusicAlbums (I used MySQL) with tables artists and albums;
- class DataBase - using Singleton - managing a connection to the database (connect, close);
- class ArtistController - DAO - a interface for creating an Artist and getting an Artist by name
- class ArtistControllerDb - my implementation of interface for dealing with sql server
- class Artist - representation of an artist;
- class AlbumController - DAO - a interface for creating an Album and getting Albums by artist
- class AlbumControllerDb - my implementation of interface for dealing with sql server
- class Album - representation of an album.

Optional & Bonus (week 9): 
- storing different charts in the database, using tables: charts(id, name) and charts_albums(id, chart_id, album_id, rank) + oop model -> class Chart;
- class RandomData - to insert random artists, albums and charts into db, using Java Faker;
- getting the ranking of the artists based on their position in charts and the size of chart + generating a HTML report using FreeMarker;
- class DBCPDataSource - creating a connection pool using Apache Commons DBCP;
- class ConcurrentTasks - using a ThreadPoolExecutor to create a number of tasks (class Task - implements Runnable) and test, using the singleton connection versus the coonection pool approach.
