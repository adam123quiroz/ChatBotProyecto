package edu.com.chatbotsoftI.controller;

import edu.com.chatbotsoftI.dao.UserRepository;
import edu.com.chatbotsoftI.domain.User;
import edu.com.chatbotsoftI.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

//import java.util.List;
import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    //  @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @RequestMapping(value = "/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserDto> all() {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user:userRepository.findAll()) {
            userDtoList.add(new UserDto(user));
        }
        return userDtoList;
    }
/*
    @GetMapping("/user")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{iduser}")
   public Optional<User> find(@PathVariable int iduser) {
        Optional<User> user = userRepository.findById(iduser);
        //return userRepository.findOne(iduser);
        return user;
        /*UserEntity user= UserRepository.(iduser);
        UserEntity user = userRepository.findById(iduser);
        if (user ==null)
        {
            throw new RuntimeException("User id not found "+iduser);
        }
        return user;


    }
    @PostMapping("application/json")
    public User create(@RequestBody User newuser){
        return  userRepository.save(newuser);
    }

    @DeleteMapping("/{iduser}")
    public void delete(@PathVariable("iduser") int iduser){
        userRepository.deleteById(iduser);
    }

    @PutMapping("/user/{iduser}")
    public  User update(@PathVariable int iduser, @RequestBody Map<User, Integer > body) {
        User user = userRepository.getOne(iduser);
            user.setIduser(iduser);
            return userRepository.save(user);

    }
*/
}



