package client.scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;

public class PrimaryNoteCtrl {
  private Stage primaryStage;

  private NoteViewCtrl overviewCtrl;
  private Scene overview;

  private AddNoteCtrl addCtrl;
  private Scene add;

  public void initialize(Stage primaryStage, Pair<NoteViewCtrl, Parent> overview,
                         Pair<AddNoteCtrl, Parent> add) {
    this.primaryStage = primaryStage;
    this.overviewCtrl = overview.getKey();
    this.overview = new Scene(overview.getValue());

    this.addCtrl = add.getKey();
    this.add = new Scene(add.getValue());

    showOverview();
    primaryStage.show();
  }

  public void showOverview() {
    primaryStage.setTitle("Quotes: Overview");
    primaryStage.setScene(overview);
    overviewCtrl.refresh();
  }

  public void showAdd() {
    primaryStage.setTitle("Quotes: Adding Quote");
    primaryStage.setScene(add);
    add.setOnKeyPressed(e -> addCtrl.keyPressed(e));
  }
}
