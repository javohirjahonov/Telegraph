package com.example.telegraphback.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity(name = "contents")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContentEntity extends BaseEntity {

    private String title;
    private String author;
    private String content;
}
