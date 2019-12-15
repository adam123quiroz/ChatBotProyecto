package edu.com.chatbotsoftI.dao;

import edu.com.chatbotsoftI.entity.EvePersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvePersonRepository extends JpaRepository<EvePersonEntity, Integer> {
    List<EvePersonEntity> findAll();
    EvePersonEntity findByBotUserId(String botUserId);
    EvePersonEntity findByIdperson(Integer idperson);
}
