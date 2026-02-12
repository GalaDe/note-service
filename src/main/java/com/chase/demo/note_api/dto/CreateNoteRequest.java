package com.chase.demo.note_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//CreateNoteRequest created to handle incoming data, controlled by client
//Purpose:
// - Represents input coming from the client
// - NOT your domain model
// - NOT your database entity
public record CreateNoteRequest(
    //This is Bean Validation (Jakarta Validation).
    @NotBlank  //rejects null, "", " "
    @Size(max=100) //enforces max length, prevents abuse
    String title,
    String content

){}