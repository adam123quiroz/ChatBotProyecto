package edu.com.chatbotsoftI.dao;

import edu.com.chatbotsoftI.entity.EveAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EveAddressRepository extends JpaRepository<EveAddressEntity, Integer> {
}
