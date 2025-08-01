package com.THS_GROUP.Controller;

import com.THS_GROUP.Entyties.Enseignant;
import com.THS_GROUP.Entyties.Etudiant;
import com.THS_GROUP.Services.EnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"}, 
methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
           RequestMethod.DELETE, RequestMethod.OPTIONS},
allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/THS-GROUP/enseignants")
public class EnseignantController {

    @Autowired
    private EnseignantService enseignantService;

    /**
     * Retrieve all enseignants.
     *
     * @return List of all enseignants.
     */
    @GetMapping
    public List<Enseignant> getAllEnseignants() {
    	System.out.println("La liste de tous les enseignants a été démander");
        return enseignantService.findAll();
    }

    /**
     * Retrieve an enseignant by their ID.
     *
     * @param id Enseignant ID.
     * @return The enseignant corresponding to the provided ID or 404 if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Enseignant> getEnseignantById(@PathVariable("id") Long id) {
    	System.out.println("Un enseignant a été démander via son ID");
        return enseignantService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Create or update an enseignant.
     *
     * @param enseignant Enseignant object to persist.
     * @return The saved enseignant.
     */
    @PostMapping("/createEnseignant")
    public Enseignant create(@RequestBody Enseignant enseignant) {
    	System.out.println("Un enseignant a été ajouter");
        return enseignantService.save(enseignant);
    }

    /**
     * Delete an enseignant by their ID.
     *
     * @param id Enseignant ID to delete.
     * @return HTTP 204 No Content response.
     */
    @DeleteMapping("/enseignantDelete/{id}")
    public ResponseEntity<Void> deleteEnseignant(@PathVariable("id") Long id) {
        enseignantService.deleteById(id);
        System.out.println("La suppression d'un enseignant a été démander");
        return ResponseEntity.noContent().build();
    }

    /**
     * Find all enseignants teaching a specific course.
     *
     * @param coursId Course ID.
     * @return List of enseignants teaching the specified course.
     */
   /* @GetMapping("/cours/{coursId}")
    public ResponseEntity<List<Enseignant>> findEnseignantsByCoursId(@PathVariable("coursId") Long coursId) {
        List<Enseignant> enseignants = enseignantService.findEnseignantsByCoursId(coursId);
        System.out.println("Une liste des enseignant a été demander via l'ID d'un cour");
        return ResponseEntity.ok(enseignants);
    }*/
    
    /**
     * Find all enseignants responsible for a specific formation.
     *
     * @param formationId Formation ID.
     * @return List of enseignants responsible for the specified formation.
     */
    /*
    @GetMapping("/formation/{formationId}")
    public ResponseEntity<List<Enseignant>> findEnseignantsByFormationId(@PathVariable("formationId") Long formationId) {
        List<Enseignant> enseignants = enseignantService.findEnseignantsByFormationId(formationId);
        System.out.println("Une liste des enseignant a été démander via l'id d'une formation");
        return ResponseEntity.ok(enseignants);
    }*/
    /**
     * Update an existing enseignant.
     *
     * @param id Enseignant ID to update.
     * @param updatedEnseignant Updated enseignant data.
     * @return The updated enseignant or 404 if not found.
     */
    @PutMapping("/UpdateEnseignant/{id}")
    public ResponseEntity<Enseignant> updateEnseignant(@PathVariable("id") Long id, @RequestBody Enseignant updatedEnseignant) {
        try {
            Enseignant enseignant = enseignantService.updateEnseignant(id, updatedEnseignant);
            System.out.println("Un enseignant a été mis à jour");
            return ResponseEntity.ok(enseignant);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getEnseignantByNumMatricule/{numMatricule}")
    public ResponseEntity<Enseignant> getEnseignantByNumMatricule(@PathVariable("numMatricule") String numMatricule) {
    	System.out.println("Un enseignant a été demander via un numero matricule");
        return enseignantService.findByNumMatricule(numMatricule)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}