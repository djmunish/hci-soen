
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.layout.HBox;
import org.controlsfx.control.CheckComboBox;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
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
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;



public class CodeEditorController extends Application {



	public void CodeEditorExample(String[] args) {};
	Constants.userType user;
	@Override
	public void start(Stage stage) throws Exception {
		// create the editing controls.


		MenuBar menuBar = new MenuBar();

		// --- Menu File
		Menu menuFile = new Menu("File");
		MenuItem open = new MenuItem("Open");
		menuFile.getItems().addAll(open);

		// --- Code Edit
		Menu edit = new Menu("Edit");
		MenuItem undo = new MenuItem("Undo Typing");
		MenuItem redo = new MenuItem("Redo Typing");
		edit.getItems().addAll(undo,redo);
		

		// --- Menu Save
		Menu save = new Menu("Save");
		MenuItem saveFile = new MenuItem("Save File");
		save.getItems().addAll(saveFile);

		// --- Menu View

		menuBar.getMenus().addAll(menuFile,edit,save);

		Label title = new Label("CodeEditor");
		title.setStyle("-fx-font-size: 20;");
		title.setTextFill(Color.web("#20202B"));
		Label outputTitle = new Label("Output");
		outputTitle.setStyle("-fx-font-size: 20;");
		outputTitle.setTextFill(Color.web("#20202B"));

		final TextArea editor = new TextArea();
		editor.setPrefHeight(400); 
		editor.setPrefWidth(300); 
		editor.setStyle("-fx-control-inner-background: #F2FAFA");
		final TextArea output = new TextArea();
		output.setPrefHeight(200); 
		output.setPrefWidth(300);
		output.setStyle("-fx-control-inner-background: #F2FAFA");
		final Button revertEdits = new Button("Erase All");
		final Button outputErase = new Button("");
		Image trash = new Image(getClass().getResourceAsStream("images/trash.png"),20,20,true,true);
		outputErase.setGraphic(new ImageView(trash));


		String content = new String ( Files.readAllBytes( Paths.get("test.cpp") ) );
		if(!content.isEmpty()) {
			editor.setText(content);
		}

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
		//		toolBar.getItems().add(optimize);

		String opt1[] = {"Ofast","O1", "O2", "O3"};
		ChoiceBox<String> optimizeOption = new ChoiceBox<>(FXCollections.observableArrayList(opt1));
		Platform.runLater(() -> {
			SkinBase<ChoiceBox<String>> skin = (SkinBase<ChoiceBox<String>>) optimizeOption.getSkin();
			// children contain only "Label label" and "StackPane openButton"
			for (Node child : skin.getChildren()) {
				if (child instanceof Label) {
					Label label = (Label) child;
					if (label.getText().isEmpty()) {
						label.setText("Optimize Options");
					}
					return;
				}
			}
		});
		toolBar.getItems().add(optimizeOption);

		final ObservableList<String> optionsOptimize = FXCollections.observableArrayList();


		String opt[] = {"Assembly File", "Executable File", "Binary File"};
		ChoiceBox<String> developerOption = new ChoiceBox<>(FXCollections.observableArrayList(opt));
		Platform.runLater(() -> {
			SkinBase<ChoiceBox<String>> skin = (SkinBase<ChoiceBox<String>>) developerOption.getSkin();
			// children contain only "Label label" and "StackPane openButton"
			for (Node child : skin.getChildren()) {
				if (child instanceof Label) {
					Label label = (Label) child;
					if (label.getText().isEmpty()) {
						label.setText("Developer Options");
					}
					return;
				}
			}
		});
		toolBar.getItems().add(developerOption);

		final ObservableList<String> options = FXCollections.observableArrayList();

		if(user == Constants.userType.NOVICE) {
			codeGenerate.setVisible(false);
			optimize.setVisible(false);
			developerOption.setVisible(false);
			optimizeOption.setVisible(false);
			//toolBar.getItems().removeAll(codeGenerate,developerOption,optimize);

			options.addAll(codeGenerate.getText(),optimize.getText(),"Developer Option");
		}
		else if(user == Constants.userType.TYPICAL) {
			developerOption.setVisible(false);
			//toolBar.getItems().remove(developerOption);
			options.addAll("Developer Option");
		}

		// Create the CheckComboBox with the data 

		final CheckComboBox<String> allOptions = new CheckComboBox<String>(options);

		// and listen to the relevant events (e.g. when the selected indices or 
		// selected items change).
		allOptions.setMaxWidth(1);
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
						optimizeOption.setVisible(false);
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
						optimizeOption.setVisible(true);
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
			allOptionLabel.setTextFill(Color.web("#FBFBFF"));
			toolBar.getItems().add(allOptionLabel);
		}

