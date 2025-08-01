package com.THS_GROUP.Controller;

import com.THS_GROUP.Entyties.Note;
import com.THS_GROUP.Services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"https://ths-group-front-end.onrender.com","http://localhost:5173", "http://localhost:3000"},
methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS},
allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/THS-GROUP/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    /**
     * Retrieve all notes.
     *
     * @return List of all notes.
     */
    @GetMapping
    public List<Note> getAllNotes() {
    	System.out.println("La liste complete des note a été demander");
        return noteService.findAll();
    }

    /**
     * Retrieve all notes for a specific student.
     *
     * @param etudiantId Student ID.
     * @return List of notes for the specified student.
     */
    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<Note>> getNotesByEtudiantId(@PathVariable("etudiantId") Long etudiantId) {
        List<Note> notes = noteService.findNotesByEtudiantId(etudiantId);
        System.out.println("La note d'un etudiant a été démander via son ID");
        return ResponseEntity.ok(notes);
    }

    /**
     * Retrieve all notes for a specific course.
     *
     * @param coursId Course ID.
     * @return List of notes for the specified course.
     */
    @GetMapping("/cours/{coursId}")
    public ResponseEntity<List<Note>> getNotesByCoursId(@PathVariable("coursId") Long coursId) {
        List<Note> notes = noteService.findNotesByCoursId(coursId);
        System.out.println("Une note a été démander via l'ID du cours");
        return ResponseEntity.ok(notes);
    }

    /**
     * Create or update a note.
     *
     * @param note Note object to persist.
     * @return The saved note.
     */
    @PostMapping("/createNote")
    public Note create(@RequestBody Note note) {
    	System.out.println("Une note a été ajoutée");
        return noteService.save(note);
    }

    /**
     * Delete a note by its ID.
     *
     * @param id Note ID to delete.
     * @return HTTP 204 No Content response.
     */
    @DeleteMapping("/noteDelete/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id") Long id) {
        noteService.deleteById(id);
        System.out.println("La suppression d'une note a été demander via son ID");
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieve a specific note for a given student and course.
     *
     * @param etudiantId Student ID.
     * @param coursId    Course ID.
     * @return The note corresponding to the provided IDs or 404 if not found.
     */
    @GetMapping("/etudiant/{etudiantId}/cours/{coursId}")
    public ResponseEntity<Optional<Note>> getNoteByEtudiantAndCours(
            @PathVariable("etudiantId") Long etudiantId,
            @PathVariable("coursId") Long coursId
    ) {
        try {
            Optional<Note> note = noteService.findNoteByEtudiantAndCours(etudiantId, coursId);
            System.out.println("Une note a été demander via l'id d'un etudiant et du cour");
            return ResponseEntity.ok(note);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateNote(
        @RequestParam("valeur") double valeur,
        @RequestParam("dateAttribution") Date dateAttribution,
        @RequestParam("etudiantId") Long etudiantId,
        @RequestParam("coursId") Long coursId
    ) {
        try {
            noteService.updateNote(valeur, dateAttribution, etudiantId, coursId);
            return ResponseEntity.ok("Note mise à jour avec succès !");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Une erreur est survenue lors de la mise à jour de la note.");
        }
    }
}