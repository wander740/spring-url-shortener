package com.example.demo.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UrlDTO(
    @NotBlank @NotNull @Length(min = 4, max = 200) String url
) {
    
}

