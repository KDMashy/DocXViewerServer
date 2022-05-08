package docviewer.docxviewerserver.core.repository;

import docviewer.docxviewerserver.core.entity.CoreEntity;
import docviewer.docxviewerserver.document.entity.DocumentEntity;
import docviewer.docxviewerserver.folder.entity.FolderEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public abstract class CoreAbstractRepository <T extends CoreEntity> {

    @PersistenceContext
    protected EntityManager entityManager;

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

    public void deleteFolderWContents(Long id){
        FolderEntity entity = (FolderEntity) findById(id);
        List<FolderEntity> entities = (List<FolderEntity>) findAll();
        for (FolderEntity found : entities) {
            String[] folderUrl = found.getFolderUrl().split("/");
            for (String part : folderUrl) {
                if (part.equals(entity.getFolderName())){
                    delete(found.getId());
                }
            }
        }
        delete(id);
    }

    public void deleteDocumentsWFolder(String folderName){
        List<DocumentEntity> entities = (List<DocumentEntity>) findAll();
        for (DocumentEntity entity : entities) {
            String[] docUrl = entity.getDocumentUrl().split("/");
            for (String part : docUrl) {
                if (part.equals(folderName)){
                    delete(entity.getId());
                }
            }
        }
    }

    public List<T> findAll() {
        return entityManager.createQuery("select n from " + getManagedClass().getSimpleName() + " n", getManagedClass()).getResultList();
    }

    protected abstract Class<T> getManagedClass();
}
