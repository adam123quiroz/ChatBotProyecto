package edu.com.chatbotsoftI.dao;

import edu.com.chatbotsoftI.entity.EveUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EveUserRepository extends JpaRepository<EveUserEntity, Integer> {
    EveUserEntity findByNameUserAndPassword(String userName, String password);
}
