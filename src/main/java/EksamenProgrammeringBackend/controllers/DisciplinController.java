package EksamenProgrammeringBackend.controllers;

import EksamenProgrammeringBackend.models.Disciplin;
import EksamenProgrammeringBackend.services.DisciplinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/discipliner")
public class DisciplinController {

    private final DisciplinService disciplinService;

    @Autowired
    public DisciplinController(DisciplinService disciplinService) {
        this.disciplinService = disciplinService;
    }

    @GetMapping
    public ResponseEntity<List<Disciplin>> getAllDiscipliner() {
        List<Disciplin> discipliner = disciplinService.getAllDiscipliner();
        return new ResponseEntity<>(discipliner, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplin> getDisciplinById(@PathVariable Long id) {
        Optional<Disciplin> disciplin = disciplinService.getDisciplinById(id);
        return disciplin.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Disciplin> createDisciplin(@RequestBody Disciplin disciplin) {
        Disciplin createdDisciplin = disciplinService.saveDisciplin(disciplin);
        return new ResponseEntity<>(createdDisciplin, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplin> updateDisciplin(@PathVariable Long id, @RequestBody Disciplin disciplinDetails) {
        Optional<Disciplin> optionalDisciplin = disciplinService.getDisciplinById(id);
        if (optionalDisciplin.isPresent()) {
            Disciplin disciplin = optionalDisciplin.get();
            disciplin.setNavn(disciplinDetails.getNavn());
            disciplin.setResultattype(disciplinDetails.getResultattype());

            Disciplin updatedDisciplin = disciplinService.saveDisciplin(disciplin);
            return new ResponseEntity<>(updatedDisciplin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDisciplin(@PathVariable Long id) {
        try {
            disciplinService.deleteDisciplinById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
