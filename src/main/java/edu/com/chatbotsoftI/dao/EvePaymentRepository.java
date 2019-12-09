package edu.com.chatbotsoftI.dao;

import edu.com.chatbotsoftI.entity.EvePaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvePaymentRepository extends JpaRepository<EvePaymentEntity, Integer> {
}
