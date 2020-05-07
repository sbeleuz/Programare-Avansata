package ro.uaic.info.java.lab11.repos;

import ro.uaic.info.java.lab11.entities.Player;
import ro.uaic.info.java.lab11.util.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class PlayerRepository {
    private final EntityManager em;

    public PlayerRepository(PersistenceUtil persistenceUtil) {
        this.em = persistenceUtil.getEmf().createEntityManager();
    }

    public int addPlayer(Player player) {
        em.getTransaction().begin();
        em.persist(player);
        em.flush();
        int id = player.getId();
        em.getTransaction().commit();
        return id;
    }

    public List<Player> getAllPlayers() {
        return em.createNamedQuery("Players.getAllPlayers", Player.class)
                .getResultList();
    }

    public void changePlayerName(int playerId, String name) {
        em.getTransaction().begin();
        em.createQuery("UPDATE Player p set p.name = :name WHERE p.id = :id")
                .setParameter("name", name)
                .setParameter("id", playerId)
                .executeUpdate();
        em.getTransaction().commit();
    }

    public void deletePlayer(int playerId) {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Player p WHERE p.id = :id")
                .setParameter("id", playerId)
                .executeUpdate();
        em.getTransaction().commit();
    }

    public Player findPlayerById(int playerId) {
        return em.find(Player.class, playerId);
    }
}
