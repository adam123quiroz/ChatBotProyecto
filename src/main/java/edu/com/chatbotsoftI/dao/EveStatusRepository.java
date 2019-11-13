package edu.com.chatbotsoftI.dao;

import edu.com.chatbotsoftI.entity.EveStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//este STATUS deberia ser STATE
public interface EveStatusRepository extends JpaRepository<EveStateEntity, Integer> {
    EveStateEntity findByState(String state);
    boolean existsByState(String state);
}
