package edu.com.chatbotsoftI.dao;


import edu.com.chatbotsoftI.entity.EveCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EveCategoryRepository extends JpaRepository<EveCategoryEntity, Integer> {
    boolean existsByCategory(String category);
    EveCategoryEntity findByCategory(String category);
}
