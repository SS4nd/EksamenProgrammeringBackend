package ProgrammeringsEksamenAPI;

import EksamenProgrammeringBackend.models.Deltager;
import EksamenProgrammeringBackend.models.Disciplin;
import EksamenProgrammeringBackend.models.Resultat;
import EksamenProgrammeringBackend.repositories.DeltagerRepository;
import EksamenProgrammeringBackend.repositories.DisciplinRepository;
import EksamenProgrammeringBackend.repositories.ResultatRepository;
import EksamenProgrammeringBackend.services.DeltagerService;
import EksamenProgrammeringBackend.services.DisciplinService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ProgrammeringsEksamenAPITests {

    @Autowired
    private DeltagerService deltagerService;

    @Autowired
    private DeltagerRepository deltagerRepository;

    @Autowired
    private DisciplinService disciplinService;

    @Autowired
    private DisciplinRepository disciplinRepository;

    @Autowired
    private ResultatRepository resultatRepository;

    @Test
    public void testCreateDeltager() {
        // Create Deltager
        Deltager deltager = new Deltager();
        deltager.setNavn("Alice");
        deltager.setGender("Female");
        deltager.setAlder(25);
        deltager.setKlub("Running Club");

        // Save Deltager using service
        deltager = deltagerService.createDeltager(deltager);

        // Assertions
        assertThat(deltager.getDeltagerID()).isNotNull();

        Deltager savedDeltager = deltagerRepository.findById(deltager.getDeltagerID()).orElse(null);
        assertThat(savedDeltager).isNotNull();
        assertThat(savedDeltager.getNavn()).isEqualTo("Alice");
        assertThat(savedDeltager.getGender()).isEqualTo("Female");
        assertThat(savedDeltager.getAlder()).isEqualTo(25);
        assertThat(savedDeltager.getKlub()).isEqualTo("Running Club");
    }

    @Test
    public void testCreateDisciplin() {
        // Create Disciplin
        Disciplin disciplin = new Disciplin();
        disciplin.setNavn("100-meterløb");
        disciplin.setResultattype("Time");

        // Save Disciplin using service
        disciplin = disciplinService.createDisciplin(disciplin);

        // Assertions
        assertThat(disciplin.getDisciplinID()).isNotNull();

        Disciplin savedDisciplin = disciplinRepository.findById(disciplin.getDisciplinID()).orElse(null);
        assertThat(savedDisciplin).isNotNull();
        assertThat(savedDisciplin.getNavn()).isEqualTo("100-meterløb");
        assertThat(savedDisciplin.getResultattype()).isEqualTo("Time");
    }

    @Test
    public void testCreateResultat() {
        Deltager deltager = new Deltager();
        deltager.setNavn("Alice");
        deltager.setGender("Female");
        deltager.setAlder(25);
        deltager.setKlub("Running Club");
        deltager = deltagerService.createDeltager(deltager);

        // Create Disciplin
        Disciplin disciplin = new Disciplin();
        disciplin.setNavn("200-meterløb");
        disciplin.setResultattype("Time");
        disciplin = disciplinService.createDisciplin(disciplin);

        // Create Resultat
        Resultat resultat = new Resultat();
        resultat.setResultattype("Time");
        resultat.setDato(new Date()); // Use the current date
        resultat.setResultatværdi("25 sek");
        resultat.setDeltager(deltager);
        resultat.setDisciplin(disciplin);
        resultat = resultatRepository.save(resultat);

        // Assertions
        assertThat(resultat.getResultatID()).isNotNull();

        Resultat savedResultat = resultatRepository.findById(resultat.getResultatID()).orElse(null);
        assertThat(savedResultat).isNotNull();
        assertThat(savedResultat.getResultatværdi()).isEqualTo("25 sek");
        assertThat(savedResultat.getDeltager().getNavn()).isEqualTo("Alice");
        assertThat(savedResultat.getDisciplin().getNavn()).isEqualTo("200-meterløb");
    }
}
