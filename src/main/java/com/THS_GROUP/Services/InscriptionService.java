package com.THS_GROUP.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.THS_GROUP.Entyties.Inscription;
import com.THS_GROUP.Entyties.StatusInscription;
import com.THS_GROUP.Repository.InscriptionRepository;

@Service
public class InscriptionService {

	@Autowired
    private InscriptionRepository inscriptionRepository;

    public List<Inscription> findAll() {
        return inscriptionRepository.findAll();
    }

    public Optional<Inscription> findById(Long id) {
        return inscriptionRepository.findById(id);
    }

    public Inscription save(Inscription inscription) {
        return inscriptionRepository.save(inscription);
    }

    public void deleteById(Long id) {
        inscriptionRepository.deleteById(id);
    }
    public List<Inscription> getInscriptionsByStatus(StatusInscription status) {
        return inscriptionRepository.findByStatus(status);
    }
    public void updateInscriptionStatus(Long id, StatusInscription status, Date dateConfirmation) {
        inscriptionRepository.updateStatusById(id, status, dateConfirmation);
    }   
}
