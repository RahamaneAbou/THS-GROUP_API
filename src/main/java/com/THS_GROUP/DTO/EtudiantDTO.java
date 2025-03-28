package com.THS_GROUP.DTO;

import java.util.Date;
import java.util.List;

import com.THS_GROUP.Entyties.StatusInscription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantDTO {
    private Long id;
    private String numMatricule;
    private String nom;
    private String prenom;
    private int age;
    private String email;
    private String numeroTelephone;
    private FormationDTO formation; // Référence vers la formation associée
    private StatusInscription statusInscription;
    private Date dateInscription;
    private List<NoteDTO> notes; // Liste des notes sous forme de DTOs
}