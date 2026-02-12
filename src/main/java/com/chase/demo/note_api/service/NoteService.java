package com.chase.demo.note_api.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.chase.demo.note_api.dto.CreateNoteRequest;
import com.chase.demo.note_api.exception.NoteNotFoundException;
import com.chase.demo.note_api.model.Note;
import com.chase.demo.note_api.repository.NoteRepository;

@Service
public class NoteService {

    // Create field called repo of type NoteRepository, at this point, repo is uninitialized
    private final NoteRepository repo;

    public NoteService(NoteRepository repo) {
        this.repo = repo;
    }

    public Note createNote(CreateNoteRequest req){
        var note = new Note (
            UUID.randomUUID(),
            req.title(),
            req.content(),
            Instant.now()
        );

        return repo.createNote(note);
    }

    public Note getNoteByID(UUID id){
          return repo.getNoteByID(id).orElseThrow(() -> new NoteNotFoundException(id));
    }

    public List<Note> getAllNotes(){
        return repo.getAllNotes();
    }
}
