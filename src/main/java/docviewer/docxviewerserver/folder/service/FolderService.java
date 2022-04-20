package docviewer.docxviewerserver.folder.service;

import docviewer.docxviewerserver.core.service.CoreAbstractService;
import docviewer.docxviewerserver.folder.entity.FolderEntity;
import docviewer.docxviewerserver.folder.model.FolderDto;
import docviewer.docxviewerserver.folder.service.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FolderService extends CoreAbstractService<FolderDto, FolderEntity> {

    @Autowired
    private FolderRepository folderRepository;

    @Override
    protected FolderEntity initNewEntity() {
        return new FolderEntity();
    }

    @Override
    protected FolderDto entityToDTO(FolderEntity entity) {
        FolderDto dto = new FolderDto();
        dto.setId(entity.getId());
        dto.setFolderName(entity.getFolderName());
        dto.setFolderUrl(entity.getFolderUrl());
        return dto;
    }

    @Override
    protected void populateDTODataToEntity(FolderDto dto, FolderEntity entity) {
        entity.setFolderName(dto.getFolderName());
        entity.setFolderUrl(dto.getFolderUrl());
    }
}
