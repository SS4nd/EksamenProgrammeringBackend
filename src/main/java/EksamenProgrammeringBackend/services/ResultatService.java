package EksamenProgrammeringBackend.services;

import EksamenProgrammeringBackend.models.Resultat;
import EksamenProgrammeringBackend.repositories.ResultatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultatService {

    @Autowired
    private ResultatRepository resultatRepository;

    public List<Resultat> getAllResultater() {
        return resultatRepository.findAll();
    }

    public List<Resultat> getResultaterByDeltagerId(Long deltagerId) {
        List<Resultat> allResultater = resultatRepository.findAll();
        return allResultater.stream()
                .filter(resultat -> resultat.getDeltager().getId().equals(deltagerId))
                .collect(Collectors.toList());
    }

    public Resultat saveResultat(Resultat resultat) {
        return resultatRepository.save(resultat);
    }

    public void deleteResultatById(Long id) {
        resultatRepository.deleteById(id);
    }


}