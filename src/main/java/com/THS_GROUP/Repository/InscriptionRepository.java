package com.THS_GROUP.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.THS_GROUP.Entyties.Inscription;
import com.THS_GROUP.Entyties.StatusInscription;

import jakarta.transaction.Transactional;

public interface InscriptionRepository extends JpaRepository<Inscription, Long>{
    @Transactional
    @Modifying
    @Query("UPDATE Inscription i SET i.status = :status, i.dateConfirmation = :dateConfirmation WHERE i.id = :id")
    void updateStatusById(@Param("id") Long id, @Param("status") String status, @Param("dateConfirmation") Date dateConfirmation);

    List<Inscription> findByStatus(StatusInscription status);

    /**
     * Mettre à jour le statut d'une inscription par son ID.
     */
    @Transactional
    @Modifying
    @Query("UPDATE Inscription i SET i.status = :status, i.dateConfirmation = :dateConfirmation WHERE i.id = :id")
    void updateStatusById(Long id, 
                          StatusInscription status, 
                           Date dateConfirmation);
}
