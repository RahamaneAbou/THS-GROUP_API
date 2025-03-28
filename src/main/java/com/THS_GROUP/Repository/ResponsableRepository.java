package com.THS_GROUP.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.THS_GROUP.Entityes.Responsable;


public interface ResponsableRepository extends JpaRepository<Responsable, Long>  {

	public Optional<Responsable> findByPassword(String password);
	public Optional<Responsable> findByNom(String nom);
	public Optional<Responsable> findByPrenom(String prenom);
	public Optional<Responsable> findByEmail(String email);
}
