package util;

import entity.AbstractEntity;
import repo.AbstractRepository;

public abstract class AbstractFactory<E extends AbstractEntity> {
    Database database;
    PersistenceUtil persistenceUtil;

    public AbstractFactory(Database database, PersistenceUtil persistenceUtil) {
        this.database = database;
        this.persistenceUtil = persistenceUtil;
    }

    public abstract AbstractRepository<E> create(String implementation);
}
