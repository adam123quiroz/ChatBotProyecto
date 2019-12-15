package edu.com.chatbotsoftI.dao;

import edu.com.chatbotsoftI.entity.EvePersonChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.mail.internet.InternetAddress;

public interface EvePersonChatRepository extends JpaRepository<EvePersonChatEntity,Integer> {

   // @Query(value = "SELECT * FROM evepersonchat where  idperson = ?1 ORDER BY idevuserchat DESC LIMIT 1", nativeQuery = true)
    //public EvePersonChatEntity findLastChatByEvepersonByIdperson(Integer userId);
    public EvePersonChatEntity findLastChatByIdevuserchat(Integer userId);

//    public EvePersonChatEntity findByEvepersonByIdperson(Integer userid);
   // EvePersonChatEntity findByEvepersonByIdpersonAndAndUserbotchatid(Integer userid, String userbotchatid);
  //  EvePersonChatEntity findByUserbotchatid(String userbotchatid);
}
