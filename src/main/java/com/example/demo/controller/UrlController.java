package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.UrlDTO;
import com.example.demo.service.UrlService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

@Validated
@RestController
@CrossOrigin(origins = "*")
public class UrlController {

   private final UrlService urlService;

    public UrlController(UrlService urlService){
        this.urlService = urlService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UrlDTO tinyUrl(@RequestBody @NotEmpty @Valid UrlDTO urlDTO){
        return this.urlService.tinyUrl(urlDTO);
    }

    @GetMapping("/{url}")
    @ResponseStatus(code = HttpStatus.TEMPORARY_REDIRECT)
    public ModelAndView findByShortURL(@PathVariable String url) {
        return this.urlService.findByShortURL(url);
    }
}
