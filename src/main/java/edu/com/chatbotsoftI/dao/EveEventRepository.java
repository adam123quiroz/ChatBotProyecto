package edu.com.chatbotsoftI.dao;

import edu.com.chatbotsoftI.entity.EveEventEntity;
import edu.com.chatbotsoftI.entity.EveUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EveEventRepository extends JpaRepository<EveEventEntity, Integer> {
    List<EveEventEntity> findAllByStatus(int status);

    List<EveEventEntity> findAllByEveTypeEventByIdTypeEvent_TypeEventAndStatus(String typeEvent, int status);
    // List<EveEventEntity> findAllByIdtypeevent_TypeeventAndStatus(String typeEvent, int status);

    List<EveEventEntity> findAllByEveUserByIdUser_NameUser(String userName);
   // List<EveEventEntity> findAllByIduser_Nameuser(String userName);

    List<EveEventEntity> findAllByEveUserByIdUser(EveUserEntity userEntity);

    EveEventEntity findByIdEventAndStatus(Integer id, Integer status);

    EveEventEntity findByIdEvent(Integer id);
    List<EveEventEntity> findAllByEveUserByIdUser_IdUserAndStatus(Integer idSer, Integer status);
}

