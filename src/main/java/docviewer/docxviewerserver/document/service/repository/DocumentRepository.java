package docviewer.docxviewerserver.document.service.repository;

import docviewer.docxviewerserver.core.repository.CoreAbstractRepository;
import docviewer.docxviewerserver.document.entity.DocumentEntity;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepository extends CoreAbstractRepository<DocumentEntity> {

    @Override
    protected Class<DocumentEntity> getManagedClass() { return DocumentEntity.class; }
}
