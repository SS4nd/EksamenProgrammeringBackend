package EksamenProgrammeringBackend.models;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Getter
@Setter
@Table(name = "resultat")
public class Resultat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ResultatID")
    private Long resultatID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DeltagerID", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Ignore Hibernate specific properties
    private Deltager deltager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DisciplinID", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Ignore Hibernate specific properties
    private Disciplin disciplin;

    @Column(name = "Resultattype")
    private String resultattype;

    @Temporal(TemporalType.DATE)
    @Column(name = "Dato")
    private Date dato;

    @Column(name = "Resultatværdi")
    private String resultatværdi;

    public Resultat() {
    }
}
