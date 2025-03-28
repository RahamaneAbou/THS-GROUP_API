package com.THS_GROUP.Services;

import com.THS_GROUP.Entyties.Formation;
import com.THS_GROUP.DTO.FormationDTO;
import org.springframework.stereotype.Service;

@Service
public class FormationServiceDTO {

    public FormationDTO mapToDto(Formation formation) {
        if (formation == null) return null;

        FormationDTO dto = new FormationDTO();
        dto.setId(formation.getId());
        dto.setNom(formation.getNom());
        dto.setDescription(formation.getDescription());
        dto.setPointsCles(formation.getPointsCles());
        dto.setDuree(formation.getDuree());
        dto.setPlacesDisponibles(formation.getPlacesDisponibles());

        return dto;
    }
}