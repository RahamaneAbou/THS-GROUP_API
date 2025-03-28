package com.THS_GROUP.Services;

import com.THS_GROUP.Entyties.Etudiant;
import com.THS_GROUP.Repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    // Récupérer tous les étudiants
    public List<Etudiant> findAll() {
        return etudiantRepository.findAll();
    }

    // Récupérer un étudiant par son ID
    public Optional<Etudiant> findById(Long id) {
        return etudiantRepository.findById(id);
    }

    // Récupérer un étudiant par son numéro matricule
    public Optional<Etudiant> findByNumMatricule(String numMatricule) {
        return etudiantRepository.findByNumMatricule(numMatricule);
    }

    // Créer ou mettre à jour un étudiant
    public Etudiant save(Etudiant etudiant) {
        if (etudiant == null) {
            throw new IllegalArgumentException("L'étudiant ne peut pas être null.");
        }
        return etudiantRepository.save(etudiant);
    }

    // Supprimer un étudiant par son ID
    public String deleteById(Long id) {
        etudiantRepository.deleteById(id);
        return ("Etudiant supprimé");
    }

    // Trouver tous les étudiants d'une formation donnée
    public List<Etudiant> findEtudiantsByFormationId(Long formationId) {
        return etudiantRepository.findByFormationId(formationId);
    }

    // Rechercher un étudiant par nom et prénom
    public Optional<Etudiant> findByNomAndPrenom(String nom, String prenom) {
        return etudiantRepository.findByNomAndPrenom(nom, prenom);
    }
}