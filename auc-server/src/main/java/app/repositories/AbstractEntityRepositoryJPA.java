package app.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public abstract class AbstractEntityRepositoryJPA<E extends Identifiable> implements EntityRepository<E> {

    @PersistenceContext
    protected EntityManager entityManager;
    private final Class<E> entityClass;

    public AbstractEntityRepositoryJPA(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public List<E> findByQuery(String jpqlName, Object... params) {
        TypedQuery<E> query = entityManager.createNamedQuery(jpqlName, entityClass);

        int i = 1;

        for (Object object : params) {
            query.setParameter(i, object);

            i++;
        }

        return query.getResultList();
    }
}
