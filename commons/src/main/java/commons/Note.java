package commons;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Note {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public long id;
  public String title;
  public String markdown;

  private Note() {
  }
  public Note( String title) {
    this.title = title;
    this.markdown = "";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Note note = (Note) o;
    return id == note.id && Objects.equals(title, note.title) && Objects.equals(markdown, note.markdown);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, markdown);
  }

  @Override
  public String toString() {
    return "Note{" +
      "id=" + id +
      ", title='" + title + '\'' +
      ", markdown='" + markdown + '\'' +
      '}';
  }
}
