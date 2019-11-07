package edu.com.chatbotsoftI.dao;

import edu.com.chatbotsoftI.entity.EveEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EveEventRepository extends JpaRepository<EveEventEntity, Integer> {
    List<EveEventEntity> findAllByStatus(int status);
    List<EveEventEntity> findAllByEvetypeeventByIdtypeevent_TypeeventAndStatus(String typeEvent, int status);

}

