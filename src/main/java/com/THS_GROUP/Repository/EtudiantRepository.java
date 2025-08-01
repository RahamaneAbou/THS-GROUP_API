package com.THS_GROUP.Repository;

import com.THS_GROUP.Entyties.Etudiant;
import com.THS_GROUP.Entyties.StatusInscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Optional<Etudiant> findByNumMatricule(String numMatricule);
    List<Etudiant> findByFormationId(Long formationId);
    @Query("SELECT e FROM Etudiant e WHERE e.nom = :nom AND e.prenom = :prenom")
    Optional<Etudiant> findByNomAndPrenom(@Param("nom") String nom, @Param("prenom") String prenom);
    List<Etudiant> findByStatusInscription(StatusInscription status);
}