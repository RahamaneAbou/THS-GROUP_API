package com.THS_GROUP.Repository;

import com.THS_GROUP.Entyties.Note;
import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    Optional<Note> findById(Long id);
    List<Note> findByEtudiantId(Long etudiantId); // Find notes by student ID
    List<Note> findByCoursId(Long coursId); // Find notes by course ID
	Optional<Note> findByEtudiantIdAndCoursId(Long etudiantId, Long coursId);
	
	@Transactional
    @Modifying
    @Query("UPDATE Note n SET n.valeur = :valeur, n.DateAttribution = :dateAttribution " +
           "WHERE n.etudiant.id = :etudiantId AND n.cours.id = :coursId")
    void updateNoteByEtudiantAndCours( double valeur, Date dateAttribution,
         Long etudiantId,
 Long coursId
    );
	// Récupérer toutes les notes pour une formation donnée
    @Query("SELECT n FROM Note n WHERE n.etudiant.formation.id = :formationId")
    List<Note> findNotesByFormationId(@Param("formationId") Long formationId);
    
}