package EksamenProgrammeringBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "disciplin")
@Getter @Setter
public class Disciplin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DisciplinID")
    private Long disciplinID;

    @Column(name = "Navn", nullable = false)
    private String navn;

    @Column(name = "Resultattype", nullable = false, length = 10)
    private String resultattype; // 'cm' or 'sek'

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "deltager_disciplin",
            joinColumns = @JoinColumn(name = "disciplin_id"),
            inverseJoinColumns = @JoinColumn(name = "deltager_id"))
    @JsonIgnore // or @JsonBackReference (with @JsonManagedReference on Deltager.discipliner)
    private Set<Deltager> deltagere = new HashSet<>();

    // Constructors
    public Disciplin() {
    }

    public Disciplin(String navn, String resultattype) {
        this.navn = navn;
        this.resultattype = resultattype;
    }
}
