package com.example.telegraphback.service.contentService;

import com.example.telegraphback.dto.ContentCreateDto;
import com.example.telegraphback.entity.ContentEntity;
import com.example.telegraphback.entity.UserEntity;
import com.example.telegraphback.service.BaseService;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ContentService extends BaseService<ContentEntity, ContentCreateDto> {
    ContentEntity addContent(ContentCreateDto contentCreateDto, UUID ownerId);

    List<ContentEntity> getUserContentsWithPageAble(int page, int size, String sort, UUID userId);

    List<ContentEntity> getUserContents(UUID userId);


    List<ContentEntity> search(int page, int size, String title, String author, String content, UUID userId);

    ContentEntity getContentById(UUID contentId);

}
