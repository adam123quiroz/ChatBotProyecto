package edu.com.chatbotsoftI.dao;


import edu.com.chatbotsoftI.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

//@RestResource(exported = false)
public interface UserRepository extends JpaRepository <User, Integer> {


}
