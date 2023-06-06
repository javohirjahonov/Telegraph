package com.example.telegraphback.repository;

import com.example.telegraphback.entity.ContentLinksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LinkRepository extends JpaRepository<ContentLinksEntity, UUID> {

    List<ContentLinksEntity> findByUsersId(UUID userId);
}
