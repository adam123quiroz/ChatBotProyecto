package edu.com.chatbotsoftI.dao;

import edu.com.chatbotsoftI.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

//@RestResource(exported = false)
public interface UserRepository extends JpaRepository <User, Integer> {
    List<User> findAllByStatus(int status);
    User findByNameuser(String nameUser);
}
