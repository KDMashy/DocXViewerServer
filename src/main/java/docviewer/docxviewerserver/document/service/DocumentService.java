package docviewer.docxviewerserver.document.service;

import docviewer.docxviewerserver.core.entity.CoreEntity;
import docviewer.docxviewerserver.core.service.CoreAbstractService;
import docviewer.docxviewerserver.document.entity.DocumentEntity;
import docviewer.docxviewerserver.document.model.DocumentDto;
import docviewer.docxviewerserver.document.service.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService extends CoreAbstractService<DocumentDto, DocumentEntity> {

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    protected DocumentEntity initNewEntity() {
        return new DocumentEntity();
    }

    @Override
    protected DocumentDto entityToDTO(DocumentEntity entity) {
        DocumentDto dto = new DocumentDto();
        dto.setId(entity.getId());
        dto.setDocumentName(entity.getDocumentName());
        dto.setMimeType(entity.getMimeType());
        dto.setDocumentUrl(entity.getDocumentUrl());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    @Override
    protected void populateDTODataToEntity(DocumentDto dto, DocumentEntity entity) {
        entity.setDocumentName(dto.getDocumentName());
        entity.setMimeType(dto.getMimeType());
        entity.setDocumentUrl(dto.getDocumentUrl());
        entity.setDescription(dto.getDescription());
    }
}
