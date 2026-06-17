package com.demo.resume_reviewer.service;

import org.springframework.stereotype.Service;

@Service
public class PromptService {

    public String buildResumeReviewPrompt(String resumeText) {

        return """
                You are a senior technical recruiter.

                Review the following resume.

                Provide:
                1. Strengths
                2. Weaknesses
                3. Missing Skills
                4. Improvement Suggestions

                Resume:

                %s
                """.formatted(resumeText);
    }
}