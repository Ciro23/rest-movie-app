package it.tino.restmovieapp;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface ObjectMapper<T, K> {

    default T dbToDomain(K dbEntity) {
        List<T> domainEntities = dbToDomain(Collections.singleton(dbEntity));
        return domainEntities.getFirst();
    }

    List<T> dbToDomain(Collection<K> dbEntities);

    default K domainToDb(T domainEntity) {
        List<K> dbEntities = domainToDb(Collections.singleton(domainEntity));
        return dbEntities.getFirst();
    }

    List<K> domainToDb(Collection<T> domainEntities);
}
