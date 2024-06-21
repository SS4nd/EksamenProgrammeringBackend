package EksamenProgrammeringBackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeltagerDTO {

    private Long deltagerID;
    private String navn;
    private String gender;
    private Integer alder;
    private String klub;

    // No-args constructor
    public DeltagerDTO() {
    }

    // Constructor with fields
    public DeltagerDTO(Long deltagerID, String navn, String gender, Integer alder, String klub) {
        this.deltagerID = deltagerID;
        this.navn = navn;
        this.gender = gender;
        this.alder = alder;
        this.klub = klub;
    }
}
