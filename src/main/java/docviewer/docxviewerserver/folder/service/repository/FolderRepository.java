package docviewer.docxviewerserver.folder.service.repository;

import com.sun.tools.jconsole.JConsoleContext;
import docviewer.docxviewerserver.core.repository.CoreAbstractRepository;
import docviewer.docxviewerserver.document.entity.DocumentEntity;
import docviewer.docxviewerserver.document.service.repository.DocumentRepository;
import docviewer.docxviewerserver.folder.entity.FolderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FolderRepository extends CoreAbstractRepository<FolderEntity> {

    @Override
    protected  Class<FolderEntity> getManagedClass() { return FolderEntity.class; }
}
