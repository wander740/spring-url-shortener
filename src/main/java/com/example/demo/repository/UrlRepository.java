package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Url;

public interface UrlRepository extends JpaRepository<Url, Long>{
    Optional<Url> findByLongURL(String longURL);
    Optional<Url> findByShortURL(String shortURL);
}