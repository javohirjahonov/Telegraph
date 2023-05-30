package com.example.telegraphback.service.LinkService;

import com.example.telegraphback.entity.ContentLinksEntity;
import com.example.telegraphback.service.BaseService;

import java.util.List;
import java.util.UUID;

public interface LinkService extends BaseService<ContentLinksEntity, ContentLinksEntity> {
    ContentLinksEntity addLinkForContent(ContentLinksEntity contentLinksEntity, UUID contentId, UUID userId);

    List<ContentLinksEntity> getUserContentLinks(UUID userId);

}
