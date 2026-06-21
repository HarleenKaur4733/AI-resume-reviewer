package com.demo.resume_reviewer.service;

import org.springframework.stereotype.Service;

@Service
public class RagService {

    private final RetrievalService retrievalService;
    private final ChatService chatService;

    public RagService(
            RetrievalService retrievalService,
            ChatService chatService) {

        this.retrievalService = retrievalService;
        this.chatService = chatService;
    }

    public String ask(String question) {

        String context = retrievalService.getContext(question);

        // augment the question with the context and ask the chat service to get the
        // answer
        String prompt = """
                Answer the question using only the provided context.

                Context:
                %s

                Question:
                %s
                """
                .formatted(context, question);
        System.out.println("CONTEXT:");
        System.out.println(context);
        return chatService.ask(prompt);
    }
}