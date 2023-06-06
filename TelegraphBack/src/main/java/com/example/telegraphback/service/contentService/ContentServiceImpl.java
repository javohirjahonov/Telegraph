package com.example.telegraphback.service.contentService;

import com.example.telegraphback.dto.ContentCreateDto;
import com.example.telegraphback.entity.ContentEntity;
import com.example.telegraphback.entity.ContentLinksEntity;
import com.example.telegraphback.entity.UserEntity;
import com.example.telegraphback.repository.ContentRepository;
import com.example.telegraphback.repository.LinkRepository;
import com.example.telegraphback.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;
    private final UserRepository userRepository;
    private final LinkRepository linkRepository;
    private final ModelMapper modelMapper;
    private final EntityManager entityManager;

    @Override
    public ContentEntity addContent(ContentCreateDto contentCreateDto, UUID ownerId) {
        ContentEntity contentEntity = modelMapper.map(contentCreateDto, ContentEntity.class);

        UserEntity userEntity = userRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + ownerId));

        if (!userEntity.getContents().contains(contentEntity)) {
            userEntity.getContents().add(contentEntity);
            ContentLinksEntity contentLinksEntity = new ContentLinksEntity();
            contentLinksEntity.setLinkTitle(String.join("-", contentEntity.getTitle(), LocalDateTime.now().toString()));
            contentLinksEntity.setContents(contentEntity);
            contentLinksEntity.setUsers(userEntity);
            contentEntity.setLinks(contentLinksEntity);
            contentEntity.setUsers(userEntity);
            contentRepository.save(contentEntity);
            linkRepository.save(contentLinksEntity);
        }
        return contentEntity;
    }


    @Override
    public ContentEntity add(ContentCreateDto contentCreateDto) {
        ContentEntity contentEntity = modelMapper.map(contentCreateDto, ContentEntity.class);

        contentRepository.save(contentEntity);
        return contentEntity;
    }

    @Override
    public ContentEntity update(ContentCreateDto contentCreateDto, UUID id) {
        ContentEntity contentEntity = modelMapper.map(contentCreateDto, ContentEntity.class);
        ContentEntity content = contentRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Not found"));

        if (contentCreateDto.getTitle() != null) {
            content.setTitle(contentCreateDto.getTitle());
        }
        if (contentCreateDto.getAuthor() != null) {
            content.setAuthor(contentCreateDto.getAuthor());
        }
        if (contentCreateDto.getContent() != null) {
            content.setContent(contentCreateDto.getContent());
        }
        content.setId(id);
        contentEntity.setId(id);
        contentRepository.save(content);
        return contentEntity;
    }

    @Override
    public ContentEntity getContentById(UUID contentId) {
        return contentRepository.findContentEntityByUsersId(contentId);
    }

    @Override
    public void delete(UUID contentId) {
        ContentEntity contentEntity = contentRepository.findById(contentId)
                .orElseThrow(() -> new NullPointerException("No such content"));
        if (contentEntity.getId().equals(contentId)) {
            contentRepository.deleteById(contentId);
        } else {
            throw new NullPointerException("Content not fount");
        }
    }

    @Override
    public void getById(UUID id) {
        contentRepository.findById(id);
    }

    @Override
    public List<ContentEntity> getUserContentsWithPageAble(int page, int size, String sort, UUID userId) {
        Sort sort1 = Sort.by(Sort.Direction.ASC, sort);
        Pageable pageable = PageRequest.of(page, size, sort1);
        return contentRepository.findAllByUsersId(pageable, userId);
    }

    @Override
    public List<ContentEntity> getUserContents(UUID userId) {
        return contentRepository.findContentEntitiesByUsersId(userId);
    }

    @Override
    public List<ContentEntity> search(int page, int size, String title, String author, String content, UUID userId) {
        Pageable pageable = PageRequest.of(page, size);
        return contentRepository.findContentEntitiesByTitleContainsIgnoreCaseOrAuthorContainingIgnoreCaseOrContentIsContainingIgnoreCaseOrUsersId(pageable, title, author, content, userId);
    }
}
