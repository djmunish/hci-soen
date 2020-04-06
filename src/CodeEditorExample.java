import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;

/**
 * An example application which demonstrates use of a
 * CodeMirror based JavaScript CodeEditor wrapped in
 * a JavaFX WebView.
 */
public class CodeEditorExample extends Application {
  // some sample code to be edited.
  static final private String editingCode = "";


public void CodeEditorExample(String[] args) {};

  @Override 
  public void start(Stage stage) throws Exception {
    // create the editing controls.
    
	  Label title = new Label("CodeEditor");
      
	  title.setStyle("-fx-font-size: 20;");
    
    final Label labeledCode = new Label(editingCode);
    final CodeEditor editor = new CodeEditor(editingCode);
    final Button revertEdits = new Button("Revert edits");
    
    revertEdits.setOnAction(new EventHandler<ActionEvent>() {
    
    	@Override 
      public void handle(ActionEvent actionEvent) {
        editor.revertEdits();
      }
    });
 

    // layout the scene.
    final VBox layout = VBoxBuilder.create().spacing(10).children(
      title,
      editor,
      HBoxBuilder.create().spacing(10).children(revertEdits).build(),
      labeledCode
    ).build();
    layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");

    // display the scene.
    final Scene scene = new Scene(layout);
    stage.setScene(scene);
    stage.show();
  }
}