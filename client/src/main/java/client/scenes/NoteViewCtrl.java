package client.scenes;

import client.utils.ServerUtils;
import commons.Note;
import commons.Quote;
import com.google.inject.Inject;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.web.WebView;


import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class NoteViewCtrl implements Initializable {
  public ObservableList<Note> noteData;

  @FXML
  private TextField titleField;
  @FXML
  private TextArea textArea;
  @FXML
  private WebView webView;
  @FXML
  private TableView<Note> tableView;
  @FXML
  private TableColumn<Note, String> colNote;
  @FXML
  private Button editTitleButton;
  private final ServerUtils server;



  private final PrimaryNoteCtrl mainCtrl;

  @Inject
  public NoteViewCtrl(ServerUtils server, TableView<Note> tableView, WebView webView, TextArea textArea,PrimaryNoteCtrl mainCtrl) {
    this.server = server;
    this.tableView = tableView;
    this.webView = webView;
    this.textArea = textArea;
    this.mainCtrl = mainCtrl;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
   colNote.setCellValueFactory(q -> new SimpleStringProperty(q.getValue().title));
    tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      if (oldValue != null) {
        // Save the changes made in the TextArea to the oldValue
        oldValue.markdown = textArea.getText();
        oldValue.title = titleField.getText();
      }
      if (newValue != null) {
        // Update the TextArea to show the markdown content of the newly selected item
        textArea.setText(newValue.markdown);
        titleField.setText(newValue.title);
      }
    });
  }
  public void refresh() {
    var notes = server.getNotes();
    noteData = FXCollections.observableList(notes);
    tableView.setItems(noteData);
  }
  public void addNote() {
    mainCtrl.showAdd();
  }


}
