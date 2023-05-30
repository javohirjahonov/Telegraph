package com.example.telegraphback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Name;
import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Entity(name = "content_links")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContentLinksEntity extends BaseEntity {
    private String linkTitle;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "content_id", referencedColumnName = "id")
    private ContentEntity contents;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity users;
}
