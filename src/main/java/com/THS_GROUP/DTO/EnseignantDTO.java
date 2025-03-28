package com.THS_GROUP.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnseignantDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String numeroTelephone;
    private String numMatricule;
    private List<CoursDTO> cours; // Liste des cours sous forme de DTOs
    private List<FormationDTO> filieresResponsables; // Liste des formations responsables sous forme de DTOs
}