package com.THS_GROUP.Services;


import com.THS_GROUP.Entyties.Administrateur;
import com.THS_GROUP.Repository.AdministrateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministrateurService {

    @Autowired
    private AdministrateurRepository administrateurRepository;

    public List<Administrateur> getAllAdministrateurs() {
        return administrateurRepository.findAll();
    }

    public Optional<Administrateur> getAdministrateurById(Long id) {
        return administrateurRepository.findById(id);
    }

    public Administrateur createAdministrateur(Administrateur administrateur) {
        return administrateurRepository.save(administrateur);
    }

    public Administrateur updateAdministrateur(Long id, Administrateur administrateurDetails) {
        Administrateur administrateur = administrateurRepository.findById(id).orElseThrow();
        administrateur.setEmail(administrateurDetails.getEmail());
        administrateur.setPassword(administrateurDetails.getPassword()); // À hasher plus tard
        administrateur.setNomComplet(administrateurDetails.getNomComplet());
        return administrateurRepository.save(administrateur);
    }

    public void deleteAdministrateur(Long id) {
        administrateurRepository.deleteById(id);
    }

    public Optional<Administrateur> findByEmail(String email) {
        return administrateurRepository.findByEmail(email);
    }
}