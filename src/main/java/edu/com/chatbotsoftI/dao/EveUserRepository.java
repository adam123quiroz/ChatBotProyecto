package edu.com.chatbotsoftI.dao;

import edu.com.chatbotsoftI.entity.EveUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EveUserRepository extends JpaRepository<EveUserEntity, Integer> {
    EveUserEntity findByNameuserAndPassword(String userName, String password);
    List<EveUserEntity> findAllByEmail(String email);
    EveUserEntity findByNameUserAndPassword(String userName, String password);
}
