package no.vebb.boardgames.connectfour;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import no.vebb.boardgames.connectfour.controller.MainController;
import no.vebb.boardgames.connectfour.model.Model;
import no.vebb.boardgames.connectfour.view.MainView;

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

        ChangeListener<Number> sizeChangeListener = ((a, b, c) -> view.rescale());

        stage.widthProperty().addListener(sizeChangeListener);
        stage.heightProperty().addListener(sizeChangeListener);

        stage.maximizedProperty().addListener((a, b, c) -> {
            Platform.runLater(() -> view.rescale());
        });
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