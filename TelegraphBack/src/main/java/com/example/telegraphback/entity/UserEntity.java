package com.example.telegraphback.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity extends BaseEntity {
    private String name;
    private String phoneNumber;
    private String password;
}
