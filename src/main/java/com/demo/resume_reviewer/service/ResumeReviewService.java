package com.demo.resume_reviewer.service;

import org.springframework.stereotype.Service;

@Service
public class ResumeReviewService {
    private final PromptService promptService;
    private final ChatService chatService;

    public ResumeReviewService(
            PromptService promptService,
            ChatService chatService) {

        this.promptService = promptService;
        this.chatService = chatService;
    }

    public String reviewResume(String resumeText) {

        String prompt = promptService.buildResumeReviewPrompt(resumeText);

        return chatService.ask(prompt);
    }
}
