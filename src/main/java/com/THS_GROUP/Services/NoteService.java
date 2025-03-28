package com.THS_GROUP.Services;

import com.THS_GROUP.Entyties.Note;
import com.THS_GROUP.Repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    // Retrieve all notes
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    // Retrieve a note by its ID
    public Optional<Note> findById(Long id) {
        return noteRepository.findById(id);
    }

    // Create or update a note
    @Transactional
    public Note save(Note note) {
        if (note == null) {
            throw new IllegalArgumentException("La note ne peut pas être null.");
        }
        return noteRepository.save(note);
    }

    // Delete a note by its ID
    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }

    // Find all notes for a specific student
    public List<Note> findNotesByEtudiantId(Long etudiantId) {
        return noteRepository.findByEtudiantId(etudiantId);
    }

    // Find all notes for a specific course
    public List<Note> findNotesByCoursId(Long coursId) {
        return noteRepository.findByCoursId(coursId);
    }

	public Optional<Note> findNoteByEtudiantAndCours(Long etudiantId, Long coursId) {
		// TODO Auto-generated method stub
		return noteRepository.findByEtudiantIdAndCoursId(etudiantId, coursId);
	}
    // Récupérer toutes les notes pour une formation donnée
    public List<Note> findNotesByFormationId(Long formationId) {
        return noteRepository.findNotesByFormationId(formationId);
    }
    public void updateNote(double valeur, Date dateAttribution, Long etudiantId, Long coursId) {
        if (valeur < 0 || valeur > 20) {
            throw new IllegalArgumentException("La note doit être comprise entre 0 et 20.");
        }
        if (dateAttribution == null) {
            throw new IllegalArgumentException("La date d'attribution ne peut pas être nulle.");
        }
        noteRepository.updateNoteByEtudiantAndCours(valeur, dateAttribution, etudiantId, coursId);
    }


   
}