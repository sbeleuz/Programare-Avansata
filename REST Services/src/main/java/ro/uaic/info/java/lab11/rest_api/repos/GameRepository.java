package ro.uaic.info.java.lab11.rest_api.repos;

import ro.uaic.info.java.lab11.rest_api.entities.GameEntity;
import ro.uaic.info.java.lab11.rest_api.util.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class GameRepository {
    private final EntityManager em;

    public GameRepository(PersistenceUtil persistenceUtil) {
        this.em = persistenceUtil.getEmf().createEntityManager();
    }

    public int addGame(GameEntity gameEntity) {
        em.getTransaction().begin();
        em.persist(gameEntity);
        em.flush();
        int id = gameEntity.getId();
        em.getTransaction().commit();
        return id;
    }

    public List<GameEntity> getAllGames() {
        return em.createNamedQuery("Games.getAllGames", GameEntity.class)
                .getResultList();
    }

    public void deleteGame(int gameId) {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM GameEntity g WHERE g.id = :id")
                .setParameter("id", gameId)
                .executeUpdate();
        em.getTransaction().commit();
    }

    public GameEntity findGameById(int gameId) {
        return em.find(GameEntity.class, gameId);
    }
}
