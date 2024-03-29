package edu.com.chatbotsoftI.dao;

import edu.com.chatbotsoftI.entity.EveTypeEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EveTypeEventRepository extends JpaRepository<EveTypeEventEntity, Integer> {
    EveTypeEventEntity findByTypeEvent(String typeEvent);
    boolean existsByTypeEvent(String typeEvent);
}
