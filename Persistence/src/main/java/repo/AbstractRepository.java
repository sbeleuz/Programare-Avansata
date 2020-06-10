package repo;

import entity.AbstractEntity;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class AbstractRepository<E extends AbstractEntity> {
    protected EntityManager em;

    public int create(E entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.flush();
        int id = entity.getId();
        em.getTransaction().commit();
        return id;
    }

    public abstract E findById(int id);

    public abstract List<E> findByName(String name);

    public void closeEm() {
        em.close();
    }
}
