package no.vebb.fourinarow.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainView extends VBox {

    private Canvas canvas;
    private ViewableModel model;

    private Button resetButton;

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;

    private final int NUMBER_OF_ROWS = 6;
    private final int NUMBER_OF_COLUMNS = 7;
    private int PIECE_SIZE = 150;
    private int width;
    private int height;

    public MainView(ViewableModel model) {
        this.model = model;
        this.width = PIECE_SIZE * NUMBER_OF_COLUMNS;
        this.height = PIECE_SIZE * NUMBER_OF_ROWS;
        this.canvas = new Canvas(width, height);
        initializeButtons();
        addChildren();
    }

    private void initializeButtons() {
        this.resetButton = new Button("Reset");
        this.button1 = new Button("1");
        this.button2 = new Button("2");
        this.button3 = new Button("3");
        this.button4 = new Button("4");
        this.button5 = new Button("5");
        this.button6 = new Button("6");
        this.button7 = new Button("7");
    }

    private void addChildren() {
        this.getChildren().addAll(
                this.resetButton,
                this.button1,
                this.button2,
                this.button3,
                this.button4,
                this.button5,
                this.button6,
                this.button7,
                this.canvas);
    }

    public void draw() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();

        g.setFill(Color.LIGHTBLUE);
        g.fillRect(0, 0, width, height);
    }

}
