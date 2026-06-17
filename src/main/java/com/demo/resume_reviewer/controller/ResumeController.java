package com.demo.resume_reviewer.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/review-pdf")
    public ResumeResponse reviewPdf(
            @RequestParam("file") MultipartFile file)
            throws IOException {

        String review = resumeReviewService.reviewResume(file);

        return new ResumeResponse(review);
    }
}
