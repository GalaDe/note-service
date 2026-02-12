package com.chase.demo.note_api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.chase.demo.note_api.dto.CreateNoteRequest;
import com.chase.demo.note_api.exception.NoteNotFoundException;
import com.chase.demo.note_api.model.Note;
import com.chase.demo.note_api.repository.NoteRepository;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    @Mock
    private NoteRepository repo;

    @Test
    void createNote_createsAndReturnsPersistedNote() {
        var service = new NoteService(repo);
        var request = new CreateNoteRequest("Title", "Content");

        when(repo.createNote(any(Note.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var before = Instant.now();
        var result = service.createNote(request);
        var after = Instant.now();

        ArgumentCaptor<Note> noteCaptor = ArgumentCaptor.forClass(Note.class);
        verify(repo).createNote(noteCaptor.capture());

        var persisted = noteCaptor.getValue();

        assertThat(persisted.id()).isNotNull();
        assertThat(persisted.title()).isEqualTo("Title");
        assertThat(persisted.content()).isEqualTo("Content");
        assertThat(persisted.createdAt()).isBetween(before.minusSeconds(1), after.plusSeconds(1));

        assertThat(result).isEqualTo(persisted);
    }

    @Test
    void getNoteById_returnsNoteWhenPresent() {
        var service = new NoteService(repo);
        var id = UUID.randomUUID();
        var note = new Note(id, "Title", "Content", Instant.now());

        when(repo.getNoteByID(id)).thenReturn(Optional.of(note));

        var result = service.getNoteByID(id);

        assertThat(result).isEqualTo(note);
    }

    @Test
    void getNoteById_throwsWhenMissing() {
        var service = new NoteService(repo);
        var id = UUID.randomUUID();

        when(repo.getNoteByID(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getNoteByID(id))
                .isInstanceOf(NoteNotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void getAllNotes_returnsRepositoryResults() {
        var service = new NoteService(repo);
        var notes = List.of(
                new Note(UUID.randomUUID(), "A", "One", Instant.now()),
                new Note(UUID.randomUUID(), "B", "Two", Instant.now()));

        when(repo.getAllNotes()).thenReturn(notes);

        var result = service.getAllNotes();

        assertThat(result).isEqualTo(notes);
    }
}
