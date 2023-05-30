package com.example.telegraphback.controller;

import com.example.telegraphback.dto.ContentCreateDto;
import com.example.telegraphback.entity.ContentEntity;
import com.example.telegraphback.entity.ContentLinksEntity;
import com.example.telegraphback.service.LinkService.LinkService;
import com.example.telegraphback.service.UserService.UserService;
import com.example.telegraphback.service.contentService.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ContentController {

    private final UserService userService;
    private final ContentService contentService;
    private final LinkService linkService;

    @PostMapping("/api/v1/add-content")
    public String addContent(
            @RequestParam UUID id,
            @RequestBody ContentCreateDto contentCreateDto
    ) {
        contentService.addContent(contentCreateDto, id);

        return "content add";
    }

    @DeleteMapping("/api/v1/delete-content")
    public String deleteContentPage(
            @RequestParam UUID id
    ) {
        contentService.delete(id);
        return "content successfully deleted";
    }

    @GetMapping("/api/v1/update-content")
    public String updatedContent(
            @RequestParam UUID id,
            @RequestBody ContentCreateDto contentCreateDto

    ) {
        contentService.update(contentCreateDto, id);
        return "Content updated";
    }

    @GetMapping("/api/v1/get-user-contents")
    public List<ContentEntity> getUserContents(
            @RequestParam UUID id
    ) {
        return contentService.getUserContents(id);
    }

    @GetMapping("/api/v1/get-user-contents-with-pageable")
    public List<ContentEntity> getUserContents(
            @RequestParam UUID id,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sort,
            Model model
    ) {
        return contentService.getUserContentsWithPageAble(page, size,sort,id);
    }


    @GetMapping("/api/v1/get-user-links")
    public List<ContentLinksEntity> getUserLinks(
            @RequestParam UUID userId,
            Model model
    ) {
        return linkService.getUserContentLinks(userId);
    }

    @GetMapping("/api/v1/get-content-by-id")
    public ContentEntity getContentById(
            @RequestParam UUID id
    ) {
        return contentService.getContentById(id);
    }

    @GetMapping("/search-contents")
    public List<ContentEntity> search(
            @RequestParam UUID id,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam String content,
            Model model
    ) {
        return contentService.search(page, size, title, author, content, id);
    }


}
