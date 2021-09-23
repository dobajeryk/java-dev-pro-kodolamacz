package com.example.kodolamacztask.service;

import com.example.kodolamacztask.model.Root;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class NewsSaver {

    public void saveToFile(Root response, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            assert response != null;
            response.articles
                    .forEach(article -> {
                        try {
                            writer.append(article.title).append(":").append(article.description).append(":").append(article.author).append("\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
