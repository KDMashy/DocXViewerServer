package docviewer.docxviewerserver.core.repository;

import docviewer.docxviewerserver.core.entity.CoreEntity;

import javax.transaction.Transactional;
import java.util.List;

public interface CoreAbstractRepository <T extends CoreEntity>{

    @Transactional
    T save(T entity);

    @Transactional
    T update(T data);

    T findById(Long id);

    T findByName(String username);

    @Transactional
    void delete(Long id);

    @Transactional
    void deleteFolderWContents(Long id);

    @Transactional
    void deleteDocumentsWFolder(String folderName);

    List<T> findAll();
}
