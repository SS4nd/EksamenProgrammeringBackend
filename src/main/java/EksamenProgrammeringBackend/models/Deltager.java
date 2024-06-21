package EksamenProgrammeringBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "deltager")
@Getter @Setter
public class Deltager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DeltagerID")
    private Long deltagerID;

    @Column(name = "Navn", nullable = false)
    private String navn;

    @Column(name = "Gender", length = 10)
    private String gender;

    @Column(name = "Alder")
    private Integer alder;

    @Column(name = "Klub", length = 255)
    private String klub;

    @ManyToMany(mappedBy = "deltagere", cascade = CascadeType.ALL)
    @JsonIgnore // or @JsonManagedReference (with @JsonBackReference on Disciplin.deltagere)
    private Set<Disciplin> discipliner = new HashSet<>();

    // Constructors
    public Deltager() {
    }

    @Override
    public String toString() {
        return "Deltager{" +
                "deltagerID=" + deltagerID +
                ", navn='" + navn + '\'' +
                ", gender='" + gender + '\'' +
                ", alder=" + alder +
                ", klub='" + klub + '\'' +
                '}';
    }


    public Deltager(String navn, String gender, Integer alder, String klub) {
        this.navn = navn;
        this.gender = gender;
        this.alder = alder;
        this.klub = klub;
    }

    public Long getId() {
        return deltagerID;
    }
}
