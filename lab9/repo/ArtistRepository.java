package repo;

import entity.ArtistsEntity;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ArtistRepository {
    EntityManager em;

    public ArtistRepository(PersistenceUtil persistenceUtil) {
        this.em = persistenceUtil.getEmf().createEntityManager();
    }

    public void create(ArtistsEntity artist) {
        em.getTransaction().begin();
        em.persist(artist);
        em.getTransaction().commit();
        System.out.println("Artist " + artist.getName() + " has been created.");
    }

    public ArtistsEntity findById(int id) {
        return em.find(ArtistsEntity.class, id);
    }

    public List<ArtistsEntity> findByName(String name) {
        return em.createNamedQuery("Artists.findByName", ArtistsEntity.class)
                .setParameter("name", name)
                .getResultList();
    }

    public void closeEm() {
        em.close();
    }
}
