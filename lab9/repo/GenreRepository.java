package repo;

import entity.AlbumsEntity;
import entity.GenresAlbumsEntity;
import entity.GenresEntity;
import util.PersistenceUtil;

import java.util.List;

public class GenreRepository extends AbstractRepository<GenresEntity> {
    public GenreRepository(PersistenceUtil persistenceUtil) {
        this.em = persistenceUtil.getEmf().createEntityManager();
    }

    public void createGenresAlbum(GenresAlbumsEntity genresAlbum) {
        em.getTransaction().begin();
        em.persist(genresAlbum);
        em.getTransaction().commit();
    }

    @Override
    public GenresEntity findById(int id) {
        return em.find(GenresEntity.class, id);
    }

    @Override
    public List<GenresEntity> findByName(String name) {
        return em.createNamedQuery("Genres.findByName", GenresEntity.class)
                .setParameter("name", name)
                .getResultList();
    }

    public int findByAlbum(AlbumsEntity album) {
        return em.createQuery("SELECT g.genreId FROM GenresAlbumsEntity g where g.albumId=:albumId", Integer.class)
                .setParameter("albumId", album.getId())
                .getSingleResult();
    }
}
