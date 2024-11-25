package server.api;

import commons.Note;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.database.NoteRepository;

import java.util.List;



@RestController
@RequestMapping("/api/notes")
public class NoteController {
  private NoteRepository noteRepository;

  public NoteController(NoteRepository repository) {
    noteRepository = repository;

  }
  @GetMapping(path = { "", "/" })
  public List<Note> getAll() {
    return noteRepository.findAll();
  }
  @PostMapping(path = { "", "/" })
  public ResponseEntity<Note> add(@RequestBody Note note) {

    if (note.title == null||note.title == "") {
      return ResponseEntity.badRequest().build();
    }

    Note saved = noteRepository.save(note);
    return ResponseEntity.ok(saved);
  }
  private static boolean isNullOrEmpty(String s) {
    return s == null || s.isEmpty();
  }

}
