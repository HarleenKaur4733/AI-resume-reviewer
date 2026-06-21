package com.demo.resume_reviewer.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResumeReviewService {
    private final PromptService promptService;
    private final ChatService chatService;
    private final PdfService pdfService;
    private final ChunkingService chunkingService;
    private final EmbeddingModel embeddingModel;

    public ResumeReviewService(
            PromptService promptService,
            ChatService chatService,
            PdfService pdfService,
            ChunkingService chunkingService,
            EmbeddingModel embeddingModel) {

        this.promptService = promptService;
        this.chatService = chatService;
        this.pdfService = pdfService;
        this.chunkingService = chunkingService;
        this.embeddingModel = embeddingModel;
    }

    public String reviewResume(
            MultipartFile file)
            throws IOException {

        String resumeText = pdfService.extractText(file);

        // document is text + metadata
        // Mostly rag components use document as input to the vector store
        Map<String, Object> metadata = new HashMap<>();

        metadata.put("fileName", file.getOriginalFilename());
        metadata.put("documentType", "resume");

        Document resumeDocument = new Document(resumeText, metadata);
        List<Document> chunks = chunkingService.chunk(resumeDocument);

        // System.out.println("Total Chunks = " + chunks.size());

        // for (int i = 0; i < chunks.size(); i++) {
        // System.out.println("\n========== CHUNK " + (i + 1) + " ==========");

        // String text = chunks.get(i).getText();

        // System.out.println(
        // text.substring(
        // 0,
        // Math.min(300, text.length())));

        // System.out.println("\n=============================");
        // }

        // System.out.println(resumeDocument.getText());

        // System.out.println(
        // resumeDocument.getMetadata());

        Document firstChunk = chunks.get(0);

        float[] embedding = embeddingModel.embed(
                firstChunk.getText());

        System.out.println(
                "Vector Size = "
                        + embedding.length);

        String prompt = promptService
                .buildResumeReviewPrompt(
                        resumeText);

        return chatService.ask(prompt);
    }
}
