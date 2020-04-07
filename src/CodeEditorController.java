
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.controlsfx.control.CheckComboBox;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CodeEditorController extends Application {

	public void CodeEditorExample(String[] args) {};
	Constants.userType user;
	GccHelper gccHelper= new GccHelper();
	@Override 
	public void start(Stage stage) throws Exception {
		// create the editing controls.

		Label title = new Label("CodeEditor");
		title.setStyle("-fx-font-size: 20;");
		Label outputTitle = new Label("Output");
		outputTitle.setStyle("-fx-font-size: 20;");

		final TextArea editor = new TextArea();
		editor.setPrefHeight(400); 
		editor.setPrefWidth(300); 
		final TextArea output = new TextArea();
		output.setPrefHeight(200); 
		output.setPrefWidth(300);
		final Button revertEdits = new Button("Erase All");

		//Toolbar Buttons
		ToolBar toolBar = new ToolBar();
		toolBar.setLayoutX(900);
		toolBar.setLayoutY(700);

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

		Button switchUser = new Button("Switch User");
		Image sort = new Image(getClass().getResourceAsStream("images/sort.png"),20,20,true,true);
		switchUser.setGraphic(new ImageView(sort));
		toolBar.getItems().add(switchUser);

		Button linking = new Button("Linking");
		Image linkImg = new Image(getClass().getResourceAsStream("images/link.png"),20,20,true,true);
		linking.setGraphic(new ImageView(linkImg));
		toolBar.getItems().add(linking);

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

		final ObservableList<String> options = FXCollections.observableArrayList();

		if(user == Constants.userType.NOVICE) {
			codeGenerate.setVisible(false);
			optimize.setVisible(false);
			developerOption.setVisible(false);
			//toolBar.getItems().removeAll(codeGenerate,developerOption,optimize);

			options.addAll(codeGenerate.getText(),optimize.getText(),developerOption.getText());
		}
		else if(user == Constants.userType.TYPICAL) {
			developerOption.setVisible(false);
			//toolBar.getItems().remove(developerOption);
			options.addAll(developerOption.getText());
		}

		// Create the CheckComboBox with the data 
		final CheckComboBox<String> allOptions = new CheckComboBox<String>(options);

		// and listen to the relevant events (e.g. when the selected indices or 
		// selected items change).
		allOptions.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
			public void onChanged(ListChangeListener.Change<? extends String> c) {
				//System.out.println(allOptions.getCheckModel().getCheckedItems());
				String string=allOptions.getCheckModel().getCheckedItems().toString();
				string = string.replaceAll("\\[","").replaceAll("\\]","");
				System.out.println(string);
				List<String> list = Arrays.asList(string.split("\\s*,\\s*"));
				if(user==Constants.userType.NOVICE) {
					if(!(list.contains("Code Generate"))) {
						codeGenerate.setVisible(false);	
					}

					if(!(list.contains("Optimize"))) {
						optimize.setVisible(false);	
					}

					if(!(list.contains("Developer Option"))) {
						developerOption.setVisible(false);	
					}
				}else {
					if(!(list.contains("Developer Option"))) {
						developerOption.setVisible(false);	
					}
				}

				for (String buttonSelected : list) {

					if(buttonSelected.contentEquals("Code Generate")) {
						codeGenerate.setVisible(true);	
					}

					if(buttonSelected.contentEquals("Optimize")) {
						optimize.setVisible(true);
					}

					if(buttonSelected.contentEquals("Developer Option")) {
						developerOption.setVisible(true);
					}
				}

			}
		});
		if(!(user == Constants.userType.EXPERT)) {

			toolBar.getItems().add(allOptions);
			final Label allOptionLabel = new Label();
			allOptionLabel.setText("All Options");
			toolBar.getItems().add(allOptionLabel);
		}

		revertEdits.setOnAction(new EventHandler<ActionEvent>() {

			@Override 
			public void handle(ActionEvent actionEvent) {
				editor.setText("");
			}
		});

		VBox layout = new VBox(); 
		layout.setSpacing(10);
		ObservableList list = layout.getChildren(); 
		list.addAll(title, toolBar, editor,revertEdits,outputTitle,output);  
		layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");

		compile.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (editor.getText().length() == 0){
					return;
				}

				PrintWriter fw = null;
				try {
					FileWriter fileWriter = new FileWriter("test.cpp");
					fileWriter.write(editor.getText());
					fileWriter.close();

				} catch (IOException e) {
					e.printStackTrace();
				}

				String result=gccHelper.runCommand("g++ test.cpp -o test");
				output.setText(result);
			}
		});

		execute.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String result=gccHelper.runCommand("./" + "test");
				output.setText(result);
			}
		});

		switchUser.setOnAction(new EventHandler<ActionEvent>() {
			Main main= new Main();
			@Override
			public void handle(ActionEvent event) {
				try {
					main.start(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// display the scene.
		final Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.setHeight(700);
		stage.setWidth(1200);
		stage.show();
	}
}