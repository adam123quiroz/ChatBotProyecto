package edu.com.chatbotsoftI.dao;

import edu.com.chatbotsoftI.entity.EveEventEntity;
import edu.com.chatbotsoftI.entity.EveUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EveEventRepository extends JpaRepository<EveEventEntity, Integer> {
    List<EveEventEntity> findAllByStatus(int status);

    List<EveEventEntity> findAllByEvetypeeventByIdtypeevent_TypeeventAndStatus(String typeEvent, int status);
    // List<EveEventEntity> findAllByIdtypeevent_TypeeventAndStatus(String typeEvent, int status);

    List<EveEventEntity> findAllByEveuserByIduser_Nameuser(String userName);
   // List<EveEventEntity> findAllByIduser_Nameuser(String userName);

    List<EveEventEntity> findAllByEveuserByIduser(EveUserEntity userEntity);

    EveEventEntity findByIdeventAndStatus(Integer id, Integer status);

    EveEventEntity findByIdevent(Integer id);
    List<EveEventEntity> findAllByEveuserByIduser_IduserAndStatus(Integer idSer, Integer status);
}

