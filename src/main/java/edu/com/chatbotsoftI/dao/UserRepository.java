package edu.com.chatbotsoftI.dao;

import edu.com.chatbotsoftI.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository <User, Integer> {
    List<User> findAllByStatus(int status);
    User findByNameuser(String nameUser);
}
