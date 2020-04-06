
import java.io.File;
import java.io.InputStream;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
		
		//Toolbar Buttons
		ToolBar toolBar = new ToolBar();
		
		File imageFile = new File("C:/Users/Avinash/workspace_hci/hci/src/images/hammer.png");  
		Image imageDecline = new Image(imageFile.toURI().toString(),20,20,true,true);
		Button compile = new Button("Compile",new ImageView(imageDecline));
		toolBar.getItems().add(compile);
		
		imageFile = new File("C:/Users/Avinash/workspace_hci/hci/src/images/play-button.png");  
		imageDecline = new Image(imageFile.toURI().toString(),20,20,true,true);
		Button execute = new Button("Execute",new ImageView(imageDecline));
		toolBar.getItems().add(execute);
		
		imageFile = new File("C:/Users/Avinash/workspace_hci/hci/src/images/debug.png");  
		imageDecline = new Image(imageFile.toURI().toString(),20,20,true,true);
		Button debug = new Button("Debug",new ImageView(imageDecline));
		toolBar.getItems().add(debug);
		
		imageFile = new File("C:/Users/Avinash/workspace_hci/hci/src/images/coding.png");  
		imageDecline = new Image(imageFile.toURI().toString(),20,20,true,true);
		Button codeGenerate = new Button("Code Generate",new ImageView(imageDecline));
		toolBar.getItems().add(codeGenerate);
		
		imageFile = new File("C:/Users/Avinash/workspace_hci/hci/src/images/settings.png");  
		imageDecline = new Image(imageFile.toURI().toString(),20,20,true,true);
		Button optimize = new Button("Optimize",new ImageView(imageDecline));
		toolBar.getItems().add(optimize);
		
		imageFile = new File("C:/Users/Avinash/workspace_hci/hci/src/images/cloud-service.png");  
		imageDecline = new Image(imageFile.toURI().toString(),20,20,true,true);
		Button developerOption = new Button("Developer Option",new ImageView(imageDecline));
		toolBar.getItems().add(developerOption);
		
		imageFile = new File("C:/Users/Avinash/workspace_hci/hci/src/images/sort.png");  
		imageDecline = new Image(imageFile.toURI().toString(),20,20,true,true);
		Button switchUser = new Button("Switch User",new ImageView(imageDecline));
		toolBar.getItems().add(switchUser);
		
		imageFile = new File("C:/Users/Avinash/workspace_hci/hci/src/images/link.png");  
		imageDecline = new Image(imageFile.toURI().toString(),20,20,true,true);
		Button linking = new Button("Linking",new ImageView(imageDecline));
		toolBar.getItems().add(linking);
		
		ComboBox allOptions = new ComboBox();
		allOptions.setPromptText("All Options");
		allOptions.getItems().add("Option 1");
		allOptions.getItems().add("Option 2");
		allOptions.getItems().add("Option 3");
		toolBar.getItems().add(allOptions);
		

		revertEdits.setOnAction(new EventHandler<ActionEvent>() {

			@Override 
			public void handle(ActionEvent actionEvent) {
				editor.revertEdits();
			}
		});

		 VBox layout = new VBox(); 
		 layout.setSpacing(10); 
		 ObservableList list = layout.getChildren(); 
		 list.addAll(title, toolBar, editor,revertEdits,labeledCode);  
		 layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
		 
	/*	// layout the scene.
		final VBox layout = VBoxBuilder.create().spacing(10).children(
				title,
				HBoxBuilder.create().children(toolBar).build(),
				editor,
				HBoxBuilder.create().spacing(10).children(revertEdits).build(),
				labeledCode
				).build();
		layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");*/


		// display the scene.
		final Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.setHeight(600);
		stage.setWidth(970);
		stage.show();
	}
}