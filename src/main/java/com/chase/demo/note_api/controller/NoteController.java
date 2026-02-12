package com.chase.demo.note_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.chase.demo.note_api.dto.CreateNoteRequest;
import com.chase.demo.note_api.dto.NoteResponse;
import com.chase.demo.note_api.model.Note;
import com.chase.demo.note_api.service.NoteService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController //tells Spring this class handles REST requests and returns data (like JSON)
@RequestMapping("/notes")//defines the base path for your API routes
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NoteResponse createNote(@Valid @RequestBody CreateNoteRequest req) {
        return toResponse(service.createNote(req));
    }

    @GetMapping("/{id}")
    public NoteResponse getNoteByID(@PathVariable UUID id) {
        return toResponse(service.getNoteByID(id));
    }

    @GetMapping
    public List<NoteResponse> getAllNotes() {
        return service.getAllNotes().stream().map(this::toResponse).toList();
    }
    
    private NoteResponse toResponse(Note t){
        return new NoteResponse(t.id(), t.title(), t.content(), t.createdAt());
    }
}
