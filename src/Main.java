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
        primaryStage.setTitle(Constants.TITLE_SCREEN);
        final ToggleGroup group = new ToggleGroup();

        Label titleLabel = new Label(Constants.SELECT_USER_TYPE);
//        titleLabel.setTextFill(Color.FLORALWHITE);
        titleLabel.setFont(new Font("Avenir", 20));
        titleLabel.setTranslateX(100);
        titleLabel.setTranslateY(60);
        titleLabel.setPrefWidth(500);
        titleLabel.setTextAlignment(TextAlignment.CENTER);

        RadioButton noviceOption = new RadioButton(Constants.NOVICE);
        noviceOption.setToggleGroup(group);
        noviceOption.setPrefSize(150, 50);
        noviceOption.setTranslateX(60);
        noviceOption.setTranslateY(titleLabel.getTranslateY() + 20);

        RadioButton typicalOption = new RadioButton(Constants.TYPICAL);
        typicalOption.setToggleGroup(group);
        typicalOption.setPrefSize(150, 50);
        typicalOption.setTranslateX(noviceOption.getTranslateX());
        typicalOption.setTranslateY(noviceOption.getTranslateY() + 10);

        RadioButton expertOption = new RadioButton(Constants.EXPERT);
        expertOption.setToggleGroup(group);
        expertOption.setPrefSize(150, 50);
        expertOption.setTranslateX(noviceOption.getTranslateX());
        expertOption.setTranslateY(typicalOption.getTranslateY() + 10);


        Button btnok = Constants.createButton(100, (int)(expertOption.getTranslateY() + 20), 300, 50, Constants.GET_STATED_BUTTON);
        btnok.setFont(new Font("Avenir-Bold", 20));
        VBox vbox = new VBox();
        vbox.setPrefWidth(500);
        Scene scene = new Scene(vbox, 500, 500);

        vbox.getChildren().addAll(titleLabel, noviceOption, typicalOption, expertOption, btnok);
        vbox.setSpacing(10);

        vbox.setPrefWidth(500);

        vbox.setStyle("-fx-background-color:POWDERBLUE");
        primaryStage.setScene(scene);
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        primaryStage.show();
    }
}
