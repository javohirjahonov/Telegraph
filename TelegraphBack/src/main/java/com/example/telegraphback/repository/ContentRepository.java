package com.example.telegraphback.repository;

import com.example.telegraphback.entity.ContentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ContentRepository extends JpaRepository<ContentEntity, UUID> {
    List<ContentEntity> findContentEntitiesByUsersId(UUID userId);

    List<ContentEntity> findAllByUsersId(Pageable pageable, UUID userId);

//    List<ContentEntity> findContentEntitiesByTitleContainingIgnoreCaseOrCreatedDateContainingIgnoreCase(String title, LocalDateTime date);

    List<ContentEntity> findContentEntitiesByTitleContainsIgnoreCaseOrAuthorContainingIgnoreCaseOrContentIsContainingIgnoreCaseOrUsersId(Pageable pageable, String title, String author, String content, UUID userId);

    ContentEntity findContentEntityByUsersId(UUID userId);
//    findGiftCertificatesByTagsContainsAndNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase
}
