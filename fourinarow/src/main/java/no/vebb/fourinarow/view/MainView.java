package no.vebb.fourinarow.view;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import no.vebb.fourinarow.controller.MainController;
import no.vebb.fourinarow.model.Cell;
import no.vebb.fourinarow.model.CellPosition;
import no.vebb.fourinarow.model.GameState;

public class MainView extends VBox {

    private Pane pane;
    private Canvas canvas;

    private MainController controller;
    private ViewableModel model;

    private Button resetButton;
    private Label stateLabel;

    private Cell previewCell = null;

    private final int NUMBER_OF_ROWS;
    private final int NUMBER_OF_COLUMNS;
    private double pieceSize = 80;
    private double margin = 10;
    private double boardWidth;
    private double boardHeight;

    public MainView(ViewableModel model, MainController controller, int rows, int columns) {
        this.NUMBER_OF_ROWS = rows;
        this.NUMBER_OF_COLUMNS = columns;
        this.model = model;
        setBoardSize();
        this.canvas = new Canvas(boardWidth, boardHeight);
        this.controller = controller;
        this.stateLabel = new Label();
        initializeResetButton();
        initializeCanvas();
    }

    private void initializeCanvas() {
        this.pane = new Pane();
        this.getChildren().addAll(stateLabel, pane);
        this.pane.getChildren().add(canvas);

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                EventType<? extends MouseEvent> eventType = e.getEventType();
                int x = (int) (e.getSceneX() / boardWidth * NUMBER_OF_COLUMNS);
                if (eventType == MouseEvent.MOUSE_CLICKED) {
                    controller.placePiece(x);
                }
                if (model.getGameState() == GameState.IN_GAME && eventType != MouseEvent.MOUSE_EXITED) {
                    previewCell = new Cell(model.getTurnColor(), new CellPosition(NUMBER_OF_ROWS, x));
                } else {
                    previewCell = null;
                }
                draw();
            }
        };

        this.pane.setOnMouseClicked(eventHandler);
        this.pane.setOnMouseMoved(eventHandler);
        this.pane.setOnMouseExited(eventHandler);
        this.pane.setOnMouseEntered(eventHandler);
        this.pane.setOnMouseDragged(eventHandler);
    }

    private void initializeResetButton() {
        this.resetButton = new Button("Reset");
        this.resetButton.setOnAction(actionEvent -> {
            controller.reset();
            draw();
        });
        this.getChildren().add(this.resetButton);
    }

    public void draw() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setFill(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setFill(Color.BLACK);
        g.fillRect(0, pieceSize + margin, this.getWidth(), this.getHeight());
        for (Cell cell : model.getBoardCells()) {
            drawCell(cell, g);
        }
        drawCell(previewCell, g);
        updateLabel();
    }

    private void updateLabel() {
        GameState gameState = model.getGameState();
        StringBuilder text = new StringBuilder();
        switch (gameState) {
            case IN_GAME:
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
        if (cell == null)
            return;
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

        double x = column * (pieceSize + margin) + margin;
        double y = (NUMBER_OF_ROWS - row) * (pieceSize + margin) + margin;
        g.fillOval(x, y, pieceSize, pieceSize);
    }

    public void rescale() {
        double x = pane.getBoundsInParent().getMinX();
        double y = pane.getBoundsInParent().getMinY();
        double width = this.getWidth();
        double height = this.getHeight();
        pieceSize = Math.min((width - x) / NUMBER_OF_COLUMNS, (height - y) / NUMBER_OF_ROWS); // TODO: fix scaling
        setBoardSize();
        setCanvasSize();
        draw();
    }

    private void setBoardSize() {
        this.boardWidth = (pieceSize + margin) * NUMBER_OF_COLUMNS + margin;
        this.boardHeight = (pieceSize + margin) * (NUMBER_OF_ROWS + 1) + margin;
    }

    private void setCanvasSize() {
        this.canvas.setWidth(boardWidth);
        this.canvas.setHeight(boardHeight);
    }
}