		revertEdits.setOnAction(new EventHandler<ActionEvent>() {

			@Override 
			public void handle(ActionEvent actionEvent) {
				editor.setText("");
			}
		});
		outputErase.setOnAction(new EventHandler<ActionEvent>() {

			@Override 
			public void handle(ActionEvent actionEvent) {
				output.setText("");
			}
		});
		menuBar.setStyle("-fx-background-color: #74748E;-fx-text-base-color:#000000");
		toolBar.setStyle("-fx-background-color: #74748E;");

		VBox layout = new VBox();
		HBox outHbox = new HBox();
		layout.setSpacing(10);
		ObservableList list = layout.getChildren();
		layout.getChildren().addAll(menuBar);

		outHbox.getChildren().addAll(outputTitle, outputErase);
		outputErase.setTranslateX(1160);
		list.addAll(title, toolBar, editor,revertEdits,outHbox,output);
		layout.setStyle("-fx-background-color: #9393A7; -fx-padding: 10;");

		compile.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (editor.getText().length() == 0){
					return;
				}

				try {
					FileWriter fileWriter = new FileWriter("test.cpp");
					fileWriter.write(editor.getText());
					fileWriter.close();

				} catch (IOException e) {
					e.printStackTrace();
				}




				String result = "";
				try {
					Process process = Runtime.getRuntime().exec("g++ test.cpp -o test");
					String line;
					BufferedReader errorReader = new BufferedReader(
							new InputStreamReader(process.getErrorStream()));
					while ((line = errorReader.readLine()) != null) {
						System.out.println(line);
						result += line +"\n";
					}
					if(!GccHelper.isNullOrEmpty(result)){
						output.setText(result);
					}
					else{
						output.setText("command executed, any errors? No");
					}
					errorReader.close();

				} catch (IOException e) {
					e.printStackTrace();
					System.out.println(e);
				}
			}
		});

		execute.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//				 GccHelper.runCompile("./" + "test");
				//				output.setText(result);


				try {
					Process process = Runtime.getRuntime().exec("./" + "test");

					BufferedWriter writer = new BufferedWriter(
							new OutputStreamWriter(process.getOutputStream()));


					TextInputDialog dialog = new TextInputDialog("");

					dialog.setHeaderText("Enter the input if any (leave blank if no input)");
					dialog.setContentText("Input:");

					dialog.setWidth(650);
					Optional<String> result = dialog.showAndWait();

					result.ifPresent(input -> {
						System.out.println(input);
						try {
							writer.write(input);
						} catch (IOException e) {
							e.printStackTrace();
						}
					});

					writer.close();
					String resultO = "";

					BufferedReader reader = new BufferedReader(new InputStreamReader(
							process.getInputStream()));
					String line;
					while ((line = reader.readLine()) != null) {
						System.out.println(line);
						resultO += line +"\n";

					}
					if(!GccHelper.isNullOrEmpty(resultO)){
						output.setText(resultO);
					}
					else{
						output.setText("command executed, any errors? No");
					}
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

		switchUser.setOnAction(new EventHandler<ActionEvent>() {
			Main main= new Main();
			@Override
			public void handle(ActionEvent event) {

				try {
					FileWriter fileWriter = new FileWriter("test.cpp");
					fileWriter.write(editor.getText());
					fileWriter.close();
					main.start(stage);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		optimize.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Runtime rt = Runtime.getRuntime();
				try {
					//-O1 -O2 -O3 -Ofast
					Process pr = rt.exec("g++ -Ofast test.cpp -o optimizeCode");
					String optResult = GccHelper.runCommand("./optimizeCode");
					output.setText("Optimized Result:"+optResult);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		
		undo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				editor.undo();}
		});
		
		redo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				editor.redo();
			}
		});

		saveFile.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FileWriter fileWriter;
				try {
					fileWriter = new FileWriter("test.cpp");
					fileWriter.write(editor.getText());
					fileWriter.close();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("File Saved Successfully!");
					alert.showAndWait();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});



		open.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CPP files (*.cpp)", "*.cpp");
				fileChooser.getExtensionFilters().add(extFilter);
				File file = fileChooser.showOpenDialog(stage);
				String path=file.toString();
				try {
					String content = new String ( Files.readAllBytes( Paths.get(path) ) );
					editor.setText(content);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		codeGenerate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				try {
					Runtime rt = Runtime.getRuntime();
					Process pr = rt.exec("g++ -S test.cpp -fverbose-asm -Os -o codegenerated");
					output.setText("Code Generated");
					File file = new File("codegenerated");
					if(!file.createNewFile())
						java.awt.Desktop.getDesktop().open(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});


		debug.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub	
				//					Runtime rt = Runtime.getRuntime();
				//					Process pr = rt.exec("g++ -D DEBUG test.cpp -o debug");
//
				String debugResult1 = GccHelper.runCommand("g++ -D DEBUG test.cpp -o debug");
				System.out.println(debugResult1);


//
//				String debugResult = GccHelper.runCommand("./debug");
//				output.setText("Debug Result:"+debugResult1+"\n"+debugResult);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                        Process process = Runtime.getRuntime().exec("./debug");

                        output.setText("");


                        BufferedWriter writer = new BufferedWriter(
                                new OutputStreamWriter(process.getOutputStream()));


                        TextInputDialog dialog = new TextInputDialog("");

                        dialog.setHeaderText("Enter the input if any (leave blank if no input)");
                        dialog.setContentText("Input:");

                        dialog.setWidth(650);
                        Optional<String> result = dialog.showAndWait();

                        result.ifPresent(input -> {
                            System.out.println(input);
                            try {
                                writer.write(input);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

                        writer.close();
                        String resultO = "";

                        BufferedReader reader = new BufferedReader(new InputStreamReader(
                                process.getInputStream()));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                            resultO += line +"\n";

                        }
                        System.out.println(resultO);
                        if(!GccHelper.isNullOrEmpty(resultO)){
                            output.setText("Debug Result:" + resultO);
                        }
                        else{
                            output.setText("command executed, any errors? No");
                        }
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }





			}
		});

		// add a listener 
		developerOption.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue ov, Number value, Number new_value) {
				// TODO Auto-generated method stub
				Runtime rt = Runtime.getRuntime();
				if(opt[new_value.intValue()].equals("Assembly File")){

					try {
						Process pr = rt.exec("g++ -S test.cpp");
						output.setText("Assembly File Generated");
						File file = new File("test.s");
						TimeUnit.SECONDS.sleep(2);
						if(!file.createNewFile())
							java.awt.Desktop.getDesktop().open(file);
					} catch (IOException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				else if(opt[new_value.intValue()].equals("Executable File")){
					try {
						Process pr = rt.exec("g++ test.cpp -o test");
						output.setText("Executable File Generated");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
				}
				else if(opt[new_value.intValue()].equals("Binary File")){
					try {
						Process pr = rt.exec("g++ test.cpp -o test.o");
						output.setText("Binary File Generated ");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		});

		optimizeOption.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue ov, Number value, Number new_value) {
				// TODO Auto-generated method stub
				Runtime rt = Runtime.getRuntime();
				if(opt1[new_value.intValue()].equals("Ofast")){
					try {
						Process pr = rt.exec("g++ -Ofast test.cpp -o optimizeCode");
						String optResult = GccHelper.runCommand("./optimizeCode");
						output.setText("Optimized Result:"+optResult);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(opt1[new_value.intValue()].equals("O1")){

					try {
						Process pr = rt.exec("g++ -O1 test.cpp -o optimizeCode");
						String optResult = GccHelper.runCommand("./optimizeCode");
						output.setText("Optimized Result:"+optResult);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				else if(opt1[new_value.intValue()].equals("O2")){
					try {
						Process pr = rt.exec("g++ -O2 test.cpp -o optimizeCode");
						String optResult = GccHelper.runCommand("./optimizeCode");
						output.setText("Optimized Result:"+optResult);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(opt1[new_value.intValue()].equals("O3")){
					try {
						Process pr = rt.exec("g++ -O3 test.cpp -o optimizeCode");
						String optResult = GccHelper.runCommand("./optimizeCode");
						output.setText("Optimized Result:"+optResult);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		});

		// display the scene.
		final Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();
	}
}