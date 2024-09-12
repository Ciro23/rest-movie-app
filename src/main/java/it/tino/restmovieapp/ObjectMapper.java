package it.tino.restmovieapp;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * This mapper is used to convert objects from their domain
 * model to another form.
 * @param <T> The domain entity type
 * @param <K> The other entity type (JSON, database...)
 */
public interface ObjectMapper<T, K> {

    default T sourceToDomain(K source) {
        List<T> domainEntities = sourceToDomain(Collections.singleton(source));
        return domainEntities.getFirst();
    }

    List<T> sourceToDomain(Collection<K> source);

    default K domainToTarget(T domainEntity) {
        List<K> dbEntities = domainToTarget(Collections.singleton(domainEntity));
        return dbEntities.getFirst();
    }

    List<K> domainToTarget(Collection<T> domainEntities);
}
