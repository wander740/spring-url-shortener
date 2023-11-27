package com.example.demo.model;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Url {
    @Id
    private Long id;
    @NotBlank
    @NotNull
    @Length(min = 4, max = 200)
    @Column(length = 200, nullable = false)
    private String shortURL;
    private String longURL;
}

