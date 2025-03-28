package com.THS_GROUP.DTO;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormationDTO {
    private Long id;
    private String nom;
    private EnseignantDTO responsable; // Référence simplifiée au responsable
    private String description;
    private List<String> pointsCles;
    private String duree;
    private int placesDisponibles;
    private List<CoursDTO> coursList; // Liste des cours sous forme de DTOs
}