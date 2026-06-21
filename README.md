# Resume Intelligence System with RAG

A Spring Boot application that uses Retrieval-Augmented Generation (RAG) to analyze resumes and answer context-aware questions about uploaded candidate profiles.

## Features

* Upload and process PDF resumes
* Extract resume content using PDF parsing
* Split documents into semantic chunks for efficient retrieval
* Generate vector embeddings using Google Gemini Embedding Models
* Store embeddings in a Vector Store
* Perform semantic similarity search on resume content
* Answer user questions using Retrieval-Augmented Generation (RAG)
* Generate AI-powered resume reviews and insights

## Tech Stack

* Java 17
* Spring Boot
* Spring AI
* Google Gemini
* PDFBox
* SimpleVectorStore
* Maven

## RAG Pipeline

```text
PDF Resume
    ↓
Text Extraction
    ↓
Document Creation
    ↓
Chunking
    ↓
Embeddings
    ↓
Vector Store
    ↓
Similarity Search
    ↓
Context Retrieval
    ↓
Gemini
    ↓
AI Generated Response
```
<img width="1000" height="382" alt="image" src="https://github.com/user-attachments/assets/7875c261-167b-4483-820d-d3453f9ad9b2" />

## Example Queries

* Does the candidate have backend development experience?
* What technologies has the candidate worked with?
* Does the candidate have experience with Spring Security?
* What projects demonstrate leadership or ownership?
* Is the candidate suitable for a Backend Developer role?

## Key Concepts Implemented

* Document Processing
* Metadata Management
* Chunking with TokenTextSplitter
* Embedding Generation
* Vector Storage
* Semantic Search
* Retrieval-Augmented Generation (RAG)

## API Endpoints

### Upload and Index Resume

```http
POST /resume/review-pdf
```

Uploads a resume, extracts content, generates embeddings, and stores them in the Vector Store.

### Ask Questions

```http
POST /rag/ask
```

Request:

```json
{
  "question": "Does the candidate have experience with Spring Security?"
}
```

Response:

```json
{
  "answer": "Yes, the candidate has experience with Spring Security, JWT authentication, and role-based access control."
}
```


