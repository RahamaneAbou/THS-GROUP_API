package com.THS_GROUP.Repository;

import com.THS_GROUP.Entyties.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
    Optional<Enseignant> findById(Long id);
    Optional<Enseignant> findByNumMatricule(String numMatricule);
   // List<Enseignant> findByCoursId(Long coursId); // Find all enseignants teaching a specific course
   // List<Enseignant> findByFormationId(Long formationId); // Find all enseignants responsible for a specific formation
}