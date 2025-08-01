package com.THS_GROUP.Entyties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enseignants_thsgroup")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Enseignant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "numero_telephone")
    private String numeroTelephone;

    @Column(name = "num_matricule", unique = true)
    private String numMatricule;

    // Relations avec Formation (responsable)
    @OneToMany(mappedBy = "responsable", fetch = FetchType.LAZY)
    @JsonBackReference("enseignant-formations-responsable")
    private List<Formation> formationsResponsable;

    // Relations avec Formation (enseignant)
    @OneToMany(mappedBy = "enseignantId", fetch = FetchType.LAZY)
    @JsonBackReference("enseignant-formations")
    private List<Formation> formations;

    // Ne pas inclure la relation bidirectionnelle avec Cours pour éviter les boucles
    // Utiliser des requêtes séparées si nécessaire
    @Transient
    private List<Cours> coursList;

    @PrePersist
    public void generateMatricule() {
        if (this.numMatricule == null) {
            this.numMatricule = "ENS" + System.currentTimeMillis();
        }
    }
}
