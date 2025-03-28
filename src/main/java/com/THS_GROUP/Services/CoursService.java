package com.THS_GROUP.Services;

import com.THS_GROUP.Entyties.Cours;
import com.THS_GROUP.Repository.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CoursService {

    @Autowired
    private CoursRepository coursRepository;

    // Retrieve all courses
    public List<Cours> findAll() {
        return coursRepository.findAll();
    }

    // Retrieve a course by its ID
    public Optional<Cours> findById(Long id) {
        return coursRepository.findById(id);
    }

    // Create or update a course
    public Cours save(Cours cours) {
        if (cours == null) {
            throw new IllegalArgumentException("Le cours ne peut pas être null.");
        }
        return coursRepository.save(cours);
    }

    // Delete a course by its ID
    public void deleteById(Long id) {
        coursRepository.deleteById(id);
    }

    // Find all courses for a given formation
    public List<Cours> findCoursesByFormationId(Long formationId) {
        return coursRepository.findByFormationId(formationId);
    }
    public List<Cours>findCoureseByEnseignantId(Long enseignantId){
    	return coursRepository.findByEnseignantId(enseignantId);
    }

    // Update an existing course
    public Cours updateCourse(Long id, Cours updatedCourse) {
        return coursRepository.findById(id)
                .map(cours -> {
                    cours.setNom(updatedCourse.getNom());
                    cours.setDescription(updatedCourse.getDescription());
                    cours.setEnseignantId(updatedCourse.getEnseignantId());
                    cours.setFormation(updatedCourse.getFormation());
                    return coursRepository.save(cours);
                }).orElseThrow(() -> new RuntimeException("Cours non trouvé avec l'ID " + id));
    }
}