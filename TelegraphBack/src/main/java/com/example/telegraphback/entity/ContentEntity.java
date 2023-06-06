package com.example.telegraphback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private UserEntity users;

    @OneToOne(mappedBy = "contents", cascade = CascadeType.ALL)
    private ContentLinksEntity links;

}
