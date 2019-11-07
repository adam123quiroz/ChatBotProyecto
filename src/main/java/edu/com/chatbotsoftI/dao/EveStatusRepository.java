package edu.com.chatbotsoftI.dao;

import edu.com.chatbotsoftI.entity.EveStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EveStatusRepository extends JpaRepository<EveStateEntity, Integer> {
    EveStateEntity findByState(String state);
}
