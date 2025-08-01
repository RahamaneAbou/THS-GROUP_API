package com.THS_GROUP.Controller;

import com.THS_GROUP.Entyties.Etudiant;
import com.THS_GROUP.Services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/THS-GROUP/etudiants")
@CrossOrigin(
        origins = {"https://ths-group-front-end.onrender.com","http://localhost:5173", "http://127.0.0.1:5173", "https://ton-site.com"},
        allowCredentials = "true"
)
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    // Récupérer tous les étudiants
    @GetMapping
    public ResponseEntity<List<Etudiant>> getAllEtudiants() {
        try {
            List<Etudiant> etudiants = etudiantService.findAll();
            System.out.println("La liste de tous les étudiants a été demandée et chargée");
            return ResponseEntity.ok(etudiants);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Récupérer un étudiant par ID
    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable("id") Long id) {
        try {
            System.out.println("Un étudiant a été demandé via un ID");
            Optional<Etudiant> etudiant = etudiantService.findById(id);
            return etudiant.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Récupérer un étudiant par numéro matricule
    @GetMapping("/getEtudiantByNumMatricule/{numMatricule}")
    public ResponseEntity<Etudiant> getEtudiantByNumMatricule(@PathVariable("numMatricule") String numMatricule) {
        try {
            System.out.println("Un étudiant a été demandé via un numéro matricule");
            Optional<Etudiant> etudiant = etudiantService.findByNumMatricule(numMatricule);
            return etudiant.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Créer ou mettre à jour un étudiant
    @PostMapping
    public ResponseEntity<Etudiant> createOrUpdateEtudiant(@RequestBody Etudiant etudiant) {
        try {
            System.out.println("Création ou mise à jour d'un étudiant");
            Etudiant savedEtudiant = etudiantService.save(etudiant);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEtudiant);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Mettre à jour un étudiant
    @PutMapping("/{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable("id") Long id, @RequestBody Etudiant etudiant) {
        try {
            System.out.println("Mise à jour d'un étudiant demandée");
            etudiant.setId(id);
            Etudiant etudiantMisAJour = etudiantService.save(etudiant);
            return ResponseEntity.ok(etudiantMisAJour);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Supprimer un étudiant
    @DeleteMapping("/etudiantDelete/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable("id") Long id) {
        try {
            System.out.println("La suppression d'un étudiant a été demandée");
            etudiantService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Récupérer les étudiants par formation
    @GetMapping("/formation/{formationId}")
    public ResponseEntity<List<Etudiant>> findEtudiantsByFormationId(@PathVariable("formationId") Long formationId) {
        try {
            System.out.println("Recherche des étudiants par ID de formation : " + formationId);
            List<Etudiant> etudiants = etudiantService.findEtudiantsByFormationId(formationId);
            return ResponseEntity.ok(etudiants);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Récupérer les étudiants par statut
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Etudiant>> getEtudiantsByStatus(@PathVariable("status") String status) {
        try {
            System.out.println("Recherche d'étudiants par statut : " + status);
            List<Etudiant> etudiants = etudiantService.findByStatus(status);
            return ResponseEntity.ok(etudiants);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}