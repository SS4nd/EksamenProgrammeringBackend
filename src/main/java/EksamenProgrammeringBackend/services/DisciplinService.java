package EksamenProgrammeringBackend.services;

import EksamenProgrammeringBackend.models.Disciplin;
import EksamenProgrammeringBackend.repositories.DisciplinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinService {

    @Autowired
    private DisciplinRepository disciplinRepository;

    public List<Disciplin> getAllDiscipliner() {
        return disciplinRepository.findAll();
    }

    public Optional<Disciplin> getDisciplinById(Long id) {
        return disciplinRepository.findById(id);
    }

    public Disciplin saveDisciplin(Disciplin disciplin) {
        return disciplinRepository.save(disciplin);
    }

    public void deleteDisciplinById(Long id) {
        disciplinRepository.deleteById(id);
    }

    public Disciplin createDisciplin(Disciplin disciplin) {
        return disciplinRepository.save(disciplin);
    }

    // Add more methods as needed based on your application's requirements
}
