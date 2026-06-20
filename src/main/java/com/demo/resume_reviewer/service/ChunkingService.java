package com.demo.resume_reviewer.service;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.stereotype.Service;

@Service
public class ChunkingService {

    public List<Document> chunk(Document document) {

        TokenTextSplitter splitter = TokenTextSplitter.builder()
                .withChunkSize(300)
                .withMinChunkSizeChars(100)
                .build();

        return splitter.apply(List.of(document));
    }
}