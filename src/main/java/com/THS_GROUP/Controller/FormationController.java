package com.THS_GROUP.Controller;

import com.THS_GROUP.Entyties.Formation;
import com.THS_GROUP.Services.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/THS-GROUP/formations")
@CrossOrigin(
        origins = {"https://ths-group-front-end.onrender.com","http://localhost:5173", "http://127.0.0.1:5173", "https://ton-site.com"},
        allowCredentials = "true"
)
public class FormationController {

    @Autowired
    private FormationService formationService;

    // Récupérer toutes les formations
    @GetMapping
    public List<Formation> getAllFormations() {
    	System.out.println("La liste de toute les formations a été demandée");
        return formationService.findAll();
    }

    // Récupérer une formation par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Formation> getFormationById(@PathVariable("id") Long id) {
    	System.out.println("Une formation a été demander via son ID");
        return formationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Créer ou mettre à jour une formation
    @PostMapping("/createFormation")
    public Formation createOrUpdateFormation(@RequestBody Formation formation) {
    	 System.out.println("Une formatin a été ajoutée");
        return formationService.save(formation);
    }

    // Supprimer une formation par son ID
    @DeleteMapping("/formationDelete/{id}")
    public ResponseEntity<Void> deleteFormation(@PathVariable("id") Long id) {
        formationService.deleteById(id);
        System.out.println("La supression d'une formation a été demandé");
        return ResponseEntity.noContent().build();
    }

    // Trouver toutes les formations d'un enseignant donné
    @GetMapping("/enseignant/{enseignantId}")
    public ResponseEntity<List<Formation>> findFormationsByEnseignantId(@PathVariable("enseignantId") Long enseignantId) {
        List<Formation> formations = formationService.findFormationsByResponsableId(enseignantId);
        System.out.println("Un list de formation a été demander via l'ID d'un enseignant");
        return ResponseEntity.ok(formations);
    }

    // Mettre à jour une formation existante
    @PutMapping("/formationUpdate/{id}")
    public ResponseEntity<Formation> updateFormation(@PathVariable("id") Long id, @RequestBody Formation updatedFormation) {
        Formation formation = formationService.updateFormation(id, updatedFormation);
        System.out.println("Une formation a été mise à jour");
        return ResponseEntity.ok(formation);
    }
}