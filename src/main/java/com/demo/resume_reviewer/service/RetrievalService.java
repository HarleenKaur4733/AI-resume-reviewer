package com.demo.resume_reviewer.service;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
public class RetrievalService {

    private final VectorStore vectorStore;

    public RetrievalService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    // retreival service to get context from the vector store based on the question
    public String getContext(String question) {

        List<Document> docs = vectorStore.similaritySearch(question);

        return docs.stream()
                .map(Document::getText)
                .reduce("", (a, b) -> a + "\n\n" + b);
    }
}