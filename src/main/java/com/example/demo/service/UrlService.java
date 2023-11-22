package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.UrlDTO;
import com.example.demo.dto.mapper.UrlMapper;
import com.example.demo.repository.UrlRepository;

@Service
public class UrlService {

    @Value("${api.url}")
    private String urlApi;
    private final UrlRepository urlRepository;
    private final UrlMapper urlMapper;
    private String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public UrlService(UrlRepository urlRepository, UrlMapper urlMapper){
        this.urlRepository = urlRepository;
        this.urlMapper = urlMapper;
    }

    public UrlDTO tinyUrl(UrlDTO url){
        var findUrl = this.urlRepository.findByLongURL(url.url());
        if(findUrl.isPresent()) {
            return UrlDTO.builder().url(urlApi.concat(findUrl.get().getShortURL())).build();
        }

        // TODO: passa o id da url gerada
        //var id = new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16);
        //System.out.println("id> "+id);
        var tinyUrl = sixTwoConversion(11157L);
        this.urlRepository.save(urlMapper.toEntity(url, tinyUrl));
        return UrlDTO.builder().url(urlApi.concat(tinyUrl)).build();
    }

    public ModelAndView findByShortURL(String url){
        var findUrl = this.urlRepository.findByShortURL(url).orElseThrow();

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