import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.awt.*;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Welcome to SmartGCC");
        final ToggleGroup group = new ToggleGroup();

        Label titleLabel = new Label("Please select your level of experience");
//        titleLabel.setTextFill(Color.FLORALWHITE);
        titleLabel.setFont(new Font("Avenir", 20));
        titleLabel.setTranslateX(60);
        titleLabel.setTranslateY(60);
        titleLabel.setPrefWidth(500);
        titleLabel.setTextAlignment(TextAlignment.CENTER);

        RadioButton rb1 = new RadioButton("Novice");
        rb1.setToggleGroup(group);
        rb1.setPrefSize(150, 50);
        rb1.setTranslateX(titleLabel.getTranslateX());
        rb1.setTranslateY(titleLabel.getTranslateY() + 20);

        RadioButton rb2 = new RadioButton("Typical");
        rb2.setToggleGroup(group);
        rb2.setPrefSize(150, 50);
        rb2.setTranslateX(titleLabel.getTranslateX());
        rb2.setTranslateY(rb1.getTranslateY() + 10);

        RadioButton rb3 = new RadioButton("Expert");
        rb3.setToggleGroup(group);
        rb3.setPrefSize(150, 50);
        rb3.setTranslateX(titleLabel.getTranslateX());
        rb3.setTranslateY(rb2.getTranslateY() + 10);


        Button btnok = new Button("Get Started");
        btnok.setTranslateX(100);
        btnok.setTranslateY(rb3.getTranslateY() + 20);
        btnok.setPrefSize(300, 50);
        btnok.setFont(new Font("Avenir-Bold", 20));
        VBox vbox = new VBox();
        vbox.setPrefWidth(500);
        Scene scene = new Scene(vbox, 500, 500);

        vbox.getChildren().addAll(titleLabel, rb1, rb2, rb3, btnok);
        vbox.setSpacing(10);

        vbox.setPrefWidth(500);

        vbox.setStyle("-fx-background-color:POWDERBLUE");
        primaryStage.setScene(scene);
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        primaryStage.show();
    }
}
