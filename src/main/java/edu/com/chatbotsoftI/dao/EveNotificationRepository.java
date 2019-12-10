package edu.com.chatbotsoftI.dao;

import edu.com.chatbotsoftI.entity.EveNotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EveNotificationRepository extends JpaRepository<EveNotificationEntity, Integer> {
    List<EveNotificationEntity> findAllByEveleaseplaceByIdleaseplace(int idleaseplace);

}
