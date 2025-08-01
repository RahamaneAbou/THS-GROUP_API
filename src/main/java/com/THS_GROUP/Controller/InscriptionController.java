package com.THS_GROUP.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.THS_GROUP.Entyties.Inscription;
import com.THS_GROUP.Entyties.StatusInscription;
import com.THS_GROUP.Services.InscriptionService;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"}, 
methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
          RequestMethod.DELETE, RequestMethod.OPTIONS},
allowedHeaders = "*",
maxAge = 3600)
@RestController
@RequestMapping("/THS-GROUP/inscriptions")
public class InscriptionController {

    @Autowired
    private InscriptionService inscriptionService;

    @GetMapping("/getAllInscription")
    public List<Inscription> getAllInscriptions() {
        return inscriptionService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Inscription> getInscriptionById(@PathVariable Long id) {
        return inscriptionService.findById(id);
    }

    @PostMapping("/createInscription")
    public Inscription createInscription(@RequestBody Inscription inscription) {
        return inscriptionService.save(inscription);
    }

    @PutMapping("/{id}")
    public Inscription updateInscription(@PathVariable Long id, @RequestBody Inscription updatedInscription) {
        return inscriptionService.findById(id).map(inscription -> {
            inscription.setStatus(updatedInscription.getStatus());
            inscription.setDateConfirmation(updatedInscription.getDateConfirmation());
            return inscriptionService.save(inscription);
        }).orElseThrow(() -> new RuntimeException("Inscription not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteInscription(@PathVariable Long id) {
        inscriptionService.deleteById(id);
    }
    /**
     * Récupérer toutes les inscriptions en attente.
     */
    @GetMapping("/en-attente")
    public ResponseEntity<List<Inscription>> getInscriptionsEnAttente() {
        return ResponseEntity.ok(inscriptionService.getInscriptionsByStatus(StatusInscription.EN_ATTENTE));
    }    /**
     * Récupérer toutes les inscriptions confirmées.
     */
    @GetMapping("/confirmes")
    public ResponseEntity<List<Inscription>> getInscriptionsConfirmes() {
        return ResponseEntity.ok(inscriptionService.getInscriptionsByStatus(StatusInscription.COMFIMER));
    }

    /**
     * Récupérer toutes les inscriptions rejetées.
     */
    @GetMapping("/rejetees")
    public ResponseEntity<List<Inscription>> getInscriptionsRejetees() {
        return ResponseEntity.ok(inscriptionService.getInscriptionsByStatus(StatusInscription.REFUSER));
    }    /**
     * Refuser une inscription.
     * Mettre à jour le statut de l'inscription vers "REFUSER" et supprimer la date de confirmation.
     */
    @PutMapping("/{id}/confirmer")
    public ResponseEntity<String> confirmInscription(@PathVariable Long id) {
        try {
            inscriptionService.updateInscriptionStatus(id, StatusInscription.COMFIMER, new java.util.Date());
            return ResponseEntity.ok("Inscription confirmée avec succès !");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la confirmation de l'inscription.");
        }
    }    /**
     * Confirmer une inscription.
     * Mettre à jour le statut de l'inscription vers "COMFIMER" et ajouter la date de confirmation.
     */
   }
