package com.THS_GROUP.Controller;

import com.THS_GROUP.Entyties.Etudiant;
import com.THS_GROUP.Services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/THS-GROUP/etudiants")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    // Récupérer tous les étudiants
    @GetMapping
    public List<Etudiant> getAllEtudiants() {
    	System.out.println("La liste de tous les etudiant a été demander et charger");
        return etudiantService.findAll();
    }

    // Récupérer un étudiant par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable("id") Long id) {
    	System.out.println("Un etudiant a été demander via un ID");
        return etudiantService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Récupérer un étudiant par son numéro matricule
    @GetMapping("/getEtudiantByNumMatricule/{numMatricule}")
    public ResponseEntity<Etudiant> getEtudiantByNumMatricule(@PathVariable("numMatricule") String numMatricule) {
    	System.out.println("Un etudiant a été demander via un numero matricule");
        return etudiantService.findByNumMatricule(numMatricule)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Créer ou mettre à jour un étudiant
    @PostMapping
    public Etudiant createOrUpdateEtudiant(@RequestBody Etudiant etudiant) {
    	System.out.println("Un etudiant a été ajouté");
        return etudiantService.save(etudiant);
    }

    // Supprimer un étudiant par son ID
    @DeleteMapping("/etudiantDelete/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable("id") Long id) {
        etudiantService.deleteById(id);
        System.out.println("La suppression d'un etudiant a été demander");
        return ResponseEntity.noContent().build();
    }

    // Trouver tous les étudiants d'une formation donnée
    @GetMapping("/formation/{formationId}")
    public ResponseEntity<List<Etudiant>> findEtudiantsByFormationId(@PathVariable("formationId") Long formationId) {
        List<Etudiant> etudiants = etudiantService.findEtudiantsByFormationId(formationId);
        System.out.println("la liste des etudiant a été demander via l'id formation");
        return ResponseEntity.ok(etudiants);
    }
}