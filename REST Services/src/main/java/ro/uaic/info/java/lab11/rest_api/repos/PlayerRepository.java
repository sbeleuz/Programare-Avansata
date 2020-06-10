package ro.uaic.info.java.lab11.rest_api.repos;

import ro.uaic.info.java.lab11.rest_api.entities.PlayerEntity;
import ro.uaic.info.java.lab11.rest_api.util.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class PlayerRepository {
    private final EntityManager em;

    public PlayerRepository(PersistenceUtil persistenceUtil) {
        this.em = persistenceUtil.getEmf().createEntityManager();
    }

    public int addPlayer(PlayerEntity playerEntity) {
        em.getTransaction().begin();
        em.persist(playerEntity);
        em.flush();
        int id = playerEntity.getId();
        em.getTransaction().commit();
        return id;
    }

    public List<PlayerEntity> getAllPlayers() {
        return em.createNamedQuery("Players.getAllPlayers", PlayerEntity.class)
                .getResultList();
    }

    public void changePlayerName(int playerId, String name) {
        em.getTransaction().begin();
        em.createQuery("UPDATE PlayerEntity p set p.name = :name WHERE p.id = :id")
                .setParameter("name", name)
                .setParameter("id", playerId)
                .executeUpdate();
        em.getTransaction().commit();
    }

    public void deletePlayer(int playerId) {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM PlayerEntity p WHERE p.id = :id")
                .setParameter("id", playerId)
                .executeUpdate();
        em.getTransaction().commit();
    }

    public PlayerEntity findPlayerById(int playerId) {
        return em.find(PlayerEntity.class, playerId);
    }

    public List<PlayerEntity> getPlayersByGameId(int gameId) {
        return em.createQuery("SELECT p FROM PlayerEntity p, GameEntity g WHERE p.gameId = g.id AND g.id = :id", PlayerEntity.class)
                .setParameter("id", gameId)
                .getResultList();
    }
}