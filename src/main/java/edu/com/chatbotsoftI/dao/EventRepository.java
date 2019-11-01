package edu.com.chatbotsoftI.dao;

import edu.com.chatbotsoftI.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findAllByStatus(int status);
    List<Event> findAllByIdcategory_Category(String category);
}

