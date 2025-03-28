package com.THS_GROUP.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.THS_GROUP.Entityes.Responsable;
import com.THS_GROUP.Repository.ResponsableRepository;

@Service
@Transactional

public class ResponsableService {
	private ResponsableRepository responableRepository;
	public Responsable createResponsable(Responsable responsable) {
        return responableRepository.save(responsable);
    }
	 public Optional<Responsable> updateResponsable(Long id, Responsable responsableDetails) {
	        Optional<Responsable> optionalResponsable = responableRepository.findById(id);
	        
	        if (optionalResponsable.isPresent()) {
	            Responsable responsable = optionalResponsable.get();
	            responsable.setNom(responsableDetails.getNom());
	            responsable.setPrenom(responsableDetails.getPrenom());
	            responsable.setEmail(responsableDetails.getEmail());
	            responsable.setPassword(responsableDetails.getPassword());

	            return Optional.of(responableRepository.save(responsable));
	        } else {
	            return Optional.empty();
	        }
	    }
	public List <Responsable>findAll(){
		return responableRepository.findAll();
	}
	public Optional<Responsable>findById(Long id){
		return responableRepository.findById(id);
	}

	public Optional<Responsable>findByPassword(String password){
		return responableRepository.findByPassword(password);
	}

	public Optional<Responsable>findByNom(String nom){
		return responableRepository.findByNom(nom);
	}
	public Optional<Responsable>findByPrenom(String prenom){
		return responableRepository.findByPrenom(prenom);
	}
	
	public Optional<Responsable>findByEmail(String email){
		return responableRepository.findByEmail(email);
	}


}
