package com.THS_GROUP.Repository;

import com.THS_GROUP.Entyties.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoursRepository extends JpaRepository<Cours, Long> {
    Optional<Cours> findById(Long id);
    List<Cours> findByFormationId(Long formationId); // Find courses by Formation ID <button class="citation-flag" data-index="4">
    List<Cours> findByEnseignantId(Long EnseignantId);
}