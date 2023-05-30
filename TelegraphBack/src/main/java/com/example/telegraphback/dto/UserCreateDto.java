package com.example.telegraphback.dto;

import jakarta.persistence.Column;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {
    @NotEmpty(message = "Name must not be empty")
    private String name;

    @NotEmpty(message = " PhoneNumber must not be empty and only 7 numbers ")
    @Pattern(regexp = "^\\d{9}$")
    private String phoneNumber;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).+$")
    @NotEmpty(message = "Password must not be empty")
    private String password;
}
