package docviewer.docxviewerserver.document.controller;

import docviewer.docxviewerserver.document.model.DocumentDto;
import docviewer.docxviewerserver.document.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("document")
@RestController
public class DocumentCrudRestController {
    @Autowired
    private DocumentService documentService;

    @GetMapping()
    public ResponseEntity<List<DocumentDto>> findAll() {
        return ResponseEntity.ok(documentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(documentService.findById(id));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nem található elem " + exception);
        }
    }

    @PostMapping()
    public ResponseEntity<DocumentDto> save(@RequestBody DocumentDto data) {
        return ResponseEntity.ok(documentService.save(data));
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody DocumentDto data) {
        try {
            if (data.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nem megfelelő hívás, az ID kötelező");
            }
            return ResponseEntity.ok(documentService.update(data));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nem található elem " + exception);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            documentService.delete(id);
            return ResponseEntity.ok("OK");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nem található elem " + exception);
        }
    }
}
