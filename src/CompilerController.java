import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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

        Scene scene = new Scene(hbox, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setWidth(1000);
        primaryStage.setHeight(800);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
