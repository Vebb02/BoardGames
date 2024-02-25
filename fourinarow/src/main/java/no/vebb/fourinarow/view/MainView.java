package no.vebb.fourinarow.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import no.vebb.fourinarow.controller.MainController;
import no.vebb.fourinarow.model.Cell;
import no.vebb.fourinarow.model.CellPosition;
import no.vebb.fourinarow.model.CellType;
import no.vebb.fourinarow.model.GameState;

public class MainView extends VBox {

    private Canvas canvas;

    private MainController controller;
    private ViewableModel model;

    private Button resetButton;
    private Label stateLabel;

    private final int NUMBER_OF_ROWS;
    private final int NUMBER_OF_COLUMNS;
    private int pieceSize = 100;
    private int boardWidth;
    private int boardHeight;

    public MainView(ViewableModel model, MainController controller, int rows, int columns) {
        this.NUMBER_OF_ROWS = rows;
        this.NUMBER_OF_COLUMNS = columns;
        this.model = model;
        this.boardWidth = pieceSize * NUMBER_OF_COLUMNS;
        this.boardHeight = pieceSize * NUMBER_OF_ROWS;
        this.canvas = new Canvas(boardWidth, boardHeight);
        this.controller = controller;
        this.stateLabel = new Label();
        initializeButtons();
        this.getChildren().addAll(stateLabel, this.canvas);
    }

    private void initializeButtons() {
        this.resetButton = new Button("Reset");
        this.resetButton.setOnAction(actionEvent -> {
            controller.reset();
            draw();
        });
        this.getChildren().add(this.resetButton);

        for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
            Button button = new Button(Integer.toString(i+1));
            final int column = i;
            button.setOnAction(actionEvent -> {
                controller.placePiece(column);
                draw();
            });
            this.getChildren().add(button);
        }
    }

    public void draw() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, boardWidth, boardHeight);
        for (Cell cell : model.getBoardCells()) {
            drawCell(cell, g);
        }
        updateLabel();
    }

    private void updateLabel() {
        GameState gameState = model.getGameState();
        StringBuilder text = new StringBuilder();
        switch (gameState) {
            case IN_GAME:
                text.append("It's ");
                if (model.getTurnColor() == CellType.BLUE) {
                    text.append("blue");
                } else {
                    text.append("red");
                }
                text.append("'s turn");
                break;
            case TIE:
                text.append("It's a tie");
                break;
            case BLUE_WON:
                text.append("Blue wins");
                break;
            case RED_WON:
                text.append("Red wins");
                break;
            default:
                break;
        }
        stateLabel.setText(text.toString());
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
