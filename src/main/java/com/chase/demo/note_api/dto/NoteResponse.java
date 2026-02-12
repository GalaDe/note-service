package com.chase.demo.note_api.dto;

import java.time.Instant;
import java.util.UUID;

//NoteResponse created to handle outgoing data, controlled by server
public record NoteResponse(
    UUID id,
    String title,
    String content,
    Instant createdAt
){}
