package com.THS_GROUP.Repository;

import com.THS_GROUP.Entyties.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {
    Optional<Formation> findById(Long id);
    List<Formation> findByResponsableId(Long responsableId);
   
}