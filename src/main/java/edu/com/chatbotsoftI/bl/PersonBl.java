package edu.com.chatbotsoftI.bl;

import edu.com.chatbotsoftI.dao.EvePersonRepository;
import edu.com.chatbotsoftI.dto.PersonDto;
import edu.com.chatbotsoftI.entity.EvePersonEntity;
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
        for (EvePersonEntity user :
                userRepository.findAll()) {
            userDtoList.add(new PersonDto(user));
        }
        return userDtoList;
    }

    public EvePersonEntity findById(Integer id) {
        Optional<EvePersonEntity> optional = this.userRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            // Otra alternativa podría ser: crear una nueva persona con valores por defecto y retornar este nuevo objeto
            throw new RuntimeException("Record cannot found for User with ID: " + id);
        }
    }

    public void saveUser(EvePersonEntity user) {
        userRepository.save(user);
    }


}
