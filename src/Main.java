import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import javax.swing.*;

public class Main extends Application {
    private Constants.userType selectedUserType;
        
    Stage primary;
    public static void main(String[] args) {


        try {
            URL iconURL = Main.class.getResource("images/c.png");
            java.awt.Image image = new ImageIcon(iconURL).getImage();
            com.apple.eawt.Application.getApplication().setDockIconImage(image);
        } catch (Exception e) {
            // Won't work on Windows or Linux.
        }
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        primary = primaryStage;
    	primaryStage.setTitle(Constants.TITLE_SCREEN);
        final ToggleGroup group = new ToggleGroup();
        primaryStage.getIcons().add(new Image("/Images/c.png"));


        Image icon = new Image(getClass().getResourceAsStream("images/c.png"),60,60,true,true);
        ImageView iconView = new ImageView(icon);
        iconView.setTranslateX(220);
        iconView.setTranslateY(60);

        
        Label titleLabel = new Label(Constants.SELECT_USER_TYPE);
        titleLabel.setStyle("-fx-text-fill: white;");

        titleLabel.setFont(new Font("Avenir", 20));
        titleLabel.setTranslateX(180);
        titleLabel.setTranslateY(60);
        titleLabel.setPrefWidth(500);
        titleLabel.setTextAlignment(TextAlignment.CENTER);

        //trash icon laga diya

        RadioButton noviceOption = new RadioButton(Constants.NOVICE);
        noviceOption.setToggleGroup(group);
        noviceOption.setPrefSize(150, 50);
        noviceOption.setTranslateX(60);
        noviceOption.setTranslateY(titleLabel.getTranslateY() + 20);
        noviceOption.setStyle("-fx-text-fill: white;");

        RadioButton typicalOption = new RadioButton(Constants.TYPICAL);
        typicalOption.setToggleGroup(group);
        typicalOption.setPrefSize(150, 50);
        typicalOption.setTranslateX(noviceOption.getTranslateX());
        typicalOption.setTranslateY(noviceOption.getTranslateY() + 10);
        typicalOption.setStyle("-fx-text-fill: white;");

        RadioButton expertOption = new RadioButton(Constants.EXPERT);
        expertOption.setToggleGroup(group);
        expertOption.setPrefSize(150, 50);
        expertOption.setTranslateX(noviceOption.getTranslateX());
        expertOption.setTranslateY(typicalOption.getTranslateY() + 10);
        expertOption.setStyle("-fx-text-fill: white;");

        final ToggleGroup tg = new ToggleGroup();
        // add radiobuttons to toggle group
        noviceOption.setToggleGroup(tg);
        typicalOption.setToggleGroup(tg);
        expertOption.setToggleGroup(tg);

        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            public void changed(ObservableValue<? extends Toggle> ob,
                                Toggle o, Toggle n){        

                RadioButton rb = (RadioButton)tg.getSelectedToggle();

                if (rb != null) {
                    String s = rb.getText();
                    selectedUserType = Constants.userType.valueOf(s.toUpperCase());
                    System.out.println(selectedUserType);                   
                }
            }
        });

        Button startedBtn = Constants.createButton(100, (int)(expertOption.getTranslateY() + 20), 300, 50, Constants.GET_STATED_BUTTON);
        startedBtn.setFont(new Font("Avenir-Bold", 20));
        VBox vbox = new VBox();
        vbox.setPrefWidth(500);

        startedBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                startCompiler(primaryStage);
            }
        });

        vbox.getChildren().addAll(iconView,titleLabel, noviceOption, typicalOption, expertOption, startedBtn);

        Scene scene = new Scene(vbox, 500, 500);
        vbox.setSpacing(10);

        vbox.setPrefWidth(500);

        vbox.setStyle("-fx-background-color:#74748E");
        primaryStage.setScene(scene);
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        primaryStage.show();
    }
    
    

    public void startCompiler(Stage primaryStage){
    	
    	primary.setOnCloseRequest(e -> {
        	
       	 Alert closeConfirmation = new Alert(
                    Alert.AlertType.CONFIRMATION,
                    "Do you want to save file?"
            );
            Button saveButton = (Button) closeConfirmation.getDialogPane().lookupButton(
                    ButtonType.OK
            );
            saveButton.setText("Save");
            saveButton.setOnAction(new EventHandler<ActionEvent>() {
    			@Override
    			public void handle(ActionEvent event) {
    				FileChooser fileChooser = new FileChooser();
    				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CPP files (*.cpp)", "test.cpp");
    				fileChooser.getExtensionFilters().add(extFilter);
    				File file = fileChooser.showOpenDialog(primary);
    				String path=file.toString();
    			}
    		});
            //closeConfirmation.setHeaderext("Confirm Exit");
            closeConfirmation.initModality(Modality.APPLICATION_MODAL);
            closeConfirmation.initOwner(primary);
            
            Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
            if (!ButtonType.OK.equals(closeResponse.get())) {
                e.consume();
            }

       });

    	
    	CodeEditorController codeEditorObj = new CodeEditorController();
        codeEditorObj.user = selectedUserType;
        if (selectedUserType == null){
            Constants.showAlert("Select user type");
            return;
        }    
    	try {
    		codeEditorObj.start(primary);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}