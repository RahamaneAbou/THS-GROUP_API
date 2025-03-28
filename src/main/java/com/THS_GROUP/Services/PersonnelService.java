package com.THS_GROUP.Services;

import com.THS_GROUP.Entyties.Personnels;
import com.THS_GROUP.Repository.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;

    // Récupérer tous les personnels
    public List<Personnels> findAll() {
        return personnelRepository.findAll();
    }

    // Récupérer un personnel par ID
    public Optional<Personnels> findById(Long id) {
        return personnelRepository.findById(id);
    }

    // Sauvegarder un nouveau personnel ou mettre à jour un existant
    public Personnels save(Personnels personnel) {
        if (personnel.getNumMatricule() == null || personnel.getNumMatricule().isEmpty()) {
            throw new IllegalArgumentException("Le numéro matricule est obligatoire.");
        }
        return personnelRepository.save(personnel);
    }

    // Supprimer un personnel par ID
    public void deleteById(Long id) {
        personnelRepository.deleteById(id);
    }

    // Rechercher un personnel par numéro matricule
    public Personnels findByNumMatricule(String numMatricule) {
        return personnelRepository.findByNumMatricule(numMatricule);
    }

    // Rechercher un personnel par email
    public Personnels findByEmail(String email) {
        return personnelRepository.findByEmail(email);
    }
}