package it.tino.restmovieapp;

import it.tino.restmovieapp.error.MovieAppException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.dynamic.sql.SortSpecification;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

/**
 * Basic CRUD manager to interact with some DAOs.
 * @param <D> The domain model type.
 * @param <E> The database entity model type.
 * @param <ID> The primary key type.
 */
public class SimpleManager<D, E, ID> {

    protected static final Logger logger = LogManager.getLogger();

    private final SqlSessionFactory sqlSessionFactory;
    private final ObjectMapper<D, E> objectMapper;

    private final InsertFunction<E> onInsert;
    private final UpdateFunction<E> onUpdate;
    private final SelectFunction<E> onSelect;
    private final CountFunction onCount;
    private final SelectByIdFunction<E, ID> onSelectById;
    private final DeleteFunction<ID> onDelete;
    private final BiFunction<String, String, SortSpecification> onGetSortSpecification;

    public SimpleManager(
            SqlSessionFactory sqlSessionFactory,
            ObjectMapper<D, E> objectMapper,
            InsertFunction<E> onInsert,
            UpdateFunction<E> onUpdate,
            SelectFunction<E> onSelect,
            CountFunction onCount,
            SelectByIdFunction<E, ID> onSelectById,
            DeleteFunction<ID> onDelete,
            BiFunction<String, String, SortSpecification> onGetSortSpecification
    ) {
        this.sqlSessionFactory = sqlSessionFactory;
        this.objectMapper = objectMapper;

        this.onInsert = onInsert;
        this.onUpdate = onUpdate;

        this.onSelect = onSelect;
        this.onCount = onCount;
        this.onSelectById = onSelectById;
        this.onDelete = onDelete;

        this.onGetSortSpecification = onGetSortSpecification;
    }

    public SimpleManager(
            SqlSessionFactory sqlSessionFactory,
            ObjectMapper<D, E> objectMapper,
            InsertFunction<E> onInsert,
            UpdateFunction<E> onUpdate,
            SelectFunction<E> onSelect,
            SelectByIdFunction<E, ID> onSelectById,
            DeleteFunction<ID> onDelete
    ) {
        this.sqlSessionFactory = sqlSessionFactory;
        this.objectMapper = objectMapper;

        this.onInsert = onInsert;
        this.onUpdate = onUpdate;

        this.onSelect = onSelect;
        this.onCount = (s, s2) -> {
            throw new UnsupportedOperationException("Backend pagination not fully implemented!");
        };
        this.onSelectById = onSelectById;
        this.onDelete = onDelete;

        this.onGetSortSpecification = (s, s2) -> {
            throw new UnsupportedOperationException("Backend pagination not fully implemented!");
        };
    }

    public D insert(D entity) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            E dbEntity = objectMapper.domainToTarget(entity);
            int affectedRows = onInsert.apply(sqlSession, dbEntity);

            if (affectedRows == 1) {
                D domainEntity = objectMapper.sourceToDomain(dbEntity);
                sqlSession.commit();

                return domainEntity;
            }
            throw new MovieAppException("No affected rows on insert");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new MovieAppException(e);
        }
    }

    public D update(D entity) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            E dbEntity = objectMapper.domainToTarget(entity);
            int affectedRows = onUpdate.apply(sqlSession, dbEntity);

            if (affectedRows == 1) {
                D domainEntity = objectMapper.sourceToDomain(dbEntity);
                sqlSession.commit();

                return domainEntity;
            }
            throw new MovieAppException("No affected rows on update");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new MovieAppException(e);
        }
    }

    public List<D> selectAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            List<E> dbEntities = onSelect.apply(sqlSession, SelectDSLCompleter.allRows());
            return objectMapper.sourceToDomain(dbEntities);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new MovieAppException(e);
        }
    }

    public PaginatedResponse<D> selectPaginated(
        int offset,
        int size,
        String sortField,
        String sortDirection
    ) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SortSpecification sortSpecification = onGetSortSpecification.apply(sortField, sortDirection);
            List<E> dbEntities = onSelect.apply(sqlSession, c -> c
                    .orderBy(sortSpecification)
                    .limit(size)
                    .offset(offset)
            );
            List<D> domainEntities = objectMapper.sourceToDomain(dbEntities);

            long totalCount = onCount.apply(sqlSession, CountDSLCompleter.allRows());
            return new PaginatedResponse<>(domainEntities, totalCount);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new MovieAppException(e);
        }
    }

    public D selectById(ID id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            Optional<E> optionalReviewDb = onSelectById.apply(sqlSession, id);
            return optionalReviewDb.map(objectMapper::sourceToDomain).orElse(null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new MovieAppException(e);
        }
    }

    public List<D> selectByCriteria(SelectDSLCompleter selectDSLCompleter) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            List<E> dbEntities = onSelect.apply(sqlSession, selectDSLCompleter);
            return objectMapper.sourceToDomain(dbEntities);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new MovieAppException(e);
        }
    }

    /**
     * selectDSLCompleter and countDSLCompleter have to contain the same
     * lambda expression, but saved with different interfaces, because I could
     * not find a suitable common interface so that they could be just one parameter.
     */
    public PaginatedResponse<D> selectPaginatedByCriteria(
        SelectDSLCompleter selectDSLCompleter,
        CountDSLCompleter countDSLCompleter,
        int offset,
        int size,
        String sortField,
        String sortDirection
    ) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SortSpecification sortSpecification = onGetSortSpecification.apply(sortField, sortDirection);
            List<E> dbEntities = onSelect.apply(sqlSession, c -> {
                selectDSLCompleter.apply(c);
                c.orderBy(sortSpecification);
                c.limit(size).offset(offset);
                return c;
            });
            List<D> domainEntities = objectMapper.sourceToDomain(dbEntities);

            long totalCount = onCount.apply(sqlSession, countDSLCompleter);
            return new PaginatedResponse<>(domainEntities, totalCount);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new MovieAppException(e);
        }
    }

    public boolean delete(ID id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            int affectedRows = onDelete.apply(sqlSession, id);
            return affectedRows == 1;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public interface InsertFunction<K> {
        Integer apply(SqlSession sqlSession, K key);
    }

    public interface UpdateFunction<K> {
        Integer apply(SqlSession sqlSession, K key);
    }

    public interface SelectFunction<K> {
        List<K> apply(SqlSession sqlSession, SelectDSLCompleter key);
    }

    public interface CountFunction {
        long apply(SqlSession sqlSession, CountDSLCompleter key);
    }

    public interface SelectByIdFunction<K, ID> {
        Optional<K> apply(SqlSession sqlSession, ID id);
    }

    public interface DeleteFunction<ID> {
        Integer apply(SqlSession sqlSession, ID id);
    }
}
