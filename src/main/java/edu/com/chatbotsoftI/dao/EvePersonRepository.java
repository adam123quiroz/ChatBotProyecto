package edu.com.chatbotsoftI.dao;

import edu.com.chatbotsoftI.domain.EvePerson;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EvePersonRepository extends JpaRepository <EvePerson, Integer> {
    List<EvePerson> findAll();
    EvePerson findByBotUserId(String botUserId);
}
