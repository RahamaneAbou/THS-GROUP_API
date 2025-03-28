package com.THS_GROUP.Entyties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "formations")
public class Formation {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false)
	    private String nom;
	    
	    @ManyToOne
	    @JoinColumn(name = "responsable_id", nullable = true)
	    private Enseignant responsable; // Responsable de la filière

	    @Column(nullable = true, columnDefinition = "TEXT")
	    private String description;
	    
	    @ManyToOne
	    @JoinColumn(name = "enseignantId", nullable = true)
	    private Enseignant enseignantId;
	    
	    @ElementCollection
	    @CollectionTable(name = "formation_points_cles", joinColumns = @JoinColumn(name = "formation_id"))
	    @Column(name = "point_cle")
	    private List<String> pointsCles;

	    @Column(nullable = false)
	    private String duree;

	    @Column(nullable = false)
	    private int placesDisponibles;
	    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL, orphanRemoval = true)
	    @JsonIgnoreProperties("formation") 
	    private List<Cours> coursList;
}
