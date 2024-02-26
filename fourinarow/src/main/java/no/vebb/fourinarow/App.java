package no.vebb.fourinarow;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import no.vebb.fourinarow.controller.MainController;
import no.vebb.fourinarow.model.Model;
import no.vebb.fourinarow.view.MainView;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    private static final int NUMBER_OF_ROWS = 6;
    private static final int NUMBER_OF_COLUMNS = 7;

    @SuppressWarnings("exports")
    @Override
    public void start(Stage stage) throws IOException {
        Model model = new Model(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS);
        MainController controller = new MainController(model);
        MainView view = new MainView(model, controller, NUMBER_OF_ROWS, NUMBER_OF_COLUMNS);
        scene = new Scene(view);
        stage.setScene(scene);
        stage.show();
        view.draw();

        ChangeListener<Boolean> changeListener = new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
                view.rescale();
            }
        };

        ChangeListener<Number> changeListener2 = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                view.rescale();
            }
        };

        stage.maximizedProperty().addListener(changeListener);
        stage.iconifiedProperty().addListener(changeListener);

        stage.widthProperty().addListener(changeListener2);
        stage.heightProperty().addListener(changeListener2);
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}