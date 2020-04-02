import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CompilerController extends Application {
    Constants.userType user;
    public static void main(String[] args) {
        launch(args);

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println(user);
        primaryStage.setTitle(Constants.TITLE_COMPILER);
        HBox hbox = new HBox();


        Button startedBtn = Constants.createButton(100, (int)( 20), 300, 50, "Compile Test");
        startedBtn.setFont(new Font("Avenir-Bold", 20));
        VBox vbox = new VBox();
        vbox.setPrefWidth(500);


        startedBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String filename = "test.cpp";
                String outputName = "test";
//                runCommand("g++ "+ filename + "-o " + outputName);
                GccHelper.runCommand("./" + outputName);
            }
        });


        hbox.getChildren().addAll(startedBtn);

        Scene scene = new Scene(hbox, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setWidth(1000);
        primaryStage.setHeight(800);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

}
