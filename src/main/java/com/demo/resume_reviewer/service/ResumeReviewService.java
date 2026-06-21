package com.demo.resume_reviewer.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResumeReviewService {
        private final PromptService promptService;
        private final ChatService chatService;
        private final PdfService pdfService;
        private final ChunkingService chunkingService;
        private final EmbeddingModel embeddingModel;
        private final VectorStore vectorStore;

        public ResumeReviewService(
                        PromptService promptService,
                        ChatService chatService,
                        PdfService pdfService,
                        ChunkingService chunkingService,
                        EmbeddingModel embeddingModel,
                        VectorStore vectorStore) {

                this.promptService = promptService;
                this.chatService = chatService;
                this.pdfService = pdfService;
                this.chunkingService = chunkingService;
                this.embeddingModel = embeddingModel;
                this.vectorStore = vectorStore;
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

                // create a document with text and metadata, then chunk it into smaller pieces
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

                // spring ai internally uses the embedding model to create embeddings for each
                // chunk
                // and store them in the vector store
                vectorStore.add(chunks);

                System.out.println(
                                "Stored " +
                                                chunks.size() +
                                                " chunks in VectorStore");

                List<Document> results = vectorStore.similaritySearch(
                                "Spring Security");
                for (Document doc : results) {
                        System.out.println("Found document with metadata: " +
                                        doc.getMetadata());
                        System.out.println(doc.getText());
                        System.out.println("=====================================");
                }

                String prompt = promptService
                                .buildResumeReviewPrompt(
                                                resumeText);

                return chatService.ask(prompt);
        }
}
