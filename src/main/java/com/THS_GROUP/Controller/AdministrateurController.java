package com.THS_GROUP.Controller;


import com.THS_GROUP.Entyties.Administrateur;
import com.THS_GROUP.Services.AdministrateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/THS-GROUP/administrateurs")
@CrossOrigin(origins = "*")
public class AdministrateurController {

    @Autowired
    private AdministrateurService administrateurService;

    @GetMapping
    public ResponseEntity<List<Administrateur>> getAllAdministrateurs() {
        return new ResponseEntity<>(administrateurService.getAllAdministrateurs(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrateur> getAdministrateurById(@PathVariable Long id) {
        return administrateurService.getAdministrateurById(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Administrateur> createAdministrateur(@RequestBody Administrateur administrateur) {
        return new ResponseEntity<>(administrateurService.createAdministrateur(administrateur), HttpStatus.CREATED);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<Administrateur> getAdministrateurByEmail(@PathVariable String email) {
        Optional<Administrateur> administrateur = administrateurService.findByEmail(email);
        return administrateur.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Administrateur> updateAdministrateur(@PathVariable Long id, @RequestBody Administrateur administrateur) {
        return new ResponseEntity<>(administrateurService.updateAdministrateur(id, administrateur), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrateur(@PathVariable Long id) {
        administrateurService.deleteAdministrateur(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}