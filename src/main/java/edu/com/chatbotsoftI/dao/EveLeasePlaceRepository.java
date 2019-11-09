package edu.com.chatbotsoftI.dao;


import edu.com.chatbotsoftI.entity.EveLeasePlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EveLeasePlaceRepository extends JpaRepository <EveLeasePlaceEntity, Integer> {
    List<EveLeasePlaceEntity> findAllByStatus(int status);
    List<EveLeasePlaceEntity> findAllByEveuserByIduser_Nameuser(String userName);
 // List<EveLeasePlaceEntity> findAllByIduser_Nameuser(String userName);
}
