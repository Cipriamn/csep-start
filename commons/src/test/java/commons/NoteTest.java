package commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.PrivateKey;

import static org.junit.jupiter.api.Assertions.*;

class NoteTest {
  private Note testNote;
  @BeforeEach
  public void setup(){
    testNote = new Note("Prime Numbers");
    testNote.title ="2 3 5 7 11 13 17...";
  }
  @Test
  void testEquals() {
    assertEquals(testNote,testNote);
  }
  @Test
  void testNotEquals() {
    Note testFalseNote = new Note("Prime Numbers");
    assertFalse(testNote.equals(testFalseNote));
  }
  @Test
  void testHashCode() {
    Note testFalseNote = new Note("123");
    testFalseNote.title ="2 3 5 7 11 13 17...";
    assertEquals(testFalseNote.hashCode(),testNote.hashCode());
  }

  @Test
  void testToString() {
    assertNotNull(testNote.toString());
  }
}