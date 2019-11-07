package edu.com.chatbotsoftI.dao;

import edu.com.chatbotsoftI.domain.Eveuser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository <Eveuser, Integer> {
    List<Eveuser> findAllByStatus(int status);
//    User findByIduser(Integer iduser);
    Eveuser findByNameuser(String nameUser);
}
