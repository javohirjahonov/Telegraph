package com.example.telegraphback.repository;

import com.example.telegraphback.entity.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContentRepository extends JpaRepository<ContentEntity, UUID> {

}
