package edu.com.chatbotsoftI.dao;

import edu.com.chatbotsoftI.entity.EvePaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvePaymentMethodRepository extends JpaRepository<EvePaymentMethodEntity,Integer> {
}
