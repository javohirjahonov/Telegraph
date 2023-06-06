package com.example.telegraphback.repository;

import com.example.telegraphback.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findUserEntitiesByPhoneNumber(String phoneNumber);

    Optional<UserEntity> findUserEntitiesByName(String name);

    boolean existsByPhoneNumber(String phoneNumber);

    @Query(value = "SELECT u FROM users u WHERE u.id IN (SELECT u.id FROM contents WHERE u.id = :id)")
    List<UserEntity> findByContentsId(UUID id);

    Optional<UserEntity> findByPhoneNumber(String phoneNumber);

}
