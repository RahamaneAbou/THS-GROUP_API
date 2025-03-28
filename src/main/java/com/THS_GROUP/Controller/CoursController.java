package com.THS_GROUP.Controller;

import com.THS_GROUP.Entyties.Cours;
import com.THS_GROUP.Entyties.Formation;
import com.THS_GROUP.Services.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/THS-GROUP/cours")
public class CoursController {

    @Autowired
    private CoursService coursService;

    /**
     * Retrieve all courses.
     *
     * @return List of all courses.
     */
    @GetMapping
    public List<Cours> getAllCourses() {
    	System.out.println("La list de tous les cours a été demander");
        return coursService.findAll();
    }

    /**
     * Retrieve a course by its ID.
     *
     * @param id Course ID.
     * @return The course corresponding to the provided ID or 404 if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cours> getCourseById(@PathVariable("id") Long id) {
    	System.out.println("Un cours a été démander via son ID");
        return coursService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Create or update a course.
     *
     * @param cours Course object to persist.
     * @return The saved course.
     */
    @PostMapping("/createCour")
    public Cours create(@RequestBody Cours cours) {
    	System.out.println("Un cour a été ajouté");
        return coursService.save(cours);
    }
    @PutMapping("/CoursUpdate/{id}")
    public ResponseEntity<Cours> updateCours(@PathVariable("id") Long id, @RequestBody Cours updatedCours) {
        Cours cours = coursService.updateCourse(id, updatedCours);
        System.out.println("Un Cour a été mise à jour");
        return ResponseEntity.ok(cours);
    }

    /**
     * Delete a course by its ID.
     *
     * @param id Course ID to delete.
     * @return HTTP 204 No Content response.
     */
    @DeleteMapping("/courDelete/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable("id") Long id) {
        coursService.deleteById(id);
        System.out.println("La suppression d'un cours a été demander");
        return ResponseEntity.noContent().build();
    }

    /**
     * Find all courses for a given formation.
     *
     * @param formationId Formation ID.
     * @return List of courses associated with the formation.
     */
    @GetMapping("/formation/{formationId}")
    public ResponseEntity<List<Cours>> findCoursesByFormationId(@PathVariable("formationId") Long formationId) {
        List<Cours> courses = coursService.findCoursesByFormationId(formationId);
        System.out.println("La liste de cours d'une formation a été demander via l'ID de la formation");
        return ResponseEntity.ok(courses);
    }
    @GetMapping("/getByEnseignantId/{EnseignantId}")
    public ResponseEntity<List<Cours>> findCoursesByEnseignantId(@PathVariable("EnseignantId") Long EnseignantId) {
        List<Cours> courses = coursService.findCoureseByEnseignantId(EnseignantId);
        System.out.println("La liste de cours d'une formation a été demander via l'ID de l'enseignant");
        return ResponseEntity.ok(courses);
    } 
}