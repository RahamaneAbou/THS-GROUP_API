package com.THS_GROUP.Repository;

import com.THS_GROUP.Entyties.Personnels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnels, Long> {
    
    // Recherche par numéro matricule
    Personnels findByNumMatricule(String numMatricule);

    // Recherche par email (utile pour l'authentification)
    Personnels findByEmail(String email);
}