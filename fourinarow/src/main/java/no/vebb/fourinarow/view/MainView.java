package no.vebb.fourinarow.view;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import no.vebb.fourinarow.controller.MainController;
import no.vebb.fourinarow.model.CFCell;
import no.vebb.fourinarow.model.CellPosition;
import no.vebb.fourinarow.model.GameState;

public class MainView extends VBox {

    private Pane pane;
    private Canvas canvas;

    private MainController controller;
    private ViewableModel model;

    private Button resetButton;
    private Label stateLabel;

    private CFCell previewCell = null;
    private CFCell shadowCell = null;
    private double opacity = 0.5;

    private Image blueImage;
    private Image redImage;

    private final int NUMBER_OF_ROWS;
    private final int NUMBER_OF_COLUMNS;
    private double pieceSize = 80;
    private double margin;
    private double boardWidth;
    private double boardHeight;

    public MainView(ViewableModel model, MainController controller, int rows, int columns) {
        this.NUMBER_OF_ROWS = rows;
        this.NUMBER_OF_COLUMNS = columns;
        this.model = model;
        this.controller = controller;
        setBoardSize();
        initializeResetButton();
        initializeCanvas();
        initializeImages();
        Platform.runLater(() -> {
            rescale();
        });
    }

    private void initializeImages() {
        try {
            blueImage = new Image(getClass().getResourceAsStream("/no/vebb/fourinarow/view/Blue.png"));
            redImage = new Image(getClass().getResourceAsStream("/no/vebb/fourinarow/view/Red.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeCanvas() {
        this.canvas = new Canvas(boardWidth, boardHeight);
        this.stateLabel = new Label();
        this.pane = new Pane();
        this.getChildren().addAll(stateLabel, pane);
        this.pane.getChildren().add(canvas);

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                EventType<? extends MouseEvent> eventType = e.getEventType();
                int x = (int) ((e.getSceneX() - canvas.getBoundsInParent().getMinX()) / boardWidth * NUMBER_OF_COLUMNS);
                if (eventType == MouseEvent.MOUSE_CLICKED) {
                    controller.placePiece(x);
                }
                if (model.getGameState() == GameState.IN_GAME && eventType != MouseEvent.MOUSE_EXITED) {
                    previewCell = new CFCell(model.getTurnColor(), new CellPosition(NUMBER_OF_ROWS, x));
                    shadowCell = model.getShadowPiece(x);
                } else {
                    previewCell = null;
                    shadowCell = null;
                }
                draw();
            }
        };

        this.canvas.setOnMouseClicked(eventHandler);
        this.canvas.setOnMouseMoved(eventHandler);
        this.canvas.setOnMouseExited(eventHandler);
        this.canvas.setOnMouseEntered(eventHandler);
        this.canvas.setOnMouseDragged(eventHandler);
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
        g.fillRect(0, pieceSize + 1.5 * margin, this.getWidth(), this.getHeight());
        for (CFCell cell : model.getBoardCells()) {
            drawCell(cell, g);
        }
        drawCell(previewCell, g);
        g.setGlobalAlpha(opacity);
        drawCell(shadowCell, g);
        g.setGlobalAlpha(1);
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

    private void drawCell(CFCell cell, GraphicsContext g) {
        if (cell == null)
            return;
        CellPosition cellPos = cell.getCellPosition();
        int row = cellPos.getRow();
        int column = cellPos.getColumn();

        double x = column * (pieceSize + margin) + margin;
        double y = (NUMBER_OF_ROWS - row) * (pieceSize + margin) + margin;

        switch (cell.getValue()) {
            case BLUE:
                g.drawImage(blueImage, x, y, pieceSize, pieceSize);
                break;
            case RED:
                g.drawImage(redImage, x, y, pieceSize, pieceSize);
                break;
            case EMPTY:
                g.setFill(Color.LIGHTGRAY);
                g.fillOval(x, y, pieceSize, pieceSize);
                break;
            default:
                break;
        }
    }

    public void rescale() {
        double y = getHeightOfMenu();
        double width = this.getWidth();
        double height = this.getHeight();
        margin = Math.min(width, height) / 100;
        pieceSize = Math.min(
                (width - margin) / ((double) NUMBER_OF_COLUMNS) - margin,
                (height - y - margin) / ((double) NUMBER_OF_ROWS + 1) - margin);
        setBoardSize();
        setCanvasSize();
        draw();
    }

    private double getHeightOfMenu() {
        return stateLabel.getHeight() + resetButton.getHeight();
    }

    private void setBoardSize() {
        this.boardWidth = (pieceSize + margin) * (float) NUMBER_OF_COLUMNS + margin;
        this.boardHeight = (pieceSize + margin) * (float) (NUMBER_OF_ROWS + 1) + margin;
    }

    private void setCanvasSize() {
        this.canvas.setWidth(boardWidth);
        this.canvas.setHeight(boardHeight);

        pane.setPrefSize(canvas.getWidth(), canvas.getHeight());
        canvas.layoutXProperty().bind(pane.widthProperty().subtract(canvas.getWidth()).divide(2));
        canvas.layoutYProperty().bind(pane.heightProperty().subtract(canvas.getHeight()).divide(2));
    }
}
