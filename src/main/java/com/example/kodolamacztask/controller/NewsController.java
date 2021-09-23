package com.example.kodolamacztask.controller;

import com.example.kodolamacztask.service.NewsSaver;
import com.example.kodolamacztask.model.Article;
import com.example.kodolamacztask.model.Root;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class NewsController {

    private final NewsSaver newsSaver;

    public NewsController(NewsSaver newsSaver) {
        this.newsSaver = newsSaver;
    }

    @GetMapping("/news")
    public ResponseEntity<List<Article>> getNews(@RequestParam String country, @RequestParam String category) {
        final String API_KEY = "16c1b5eb58e9431495799bdb164ea848";
        String apiResourceUrl = "https://newsapi.org/v2/top-headlines?" +
                "country=" + country +
                "&category=" + category +
                "&apiKey=" + API_KEY;
        Root response = new RestTemplate().getForObject(apiResourceUrl, Root.class);

        assert response != null;
        newsSaver.saveToFile(response, "result.txt");

        return new ResponseEntity<>(response.articles, HttpStatus.OK);
    }
}
