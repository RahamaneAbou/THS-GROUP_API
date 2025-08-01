package com.THS_GROUP.Controller;

import com.THS_GROUP.Entyties.Personnels;
import com.THS_GROUP.Services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/THS-GROUP/personnels")
@CrossOrigin(origins = "*")
public class PersonnelController {

    @Autowired
    private PersonnelService personnelService;

    // Récupérer tous les personnels
    @GetMapping
    public List<Personnels> getAllPersonnels() {
        return personnelService.findAll();
    }

    // Récupérer un personnel par ID
    @GetMapping("/{id}")
    public ResponseEntity<Personnels> getPersonnelById(@PathVariable("id") Long id) {
        return personnelService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Créer ou mettre à jour un personnel
    @PostMapping
    public ResponseEntity<Personnels> createOrUpdatePersonnel(@RequestBody Personnels personnel) {
        try {
            Personnels savedPersonnel = personnelService.save(personnel);
            return ResponseEntity.ok(savedPersonnel);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Supprimer un personnel par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonnel(@PathVariable("id") Long id) {
        personnelService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Rechercher un personnel par numéro matricule
    @GetMapping("/matricule/{numMatricule}")
    public ResponseEntity<Personnels> getPersonnelByMatricule(@PathVariable("numMatricule") String numMatricule) {
        Personnels personnel = personnelService.findByNumMatricule(numMatricule);
        if (personnel != null) {
            return ResponseEntity.ok(personnel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Rechercher un personnel par email
    @GetMapping("/email/{email}")
    public ResponseEntity<Personnels> getPersonnelByEmail(@PathVariable("email") String email) {
        Personnels personnel = personnelService.findByEmail(email);
        if (personnel != null) {
            return ResponseEntity.ok(personnel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}