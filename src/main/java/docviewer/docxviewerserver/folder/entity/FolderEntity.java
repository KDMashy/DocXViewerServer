package docviewer.docxviewerserver.folder.entity;

import docviewer.docxviewerserver.core.entity.CoreEntity;

import javax.persistence.*;

@Entity
@Table(name = "folders")
public class FolderEntity  extends CoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "folderName")
    private String folderName;

    @Column(name = "folderUrl")
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
