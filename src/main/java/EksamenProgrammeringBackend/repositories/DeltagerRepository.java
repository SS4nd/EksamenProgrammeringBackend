package EksamenProgrammeringBackend.repositories;

import EksamenProgrammeringBackend.models.Deltager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeltagerRepository extends JpaRepository<Deltager, Long> {
}
