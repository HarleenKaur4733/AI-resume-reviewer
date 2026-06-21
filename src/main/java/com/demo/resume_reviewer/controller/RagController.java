package com.demo.resume_reviewer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.resume_reviewer.dto.QuestionRequest;
import com.demo.resume_reviewer.service.RagService;

@RestController
@RequestMapping("/rag")
public class RagController {

    private final RagService ragService;

    public RagController(RagService ragService) {
        this.ragService = ragService;
    }

    @PostMapping("/ask")
    public String ask(
            @RequestBody QuestionRequest request) {

        return ragService.ask(
                request.getQuestion());
    }
}