package com.example.telegraphback.service.UserService;

import com.example.telegraphback.dto.UserCreateDto;
import com.example.telegraphback.entity.UserEntity;
import com.example.telegraphback.exceptions.UniqueException;
import com.example.telegraphback.exceptions.UserCheckException;
import com.example.telegraphback.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.postgresql.util.PSQLException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final EntityManager entityManager;

    @Override
    public UserEntity add(UserCreateDto userCreateDto) {
        UserEntity userEntity = modelMapper.map(userCreateDto, UserEntity.class);
        if (userRepository.existsByPhoneNumber(userCreateDto.getPhoneNumber())) {
            throw new UniqueException("Phone number already exists");
        }
        userRepository.save(userEntity);
        return userEntity;
    }


        @Override
        public UserEntity update (UserCreateDto userCreateDto, UUID id){
            return null;
        }

        @Override
        public void delete (UUID id){
            userRepository.deleteById(id);
        }

        @Override
        public void getById (UUID id){
            userRepository.findById(id);
        }

        @Override
        public UserEntity signIn (String phoneNumber, String password){
            UserEntity userEntity = userRepository.findUserEntitiesByPhoneNumber(phoneNumber)
                    .orElseThrow(
                            () -> new UserCheckException("Phone not found"));
            if (userEntity.getPassword().equals(password)) {
                return userEntity;
            }
            throw new UserCheckException("Password wrong");
        }
    }
