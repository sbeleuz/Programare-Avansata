package util;

import entity.ArtistsEntity;
import repo.AbstractRepository;
import repo.ArtistRepositoryJdbc;
import repo.ArtistRepositoryJpa;

public class ArtistRepositoryFactory extends AbstractFactory<ArtistsEntity> {
    public ArtistRepositoryFactory(Database database, PersistenceUtil persistenceUtil) {
        super(database, persistenceUtil);
    }

    @Override
    public AbstractRepository<ArtistsEntity> create(String implementation) {
        if (implementation.equals("jdbc")) return new ArtistRepositoryJdbc(database);
        else if (implementation.equals("jpa")) return new ArtistRepositoryJpa(persistenceUtil);

        return null;
    }
}
