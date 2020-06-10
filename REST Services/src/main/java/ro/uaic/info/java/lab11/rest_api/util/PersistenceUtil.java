package ro.uaic.info.java.lab11.rest_api.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {
    private static final PersistenceUtil instance = new PersistenceUtil();
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("GomokuPU");

    private PersistenceUtil() {
    }

    public static PersistenceUtil getInstance() {
        return instance;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }
}
