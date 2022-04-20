package docviewer.docxviewerserver.folder.controller;

import docviewer.docxviewerserver.document.service.DocumentService;
import docviewer.docxviewerserver.folder.entity.FolderEntity;
import docviewer.docxviewerserver.folder.model.FolderDto;
import docviewer.docxviewerserver.folder.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("folder")
@RestController
public class FolderCrudRestController {
    @Autowired
    private FolderService folderService;
    @Autowired
    private DocumentService documentService;

    @GetMapping()
    public ResponseEntity<List<FolderDto>> findAll() {
        return ResponseEntity.ok(folderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(folderService.findById(id));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nem található elem " + exception);
        }
    }

    @PostMapping()
    public ResponseEntity<FolderDto> save(@RequestBody FolderDto data) {
        return ResponseEntity.ok(folderService.save(data));
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody FolderDto data) {
        try {
            if (data.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nem megfelelő hívás, az ID kötelező");
            }
            return ResponseEntity.ok(folderService.update(data));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nem található elem " + exception);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            FolderEntity entity = folderService.findById(id);
            folderService.deleteFolderWContents(id);
            documentService.deleteDocumentsWFolder(entity.getFolderName());
            return ResponseEntity.ok("OK");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nem található elem " + exception);
        }
    }
}
