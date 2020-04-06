
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;

public class CodeEditorExample extends Application {

	public void CodeEditorExample(String[] args) {};

	@Override 
	public void start(Stage stage) throws Exception {
		// create the editing controls.

		Label title = new Label("CodeEditor");

		title.setStyle("-fx-font-size: 20;");
		final Label labeledCode = new Label();
		final TextArea editor = new TextArea();
		final Button revertEdits = new Button("Revert edits");

		//Toolbar Buttons
		ToolBar toolBar = new ToolBar();

		Button compile = new Button("Compile");
		Image hammer = new Image(getClass().getResourceAsStream("images/hammer.png"),20,20,true,true);
		compile.setGraphic(new ImageView(hammer));
		toolBar.getItems().add(compile);

		Button execute = new Button("Execute");
		Image play = new Image(getClass().getResourceAsStream("images/play-button.png"),20,20,true,true);
		execute.setGraphic(new ImageView(play));
		toolBar.getItems().add(execute);
		
		Button debug = new Button("Debug");
		Image debugImg = new Image(getClass().getResourceAsStream("images/debug.png"),20,20,true,true);
		debug.setGraphic(new ImageView(debugImg));
		toolBar.getItems().add(debug);
		
		Button codeGenerate = new Button("Code Generate");
		Image coding = new Image(getClass().getResourceAsStream("images/coding.png"),20,20,true,true);
		codeGenerate.setGraphic(new ImageView(coding));
		toolBar.getItems().add(codeGenerate);
		
		Button optimize = new Button("Optimize");
		Image setting = new Image(getClass().getResourceAsStream("images/settings.png"),20,20,true,true);
		optimize.setGraphic(new ImageView(setting));
		toolBar.getItems().add(optimize);

		Button developerOption = new Button("Developer Option");
		Image cloudImg = new Image(getClass().getResourceAsStream("images/cloud-service.png"),20,20,true,true);
		developerOption.setGraphic(new ImageView(cloudImg));
		toolBar.getItems().add(developerOption);
		
		Button switchUser = new Button("Switch User");
		Image sort = new Image(getClass().getResourceAsStream("images/sort.png"),20,20,true,true);
		switchUser.setGraphic(new ImageView(sort));
		toolBar.getItems().add(switchUser);
		
		Button linking = new Button("Linking");
		Image linkImg = new Image(getClass().getResourceAsStream("images/link.png"),20,20,true,true);
		linking.setGraphic(new ImageView(linkImg));
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
				editor.setText("");
			}
		});

		VBox layout = new VBox(); 
		layout.setSpacing(10); 
		ObservableList list = layout.getChildren(); 
		list.addAll(title, toolBar, editor,revertEdits,labeledCode);  
		layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");

		compile.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				PrintWriter fw = null;
				try {
					FileWriter fileWriter = new FileWriter("test.cpp");
					fileWriter.write(editor.getText());
					fileWriter.close();

				} catch (IOException e) {
					e.printStackTrace();
				}

				GccHelper.runCommand("g++ test.cpp -o test");
			}
		});

		execute.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				GccHelper.runCommand("./" + "test");
			}
		});

		// display the scene.
		final Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.setHeight(600);
		stage.setWidth(970);
		stage.show();
	}
}