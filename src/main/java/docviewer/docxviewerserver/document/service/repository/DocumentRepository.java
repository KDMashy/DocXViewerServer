package docviewer.docxviewerserver.document.service.repository;

import docviewer.docxviewerserver.core.repository.CoreAbstractRepositoryImpl;
import docviewer.docxviewerserver.document.entity.DocumentEntity;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepository extends CoreAbstractRepositoryImpl<DocumentEntity> {

    @Override
    protected Class<DocumentEntity> getManagedClass() { return DocumentEntity.class; }
}
