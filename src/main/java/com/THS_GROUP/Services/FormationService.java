package com.THS_GROUP.Services;

import com.THS_GROUP.Entyties.Formation;
import com.THS_GROUP.Repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FormationService {

    @Autowired
    private FormationRepository formationRepository;

    // Récupérer toutes les formations
    public List<Formation> findAll() {
    	
        return formationRepository.findAll();
    }

    // Récupérer une formation par son ID
    public Optional<Formation> findById(Long id) {
    	
        return formationRepository.findById(id);
    }

    // Créer ou mettre à jour une formation
    public Formation save(Formation formation) {
        if (formation == null) {
            throw new IllegalArgumentException("La formation ne peut pas être null.");
        }
       
        return formationRepository.save(formation);
    }

    // Supprimer une formation par son ID
    public void deleteById(Long id) {
    	
        formationRepository.deleteById(id);
    }

    // Trouver toutes les formations d'un enseignant donné
    public List<Formation> findFormationsByResponsableId(Long responsableId) {
    	
        return formationRepository.findByResponsableId(responsableId);
    }

    // Mettre à jour une formation existante
    public Formation updateFormation(Long id, Formation updatedFormation) {
    	
        return formationRepository.findById(id)
                .map(formation -> {
                    formation.setNom(updatedFormation.getNom());
                    formation.setDescription(updatedFormation.getDescription());
                    formation.setPointsCles(updatedFormation.getPointsCles());
                    formation.setDuree(updatedFormation.getDuree());
                    formation.setPlacesDisponibles(updatedFormation.getPlacesDisponibles());
                    formation.setResponsable(updatedFormation.getResponsable());
                    return formationRepository.save(formation);
                }).orElseThrow(() -> new RuntimeException("Formation non trouvée avec l'ID " + id));
    }
}