package com.example.telegraphback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity extends BaseEntity {
    private String name;

    @Column(unique = true)
    private String phoneNumber;

    private String password;


    @OneToMany(cascade = CascadeType.ALL)
    private List<ContentEntity> contents;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ContentLinksEntity> links;

}
