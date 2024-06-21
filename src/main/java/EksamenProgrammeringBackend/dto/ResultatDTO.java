package EksamenProgrammeringBackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ResultatDTO {

    private Long id;
    private String resultatType;
    private String dato;
    private double resultatVærdi;
    private Long deltagerId;
}
