package docviewer.docxviewerserver.core.repository;

import docviewer.docxviewerserver.core.entity.CoreEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public abstract class CoreAbstractRepository <T extends CoreEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public T update(T data) {
        return entityManager.merge(data);
    }

    public T findById(Long id) {
        T entity = entityManager.find(getManagedClass(), id);
        if (entity == null) {
            throw new RuntimeException("entity with: " + id + " was not found");
        }
        return entity;
    }

    public void delete(Long id) {
        entityManager.remove((findById(id)));
    }

    public List<T> findAll() {
        return entityManager.createQuery("select n from " + getManagedClass().getSimpleName() + " n", getManagedClass()).getResultList();
    }

    protected abstract Class<T> getManagedClass();
}
