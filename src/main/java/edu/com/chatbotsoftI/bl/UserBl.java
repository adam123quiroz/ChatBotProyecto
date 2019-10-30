package edu.com.chatbotsoftI.bl;

import edu.com.chatbotsoftI.dao.UserRepository;
import edu.com.chatbotsoftI.domain.User;
import edu.com.chatbotsoftI.dto.Status;
import edu.com.chatbotsoftI.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserBl {

    private UserRepository userRepository;

    @Autowired
    public UserBl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> findAllUsers() {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user :
                userRepository.findAllByStatus(Status.ACTIVE.getStatus())) {
            userDtoList.add(new UserDto(user));
        }
        return userDtoList;
    }

    public User findById(Integer id) {
        Optional<User> optional = this.userRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            // Otra alternativa podría ser: crear una nueva persona con valores por defecto y retornar este nuevo objeto
            throw new RuntimeException("Record cannot found for User with ID: " + id);
        }
    }

    public User findByUsername(String nameUser) {
        return userRepository.findByNameuser(nameUser);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

}
