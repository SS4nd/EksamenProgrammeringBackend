package EksamenProgrammeringBackend.repositories;

import EksamenProgrammeringBackend.models.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultatRepository extends JpaRepository<Resultat, Long> {

    List<Resultat> findByDeltager_DeltagerID(Long deltagerID);

    List<Resultat> findByDeltagerId(Long deltagerId);
}
