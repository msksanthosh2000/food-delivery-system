package com.sandydev.userinformation.service;

import com.sandydev.userinformation.dto.UserDTO;
import com.sandydev.userinformation.entity.User;
import com.sandydev.userinformation.mapper.UserMapper;
import com.sandydev.userinformation.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    UserMapper mapper;

    public UserDTO saveUser(UserDTO userDTO) {

        User saved = repository.save(mapper.mapUserDTOToUser(userDTO));

        return mapper.mapUserToUserDTO(saved);
    }

    public ResponseEntity<UserDTO> fetchUserById(Integer id){
        Optional<User> fetchedUser = repository.findById(id);

        if(fetchedUser.isPresent()){
            UserDTO userDTO = mapper.mapUserToUserDTO(fetchedUser.get());
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>((HttpHeaders) null, HttpStatus.NOT_FOUND);
    }
}
