package com.demo.resume_reviewer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.resume_reviewer.service.ChatService;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    public String ask(@RequestParam String question) {
        return chatService.ask(question);
    }

    @GetMapping("/env")
    public String env() {
        return System.getenv("GEMINI_API_KEY");
    }
}
