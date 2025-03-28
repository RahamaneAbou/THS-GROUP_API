package com.THS_GROUP.Services;

import com.THS_GROUP.Entyties.Cours;
import com.THS_GROUP.DTO.CoursDTO;
import org.springframework.stereotype.Service;

@Service
public class CoursServiceDTO {

    public CoursDTO mapToDto(Cours cours) {
        if (cours == null) return null;

        CoursDTO dto = new CoursDTO();
        dto.setId(cours.getId());
        dto.setNom(cours.getNom());
        dto.setDescription(cours.getDescription());
        dto.setEnseignantId(cours.getEnseignantId());

        return dto;
    }
}