package com.demo.resume_reviewer.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResumeReviewService {
    private final PromptService promptService;
    private final ChatService chatService;
    private final PdfService pdfService;

    public ResumeReviewService(
            PromptService promptService,
            ChatService chatService,
            PdfService pdfService) {

        this.promptService = promptService;
        this.chatService = chatService;
        this.pdfService = pdfService;
    }

    public String reviewResume(
            MultipartFile file)
            throws IOException {

        String resumeText = pdfService.extractText(file);

        String prompt = promptService
                .buildResumeReviewPrompt(
                        resumeText);

        return chatService.ask(prompt);
    }
}
