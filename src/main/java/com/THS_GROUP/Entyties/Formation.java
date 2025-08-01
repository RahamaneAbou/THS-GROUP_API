package com.THS_GROUP.Entyties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "formations")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Formation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "responsable_id", nullable = true)
	@JsonIgnoreProperties({"formations", "coursList"})
	private Enseignant responsable; // Responsable de la filière

	@Column(nullable = true, columnDefinition = "TEXT")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "enseignantId", nullable = true)
	@JsonIgnoreProperties({"formations", "coursList"})
	private Enseignant enseignantId;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "formation_points_cles", joinColumns = @JoinColumn(name = "formation_id"))
	@Column(name = "point_cle")
	private List<String> pointsCles;

	@Column(nullable = false)
	private String duree;

	@Column(nullable = false)
	private int placesDisponibles;

	// Éviter les boucles infinies avec JsonBackReference
	@OneToMany(mappedBy = "formation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonBackReference("formation-cours")
	private List<Cours> coursList;

	// Relation avec les étudiants
	@OneToMany(mappedBy = "formation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonBackReference("formation-etudiants")
	private List<Etudiant> etudiants;

	// Relation avec les inscriptions
	@OneToMany(mappedBy = "formation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonBackReference("formation-inscriptions")
	private List<Inscription> inscriptions;
}
