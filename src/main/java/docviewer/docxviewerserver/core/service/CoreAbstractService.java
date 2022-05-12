package docviewer.docxviewerserver.core.service;

import docviewer.docxviewerserver.core.entity.CoreEntity;
import docviewer.docxviewerserver.core.model.CoreDto;
import docviewer.docxviewerserver.core.repository.CoreAbstractRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

public abstract class CoreAbstractService<D extends CoreDto, T extends CoreEntity> {
    @Autowired
    protected CoreAbstractRepositoryImpl<T> repository;

    public List<D> findAll() {
        return repository.findAll().stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public T findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public void deleteFolderWContents(Long id) {
        repository.deleteFolderWContents(id);
    }

    @Transactional
    public void deleteDocumentsWFolder(String name){
        repository.deleteDocumentsWFolder(name);
    }

    @Transactional
    public D save(D data) {
        T entity = initNewEntity();
        populateDTODataToEntity(data, entity);
        entity = repository.save(entity);
        return entityToDTO(entity);
    }

    @Transactional
    public D update(D data) {
        T entity = repository.findById(data.getId());
        populateDTODataToEntity(data, entity);
        entity = repository.update(entity);
        return entityToDTO(entity);
    }

    protected abstract T initNewEntity();

    protected abstract D entityToDTO(T entity);

    protected abstract void populateDTODataToEntity(D dto, T entity);
}
