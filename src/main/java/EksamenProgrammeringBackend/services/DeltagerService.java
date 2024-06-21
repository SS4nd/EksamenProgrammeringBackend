package EksamenProgrammeringBackend.services;

import EksamenProgrammeringBackend.models.Deltager;
import EksamenProgrammeringBackend.models.Disciplin;
import EksamenProgrammeringBackend.repositories.DeltagerRepository;
import EksamenProgrammeringBackend.repositories.DisciplinRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class DeltagerService {
    @Autowired

    private EntityManager entityManager;


    private final DeltagerRepository deltagerRepository;
    private final DisciplinRepository disciplinRepository;

    @Autowired
    public DeltagerService(DeltagerRepository deltagerRepository, DisciplinRepository disciplinRepository) {
        this.deltagerRepository = deltagerRepository;
        this.disciplinRepository = disciplinRepository;
    }

    public List<Deltager> getAllDeltagere() {
        return deltagerRepository.findAll();
    }

    public Optional<Deltager> getDeltagerById(Long deltagerId) {
        return deltagerRepository.findById(deltagerId);
    }

    public Deltager createDeltager(Deltager deltager) {
        Set<Disciplin> discipliner = new HashSet<>();
        for (Disciplin disciplin : deltager.getDiscipliner()) {
            Optional<Disciplin> existingDisciplinOptional = Optional.ofNullable(disciplinRepository.findByNavn(disciplin.getNavn()));
            if (existingDisciplinOptional.isPresent()) {
                discipliner.add(existingDisciplinOptional.get());
            } else {
                throw new RuntimeException("Disciplin not found: " + disciplin.getNavn());
            }
        }

        deltager.setDiscipliner(discipliner);
        return deltagerRepository.save(deltager);
    }
    @Transactional
    public Deltager addDisciplinToDeltager(Long deltagerId, Long disciplinId) {
        String nativeQuery = "INSERT INTO deltager_disciplin (deltager_id, disciplin_id) " +
                "VALUES (?, ?)";

        entityManager.createNativeQuery(nativeQuery)
                .setParameter(1, deltagerId)
                .setParameter(2, disciplinId)
                .executeUpdate();

        // Fetch and return the updated Deltager entity
        return deltagerRepository.findById(deltagerId)
                .orElseThrow(() -> new RuntimeException("Deltager not found"));
    }



    public Deltager updateDeltager(Long deltagerId, Deltager updatedDeltager) {
        Optional<Deltager> existingDeltagerOptional = deltagerRepository.findById(deltagerId);
        if (existingDeltagerOptional.isPresent()) {
            updatedDeltager.setDeltagerID(deltagerId);
            return deltagerRepository.save(updatedDeltager);
        } else {
            throw new RuntimeException("Deltager not found");
        }
    }


    @Transactional
    public void deleteDeltager(Long deltagerId) {
        Optional<Deltager> deltagerOptional = deltagerRepository.findById(deltagerId);

        if (deltagerOptional.isPresent()) {
            Deltager deltager = deltagerOptional.get();

            // Remove all associations from deltager_disciplin table
            deltager.getDiscipliner().clear();

            // Now delete the deltager
            deltagerRepository.delete(deltager);
        } else {
            throw new RuntimeException("Deltager not found with ID: " + deltagerId);
        }
    }

}
