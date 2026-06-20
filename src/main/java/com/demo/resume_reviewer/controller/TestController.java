package com.demo.resume_reviewer.controller;

import org.springframework.ai.document.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/document")
    public String test() {

        String text = """
                Harleen Kaur

                Skills:
                Java
                Spring Boot
                React

                Projects:
                Explore Horizon
                """;

        Document document = new Document(text);

        return document.getText();
    }
}
