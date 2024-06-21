package EksamenProgrammeringBackend.controllers;

import EksamenProgrammeringBackend.models.Deltager;
import EksamenProgrammeringBackend.models.Disciplin;
import EksamenProgrammeringBackend.services.DeltagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/deltager")
public class DeltagerController {

    private final DeltagerService deltagerService;

    @Autowired
    public DeltagerController(DeltagerService deltagerService) {
        this.deltagerService = deltagerService;
    }

    @GetMapping
    public ResponseEntity<List<Deltager>> getAllDeltagere() {
        List<Deltager> deltagerList = deltagerService.getAllDeltagere();
        return new ResponseEntity<>(deltagerList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Deltager> createDeltager(@RequestBody Deltager deltager) {
        Deltager createdDeltager = deltagerService.createDeltager(deltager);
        return ResponseEntity.ok(createdDeltager);
    }

    @PutMapping("/{deltagerId}/{disciplinId}")
    public ResponseEntity<Deltager> addDisciplinToDeltager(@PathVariable Long deltagerId, @PathVariable Long disciplinId) {
        try {
            Deltager updatedDeltager = deltagerService.addDisciplinToDeltager(deltagerId, disciplinId);
            return ResponseEntity.ok(updatedDeltager);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{deltagerId}")
    public ResponseEntity<Deltager> getDeltagerById(@PathVariable Long deltagerId) {
        Optional<Deltager> deltagerOptional = deltagerService.getDeltagerById(deltagerId);
        return deltagerOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{deltagerId}")
    public ResponseEntity<Deltager> updateDeltager(@PathVariable Long deltagerId, @RequestBody Deltager updatedDeltager) {
        try {
            Deltager updated = deltagerService.updateDeltager(deltagerId, updatedDeltager);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{deltagerId}/disciplines")
    public ResponseEntity<Set<Disciplin>> getDisciplinesByDeltagerId(@PathVariable Long deltagerId) {
        Optional<Deltager> deltagerOptional = deltagerService.getDeltagerById(deltagerId);
        if (deltagerOptional.isPresent()) {
            Set<Disciplin> disciplines = deltagerOptional.get().getDiscipliner();
            return ResponseEntity.ok(disciplines);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{deltagerId}")
    public ResponseEntity<Void> deleteDeltager(@PathVariable Long deltagerId) {
        deltagerService.deleteDeltager(deltagerId);
        return ResponseEntity.ok().build();
    }
}
