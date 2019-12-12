package edu.com.chatbotsoftI.dao;

import edu.com.chatbotsoftI.entity.EveNotificationUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EveNotificationUserRepository extends JpaRepository<EveNotificationUserEntity, Integer> {
    //List<EveNotificationUserEntity> findAllByIdnotificationuser(int notificaction);
}
