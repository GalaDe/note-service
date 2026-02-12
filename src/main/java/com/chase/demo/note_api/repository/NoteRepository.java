package com.chase.demo.note_api.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.chase.demo.note_api.model.Note;
import java.util.UUID;

@Repository
public class NoteRepository {
    private final Map<UUID, Note> store = new ConcurrentHashMap<>();

    public Note createNote(Note note) {
        store.put(note.id(), note);
        return note;
    }

    // Optional<T> is a container object that may or may not contain a value.
    public Optional<Note> getNoteByID(UUID id) {
        Note note = store.get(id);
        return Optional.ofNullable(note);
    }

    public List<Note> getAllNotes() {
        return new ArrayList<>(store.values());
    }
}
