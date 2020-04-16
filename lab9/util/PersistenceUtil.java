package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {
    private static final PersistenceUtil instance = new PersistenceUtil();
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MusicAlbumsPU");

    private PersistenceUtil() {
    }

    public static PersistenceUtil getInstance() {
        return instance;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void closeEmf() {
        emf.close();
    }
}
