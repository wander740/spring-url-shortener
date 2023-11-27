package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.UrlDTO;
import com.example.demo.dto.mapper.UrlMapper;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.generateid.GenerateAdapter;
import com.example.demo.repository.UrlRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

@Validated
@Service
public class UrlService {

    @Value("${api.url}")
    private String urlApi;
    private final UrlRepository urlRepository;
    private final UrlMapper urlMapper;
    private final GenerateAdapter generateAdapter;
    private String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public UrlService(UrlRepository urlRepository, UrlMapper urlMapper, GenerateAdapter generateAdapter){
        this.urlRepository = urlRepository;
        this.urlMapper = urlMapper;
        this.generateAdapter = generateAdapter;
    }

    public UrlDTO tinyUrl(@NotEmpty @Valid UrlDTO url){
        var findUrl = this.urlRepository.findByLongURL(url.url());
        if(findUrl.isPresent()) {
            return UrlDTO.builder().url(urlApi.concat(findUrl.get().getShortURL())).build();
        }

        var id = generateAdapter.generate();
        var tinyUrl = sixTwoConversion(id);
        this.urlRepository.save(urlMapper.toEntity(url, tinyUrl, id));
        return UrlDTO.builder().url(urlApi.concat(tinyUrl)).build();
    }

    public ModelAndView findByShortURL(String url){
        var findUrl = this.urlRepository.findByShortURL(url).orElseThrow(() -> new RecordNotFoundException(url));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + findUrl.getLongURL());
        return modelAndView;
        
    }

    private String sixTwoConversion(Long num){
        StringBuilder stringBuilder = new StringBuilder(1);
        do {
            stringBuilder.insert(0, characters.charAt((int) (num % characters.length())));
            num /= characters.length();
        } while (num > 0);
        return stringBuilder.toString();
    }
}