package repo;

import entity.ArtistsEntity;
import util.PersistenceUtil;

import java.util.List;

public class ArtistRepositoryJpa extends AbstractRepository<ArtistsEntity> {

    public ArtistRepositoryJpa(PersistenceUtil persistenceUtil) {
        this.em = persistenceUtil.getEmf().createEntityManager();
    }

    @Override
    public ArtistsEntity findById(int id) {
        return em.find(ArtistsEntity.class, id);
    }

    @Override
    public List<ArtistsEntity> findByName(String name) {
        return em.createNamedQuery("Artists.findByName", ArtistsEntity.class)
                .setParameter("name", name)
                .getResultList();
    }
}
