import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class for the all the constants in the project.
 * @author iknoor
 * @since 2019-07-06
 * @version 1.0.1
 */
public final class Constants {
    //Titles

    enum userType{
         NOVICE,
         EXPERT,
         TYPICAL
    }

    public static final String NOVICE = "Novice";
    public static final String EXPERT = "Expert";
    public static final String TYPICAL = "Typical";

    //Alerts
    public static final String SELECT_USER_TYPE = "Please select your level of experience";
    public static final String TITLE_SCREEN = "Welcome to SmartGCC";
    public static final String GET_STATED_BUTTON = "GCC Compiler";
    public static final String TITLE_COMPILER = "GCC Compiler";






    public static void showAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ALERT");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }


    public static Button createButton(int x,int y, int w, int h, String title){
        Button btn = new Button();
        btn.setText(title);
        btn.setTranslateX(x);
        btn.setTranslateY(y);
        btn.setPrefSize(w, h);
        return btn;
    }


}