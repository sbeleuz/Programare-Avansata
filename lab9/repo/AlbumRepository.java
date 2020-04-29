package repo;

import entity.AlbumsEntity;
import entity.ArtistsEntity;
import util.PersistenceUtil;

import java.util.List;

public class AlbumRepository extends AbstractRepository<AlbumsEntity> {

    public AlbumRepository(PersistenceUtil persistenceUtil) {
        this.em = persistenceUtil.getEmf().createEntityManager();
    }

    @Override
    public AlbumsEntity findById(int id) {
        return em.find(AlbumsEntity.class, id);
    }

    @Override
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

    public List<AlbumsEntity> findAll() {
        return em.createQuery("SELECT a FROM AlbumsEntity a", AlbumsEntity.class)
                .getResultList();
    }
}
