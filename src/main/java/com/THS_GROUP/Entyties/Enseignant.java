package com.THS_GROUP.Entyties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enseignants")
public class Enseignant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom; // Nom complet de l'enseignant
    
    @Column(nullable = true)
    private String prenom; // Nom complet de l'enseignant

    @Column(nullable = false, unique = true)
    private String email; // Email de l'enseignant

    @Column(nullable = false)
    private String numeroTelephone; // Numéro de téléphone de l'enseignant
    
    @Column(nullable=true, unique=true, length=50)
 	private String numMatricule;

    @OneToMany(mappedBy = "enseignantId", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("enseignant") 
    private List<Cours> cours; // Liste des cours enseignés par cet enseignant
    
   // @OneToMany(mappedBy = "enseignantId", cascade = CascadeType.ALL, orphanRemoval = true)
  //  @JsonIgnoreProperties("enseignant") 
   // private List<Formation> formation; // Liste des cours enseignés par cet enseignant

    @Version // Ajoutez ce champ pour gérer la concurrence optimiste
    private Integer version;
}