package com.THS_GROUP.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {
    private Long id;
    private Long etudiantId; // ID de l'étudiant
    private Long coursId;    // ID du cours
    private double valeur;   // Valeur de la note
    private String dateAttribution; // Date d'attribution sous forme de String
}