package no.vebb.fourinarow.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import no.vebb.fourinarow.controller.MainController;
import no.vebb.fourinarow.model.Cell;
import no.vebb.fourinarow.model.CellPosition;

public class MainView extends VBox {

    private Canvas canvas;

    private MainController controller;
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
    private int pieceSize = 100;
    private int boardWidth;
    private int boardHeight;

    public MainView(ViewableModel model, MainController controller) {
        this.model = model;
        this.boardWidth = pieceSize * NUMBER_OF_COLUMNS;
        this.boardHeight = pieceSize * NUMBER_OF_ROWS;
        this.canvas = new Canvas(boardWidth, boardHeight);
        this.controller = controller;
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

        this.resetButton.setOnAction(actionEvent -> { controller.reset(); draw();});
        this.button1.setOnAction(actionEvent -> { controller.placeZero(); draw();});
        this.button2.setOnAction(actionEvent -> { controller.placeOne(); draw();});
        this.button3.setOnAction(actionEvent -> { controller.placeTwo(); draw();});
        this.button4.setOnAction(actionEvent -> { controller.placeThree(); draw();});
        this.button5.setOnAction(actionEvent -> { controller.placeFour(); draw();});
        this.button6.setOnAction(actionEvent -> { controller.placeFive(); draw();});
        this.button7.setOnAction(actionEvent -> { controller.placeSix(); draw();});
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
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, boardWidth, boardHeight);
        for (Cell cell : model.getBoardCells()) {
            drawCell(cell, g);
        }
    }

    private void drawCell(Cell cell, GraphicsContext g) {
        switch (cell.getColor()) {
            case BLUE:
                g.setFill(Color.BLUE);
                break;
            case RED:
                g.setFill(Color.RED);
                break;
            case EMPTY:
                g.setFill(Color.GREY);
                break;
            default:
                break;
        }
        CellPosition cellPos = cell.getCellPosition();
        int row = cellPos.getRow();
        int column = cellPos.getColumn();

        int x = column * pieceSize;
        int y = (NUMBER_OF_ROWS - row - 1) * pieceSize;
        g.fillOval(x, y, pieceSize, pieceSize);
    }

}
