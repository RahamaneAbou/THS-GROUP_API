package com.THS_GROUP.Services;

import com.THS_GROUP.Entyties.Enseignant;
import com.THS_GROUP.Repository.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EnseignantService {

    @Autowired
    private EnseignantRepository enseignantRepository;

    // Retrieve all enseignants
    public List<Enseignant> findAll() {
        return enseignantRepository.findAll();
    }

    // Retrieve an enseignant by their ID
    public Optional<Enseignant> findById(Long id) {
        return enseignantRepository.findById(id);
    }

    // Create
    public Enseignant save(Enseignant enseignant) {
        if (enseignant == null) {
            throw new IllegalArgumentException("L'enseignant ne peut pas être null.");
        }
        return enseignantRepository.save(enseignant);
    }

    // Delete an enseignant by their ID
    public void deleteById(Long id) {
        enseignantRepository.deleteById(id);
    }

    // Find all enseignants teaching a specific course
    public List<Enseignant> findEnseignantsByCoursId(Long coursId) {
        return enseignantRepository.findByCoursId(coursId);
    }

    /* Find all enseignants responsible for a specific formation
    public List<Enseignant> findEnseignantsByFormationId(Long formationId) {
        return enseignantRepository.findByFormationId(formationId);
    }*/

    // Update an existing enseignant
    public Enseignant updateEnseignant(Long id, Enseignant updatedEnseignant) {
        return enseignantRepository.findById(id)
                .map(enseignant -> {
                    enseignant.setNom(updatedEnseignant.getNom());
                    enseignant.setPrenom(updatedEnseignant.getPrenom());
                    enseignant.setEmail(updatedEnseignant.getEmail());
                    enseignant.setNumeroTelephone(updatedEnseignant.getNumeroTelephone());
                    return enseignantRepository.save(enseignant);
                }).orElseThrow(() -> new RuntimeException("Enseignant non trouvé avec l'ID " + id));
    }

	public Optional<Enseignant> findByNumMatricule(String numMatricule) {
		// TODO Auto-generated method stub
		return enseignantRepository.findByNumMatricule(numMatricule);
	}
}