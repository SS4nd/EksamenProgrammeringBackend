package EksamenProgrammeringBackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisciplinDTO {

    private Long disciplinID;
    private String navn;
    private String resultattype;

    // No-args constructor
    public DisciplinDTO() {
    }

    // Constructor with fields
    public DisciplinDTO(Long disciplinID, String navn, String resultattype) {
        this.disciplinID = disciplinID;
        this.navn = navn;
        this.resultattype = resultattype;
    }
}
