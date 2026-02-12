package com.chase.demo.note_api.model;

import java.time.Instant;
import java.util.UUID;

public record Note (
    UUID id, // UUID is a class from java.UUID, represent 128-bit value, practically gobally unique
    String title,
    String content,
    Instant createdAt // Instant is a class from java.time, represents a moment on the timeline in UTC, with nanosecond precision.
){}



