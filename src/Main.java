import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Welcome to SmartGCC");
        final ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton("Novice");
        rb1.setToggleGroup(group);
        rb1.setPrefSize(150, 50);
        rb1.setTranslateX(60);
        rb1.setTranslateY(190);

        RadioButton rb2 = new RadioButton("Typical");
        rb2.setToggleGroup(group);
        rb2.setPrefSize(150, 50);
        rb2.setTranslateX(60);
        rb2.setTranslateY(200);

        RadioButton rb3 = new RadioButton("Expert");
        rb3.setToggleGroup(group);
        rb3.setPrefSize(150, 50);
        rb3.setTranslateX(60);
        rb3.setTranslateY(210);


        HBox hbox = new HBox();
        VBox vbox = new VBox();

        Scene scene = new Scene(hbox, 1000, 800);
        vbox.getChildren().add(rb1);
        vbox.getChildren().add(rb2);
        vbox.getChildren().add(rb3);

        vbox.setSpacing(10);

        Button btnok = new Button("Get Stared");
        btnok.setStyle("-fx-background-color: Green ");
        btnok.setTranslateX(60);
        btnok.setTranslateY(220);
        btnok.setPrefSize(300, 50);

        // Navigation to Arena.


        vbox.getChildren().add(btnok);


        Label l2 = new Label("PLEASE SELECT THE USER TYPE " + "!");
//        l2.setTextFill(Color.FLORALWHITE);
//        l2.setFont(new Font("Arial", 20));


        l2.setTranslateX(0);
        l2.setTranslateY(50);
        l2.setPrefWidth(500);
        l2.setWrapText(true);

        vbox.setPrefWidth(500);

        hbox.getChildren().add(vbox);
//        hbox.getChildren().add(btnok);
        hbox.getChildren().add(l2);
        hbox.setSpacing(50);


        hbox.setStyle("-fx-background-color: Grey");
        primaryStage.setScene(scene);
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);

        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
