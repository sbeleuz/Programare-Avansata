package repo;

import entity.AlbumsEntity;
import entity.ArtistsEntity;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class AlbumRepository {
    EntityManager em;

    public AlbumRepository(PersistenceUtil persistenceUtil) {
        this.em = persistenceUtil.getEmf().createEntityManager();
    }

    public void create(AlbumsEntity album) {
        em.getTransaction().begin();
        em.persist(album);
        em.getTransaction().commit();
        System.out.println("Album '" + album.getName() + "' has been created.");
    }

    public AlbumsEntity findById(int id) {
        return em.find(AlbumsEntity.class, id);
    }

    public List<AlbumsEntity> findByName(String name) {
        return em.createNamedQuery("Albums.findByName", AlbumsEntity.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<AlbumsEntity> findByArtist(ArtistsEntity artist) {
        return em.createNamedQuery("Albums.findByArtist", AlbumsEntity.class)
                .setParameter("artistId", artist.getId())
                .getResultList();
    }

    public void closeEm() {
        em.close();
    }
}
