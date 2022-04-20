package docviewer.docxviewerserver.document.entity;

import docviewer.docxviewerserver.core.entity.CoreEntity;

import javax.persistence.*;

@Entity
@Table(name = "documents")
public class DocumentEntity extends CoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "documentName")
    private String documentName;

    @Column(name = "mimeType")
    private String mimeType;

    @Column(name = "documentUrl")
    private String documentUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }
}
