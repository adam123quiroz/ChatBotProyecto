package edu.com.chatbotsoftI.dao;

import edu.com.chatbotsoftI.entity.EveChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EveChatRepository extends JpaRepository<EveChatEntity, Integer> {
    // JPQL SELECT ch FROM CpChat ch WHERE ch.cpUserUserId.userId = 1 ORDER BY ch.chatId DESC <<LIMIT 1>>
    @Query(value = "SELECT * FROM eve_chat where  id_eve_user_chat = ?1 ORDER BY id_eve_user_chat DESC LIMIT 1", nativeQuery = true)
    EveChatEntity findLastChatByUserId(Integer userId);
}
