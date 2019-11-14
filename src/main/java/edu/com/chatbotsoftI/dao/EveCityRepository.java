package edu.com.chatbotsoftI.dao;

import edu.com.chatbotsoftI.entity.EveCityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EveCityRepository extends JpaRepository<EveCityEntity, Integer> {
    EveCityEntity findByCity(String city);
    boolean existsByCity(String city);
}
