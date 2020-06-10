package repo;

import entity.ChartsAlbumsEntity;
import entity.ChartsEntity;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class ChartsRepository extends AbstractRepository<ChartsEntity> {

    public ChartsRepository(PersistenceUtil persistenceUtil) {
        this.em = persistenceUtil.getEmf().createEntityManager();
    }

    public void createChartsAlbum(ChartsAlbumsEntity chartsAlbum) {
        em.getTransaction().begin();
        em.persist(chartsAlbum);
        em.getTransaction().commit();
        System.out.println("Album " + chartsAlbum.getAlbumId() + " has been added in chart "
                + chartsAlbum.getChartId() + "on rank " + chartsAlbum.getRank());
    }

    public ChartsEntity findById(int id) {
        return em.find(ChartsEntity.class, id);
    }

    public List<ChartsEntity> findByName(String name) {
        return em.createNamedQuery("Charts.findByName", ChartsEntity.class)
                .setParameter("name", name)
                .getResultList();
    }
}
