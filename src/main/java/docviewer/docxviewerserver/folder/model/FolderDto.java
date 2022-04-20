package docviewer.docxviewerserver.folder.model;

import docviewer.docxviewerserver.core.model.CoreDto;

public class FolderDto implements CoreDto {
    private Long id;
    private String folderName;
    private String folderUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderUrl() {
        return folderUrl;
    }

    public void setFolderUrl(String folderUrl) {
        this.folderUrl = folderUrl;
    }
}
