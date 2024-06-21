package EksamenProgrammeringBackend.repositories;

import EksamenProgrammeringBackend.models.Disciplin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinRepository extends JpaRepository<Disciplin, Long> {
    Disciplin findByNavn(String navn);
}
