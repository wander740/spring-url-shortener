package com.example.demo.dto.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.UrlDTO;
import com.example.demo.model.Url;

@Component
public class UrlMapper {
    public Url toEntity(UrlDTO url, String tinyUrl){
        if(url == null) return null;
        return Url.builder()
            .longURL(url.url())
            .shortURL(tinyUrl)
            .build();
    }
}
