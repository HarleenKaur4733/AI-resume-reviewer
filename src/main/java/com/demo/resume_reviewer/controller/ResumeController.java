package com.demo.resume_reviewer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.resume_reviewer.dto.ResumeRequest;
import com.demo.resume_reviewer.dto.ResumeResponse;
import com.demo.resume_reviewer.service.ResumeReviewService;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    private final ResumeReviewService resumeReviewService;

    public ResumeController(
            ResumeReviewService resumeReviewService) {

        this.resumeReviewService = resumeReviewService;
    }

    @PostMapping("/review")
    public ResumeResponse review(
            @RequestBody ResumeRequest request) {

        String review = resumeReviewService.reviewResume(
                request.getResumeText());

        return new ResumeResponse(review);
    }
}
