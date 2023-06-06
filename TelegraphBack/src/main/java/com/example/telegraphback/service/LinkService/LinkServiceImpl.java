package com.example.telegraphback.service.LinkService;

import com.example.telegraphback.entity.ContentEntity;
import com.example.telegraphback.entity.ContentLinksEntity;
import com.example.telegraphback.entity.UserEntity;
import com.example.telegraphback.repository.ContentRepository;
import com.example.telegraphback.repository.LinkRepository;
import com.example.telegraphback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {

    private final ContentRepository contentRepository;
    private final LinkRepository linkRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public ContentLinksEntity addLinkForContent(ContentLinksEntity contentLinksEntity, UUID contentId, UUID userId) {
        ContentEntity contentEntity = contentRepository.findById(contentId)
                .orElseThrow(() -> new IllegalArgumentException("Content not found: " + contentId));

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));


        contentLinksEntity.setLinkTitle(String.join("-", contentEntity.getTitle(), LocalDateTime.now().toString()));
        contentLinksEntity.setContents(contentEntity);
        contentLinksEntity.setUsers(userEntity);
        linkRepository.save(contentLinksEntity);
        return contentLinksEntity;
    }


    @Override
    public ContentLinksEntity add(ContentLinksEntity contentLinksEntity) {
        return null;
    }

    @Override
    public ContentLinksEntity update(ContentLinksEntity contentLinksEntity, UUID id) {
        return null;
    }

    @Override
    public List<ContentLinksEntity> getUserContentLinks(UUID userId) {
        return linkRepository.findByUsersId(userId);
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void getById(UUID id) {

    }

}
