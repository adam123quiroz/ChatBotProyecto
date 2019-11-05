package edu.com.chatbotsoftI.bl;

import edu.com.chatbotsoftI.dao.EvePersonRepository;
import edu.com.chatbotsoftI.domain.EvePerson;
import edu.com.chatbotsoftI.enums.Status;
import edu.com.chatbotsoftI.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonBl {

    private EvePersonRepository userRepository;

    @Autowired
    public PersonBl(EvePersonRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<PersonDto> findAllUsers() {
        List<PersonDto> userDtoList = new ArrayList<>();
        for (EvePerson user :
                userRepository.findAll()) {
            userDtoList.add(new PersonDto(user));
        }
        return userDtoList;
    }

    public EvePerson findById(Integer id) {
        Optional<EvePerson> optional = this.userRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            // Otra alternativa podría ser: crear una nueva persona con valores por defecto y retornar este nuevo objeto
            throw new RuntimeException("Record cannot found for User with ID: " + id);
        }
    }

    public void saveUser(EvePerson user) {
        userRepository.save(user);
    }


}
