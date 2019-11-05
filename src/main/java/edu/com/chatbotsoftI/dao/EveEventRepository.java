package edu.com.chatbotsoftI.dao;

import edu.com.chatbotsoftI.domain.EveEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EveEventRepository extends JpaRepository<EveEvent, Integer> {
    List<EveEvent> findAllByStatus(int status);
    List<EveEvent> findAllByIdtypeevent_TypeeventAndStatus(String typeEvent, int status);
}

