package client.scenes;

import client.utils.ServerUtils;
import com.google.inject.Inject;
import commons.Note;
import commons.Person;
import commons.Quote;
import jakarta.ws.rs.WebApplicationException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;

public class AddNoteCtrl {
  private final ServerUtils server;
  private final PrimaryNoteCtrl mainCtrl;

  @FXML
  private TextField title;

  @Inject
  public AddNoteCtrl(ServerUtils server, PrimaryNoteCtrl mainCtrl) {
    this.mainCtrl = mainCtrl;
    this.server = server;
  }
  public void cancel() {
    title.clear();
    mainCtrl.showOverview();
  }

  public void ok() {
    try {
      server.addNote(new Note(title.getText()));
    } catch (WebApplicationException e) {

      var alert = new Alert(Alert.AlertType.ERROR);
      alert.initModality(Modality.APPLICATION_MODAL);
      alert.setContentText(e.getMessage());
      alert.showAndWait();
      return;
    }

    title.clear();
    mainCtrl.showOverview();
  }
  public void keyPressed(KeyEvent e) {
    switch (e.getCode()) {
      case ENTER:
        ok();
        break;
      case ESCAPE:
        cancel();
        break;
      default:
        break;
    }
  }
}
