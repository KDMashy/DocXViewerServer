package docviewer.docxviewerserver.folder.service.repository;

import docviewer.docxviewerserver.core.repository.CoreAbstractRepositoryImpl;
import docviewer.docxviewerserver.folder.entity.FolderEntity;
import org.springframework.stereotype.Repository;

@Repository
public class FolderRepository extends CoreAbstractRepositoryImpl<FolderEntity> {

    @Override
    protected  Class<FolderEntity> getManagedClass() { return FolderEntity.class; }
}
