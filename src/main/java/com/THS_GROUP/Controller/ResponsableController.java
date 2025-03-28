package com.THS_GROUP.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.THS_GROUP.Entityes.Responsable;
import com.THS_GROUP.Services.ResponsableService;

@RestController
@RequestMapping("/LPSIC-CTRL/Responsable")
public class ResponsableController {
	 @Autowired
	 private ResponsableService responsableService;

	 @GetMapping("/getAll")
	 public List<Responsable>getAllResponsable(){
		 System.out.println("");
		 return responsableService.findAll();
	 }
	    @GetMapping("/getById/{id}")
	    public ResponseEntity<Responsable> getResponsablelById(@PathVariable("id") Long id) {
	    	System.out.println("______############### La liste de tous les responsabl a été demander ##############_____");
	        return responsableService.findById(id)
	                .map(ResponseEntity::ok)
	                .orElseGet(() -> ResponseEntity.notFound().build());
	    }
	    
	    @GetMapping("/getByNom/{nom}")
	    public ResponseEntity<Responsable> getResponsablelByNom(@PathVariable("nom") String nom) {
	    	System.out.println("______############### Un responsable a été démanser via un ID ##############_____");
	        return responsableService.findByNom(nom)
	                .map(ResponseEntity::ok)
	                .orElseGet(() -> ResponseEntity.notFound().build());
	    }
	    @GetMapping("/getByPrenom/{prenom}")
	    public ResponseEntity<Responsable> getResponsablelByPrenom(@PathVariable("prenom") String prenom) {
	    	System.out.println("______############### Un responsable a été démanser via un nom ##############_____");
	        return responsableService.findByPrenom(prenom)
	                .map(ResponseEntity::ok)
	                .orElseGet(() -> ResponseEntity.notFound().build());
	    }
	    @GetMapping("/getByPassword/{password}")
	    public ResponseEntity<Responsable> getResponsablelByPassword(@PathVariable("password") String password) {
	    	System.out.println("______############### Un responsable a été démanser via un prenom ##############_____");
	        return responsableService.findByPassword(password)
	                .map(ResponseEntity::ok)
	                .orElseGet(() -> ResponseEntity.notFound().build());
	    }
	    @GetMapping("/getByEmail/{Email}")
	    public ResponseEntity<Responsable> getResponsablelById(@PathVariable("Email") String Email) {
	    	System.out.println("______############### Un responsable a été démanser via un Email ##############_____");
	        return responsableService.findByEmail(Email)
	                .map(ResponseEntity::ok)
	                .orElseGet(() -> ResponseEntity.notFound().build());
	    }
	    @PostMapping("/create")
	    public Responsable createResponsable(@RequestBody Responsable responsable) {
	        return responsableService.createResponsable(responsable);
	    }
}
