package server.api;

import commons.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.database.NoteRepository;
import server.database.NoteRepositoryTest;
import static org.springframework.http.HttpStatus.BAD_REQUEST;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.OK;

class NoteControllerTest {

  private NoteRepositoryTest repo;
  private Note someNote;
  private NoteController sut;
  @BeforeEach
  public void setup() {
     someNote = new Note("wowNote");
    repo = new NoteRepositoryTest();
    sut = new NoteController(repo);
  }
  @Test
  void getByIdTest(){
    sut.add(someNote);
    assertEquals(OK,sut.getById(0).getStatusCode());
  }

  @Test
  void add() {
    sut.add(someNote);
    assertNotNull(repo.findAll());
  }
  @Test
  void getAll() {
    sut.add(someNote);
    assertEquals(List.of(someNote),sut.getAll());
  }
  @Test
  void addEmptyTitle() {
    someNote.title = "";
    var response = sut.add(someNote);
    assertEquals(BAD_REQUEST,response.getStatusCode());
  }

}