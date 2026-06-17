package com.demo.resume_reviewer.dto;

public class ResumeResponse {

    private String review;

    public ResumeResponse() {
    }

    public ResumeResponse(String review) {
        this.review = review;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
