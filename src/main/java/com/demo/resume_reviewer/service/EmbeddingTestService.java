package com.demo.resume_reviewer.service;

import java.util.List;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.google.genai.text.GoogleGenAiTextEmbeddingModel;
import org.springframework.ai.model.google.genai.autoconfigure.embedding.GoogleGenAiEmbeddingConnectionAutoConfiguration;
import org.springframework.ai.model.google.genai.autoconfigure.embedding.GoogleGenAiEmbeddingConnectionProperties;
import org.springframework.stereotype.Service;

@Service
public class EmbeddingTestService {

    private final EmbeddingModel embeddingModel;

    public EmbeddingTestService(
            EmbeddingModel embeddingModel) {

        this.embeddingModel = embeddingModel;
    }

    public void test() {

        var response = embeddingModel.embedForResponse(
                List.of(
                        "Spring Boot",
                        "React JS"));

        System.out.println(response);
    }
}