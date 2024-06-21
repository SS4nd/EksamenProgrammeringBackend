package EksamenProgrammeringBackend.controllers;

import EksamenProgrammeringBackend.models.Resultat;
import EksamenProgrammeringBackend.services.ResultatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/resultater")
public class ResultatController {

    private final ResultatService resultatService;

    @Autowired
    public ResultatController(ResultatService resultatService) {
        this.resultatService = resultatService;
    }

    @GetMapping
    public ResponseEntity<List<Resultat>> getAllResultater() {
        List<Resultat> resultater = resultatService.getAllResultater();
        return new ResponseEntity<>(resultater, HttpStatus.OK);
    }

    @GetMapping("/{id}")  // Ensure the path variable name matches the method parameter
    public ResponseEntity<List<Resultat>> getResultaterByDeltagerId(@PathVariable("id") Long deltagerId) {
        List<Resultat> resultater = resultatService.getResultaterByDeltagerId(deltagerId);
        if (resultater.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultater);
    }

    @PostMapping
    public ResponseEntity<Resultat> createResultat(@RequestBody Resultat resultat) {
        Resultat createdResultat = resultatService.saveResultat(resultat);
        return new ResponseEntity<>(createdResultat, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Resultat> updateResultat(@PathVariable Long id, @RequestBody Resultat updatedResultat) {
        try {
            updatedResultat.setId(id);
            Resultat savedResultat = resultatService.saveResultat(updatedResultat);
            return new ResponseEntity<>(savedResultat, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteResultat(@PathVariable Long id) {
        try {
            resultatService.deleteResultatById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}