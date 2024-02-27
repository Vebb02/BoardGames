package no.vebb.boardgames.connectfour.model;

import java.util.ArrayList;
import java.util.List;

import no.vebb.boardgames.connectfour.controller.ControllableModel;
import no.vebb.boardgames.connectfour.view.ViewableModel;

public class Model implements ControllableModel, ViewableModel {

    private Board board;
    private CellType turnColor;
    private CellType startingColor = CellType.RED;
    private GameState gameState;
    private int numberOfTurns = 1;

    private final int NUMBER_OF_ROWS;
    private final int NUMBER_OF_COLUMNS;

    public Model(int rows, int columms) {
        this.NUMBER_OF_ROWS = rows;
        this.NUMBER_OF_COLUMNS = columms;
        reset();
    }

    @Override
    public void reset() {
        if (numberOfTurns > 0) {
            this.board = new Board(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS);
            changeStartingColor();
            this.turnColor = startingColor;
            this.gameState = GameState.IN_GAME;
            numberOfTurns = 0;
        }
    }

    @Override
    public List<CFCell> getBoardCells() {
        List<CFCell> boardCells = new ArrayList<>();
        board.forEach(boardCells::add);
        return boardCells;
    }

    @Override
    public void placePiece(int column) {
        if (isInGame()) {
            if (isValidColumn(column)) {
                CellPosition placedPosition = board.placePiece(column, turnColor);
                if (placedPosition != null) {
                    numberOfTurns++;
                    changeTurn();
                    evaluatePosition(placedPosition);
                    checkFullBoard();
                }
            }
        }
    }

    @Override
    public CFCell getShadowPiece(int column) {
        if (isInGame()) {
            if (isValidColumn(column)) {
                CellPosition placedPosition = board.getPlacementPosition(column);
                if (placedPosition != null) {
                    return new CFCell(turnColor, placedPosition);
                }
            }
        }
        return null;
    }

    private boolean isValidColumn(int column) {
        return column >= 0 && column < NUMBER_OF_COLUMNS;
    }

    private boolean isInGame() {
        return gameState == GameState.IN_GAME;
    }

    private void changeTurn() {
        if (turnColor == CellType.BLUE) {
            turnColor = CellType.RED;
        } else if (turnColor == CellType.RED) {
            turnColor = CellType.BLUE;
        } else {
            throw new RuntimeException("Turncolor is '" + turnColor + "' which is not a valid turn color.");
        }
    }

    private void evaluatePosition(CellPosition placedPosition) {
        for (List<CFCell> line : board.getLinesFromCell(placedPosition)) {
            CFCell lastCell = null;
            int counter = 1;
            for (CFCell cell : line) {
                if (lastCell != null) {
                    CellType currentColor = cell.getValue();
                    boolean hasSamecolor = lastCell.getValue() == currentColor;
                    if (hasSamecolor) {
                        counter++;

                        if (counter == 4) {
                            if (currentColor != CellType.EMPTY) {
                                endGame(currentColor);
                            }
                        }
                    } else {
                        counter = 1;
                    }
                }
                lastCell = cell;
            }
        }
    }

    private void endGame(CellType cellType) {
        switch (cellType) {
            case BLUE:
                gameState = GameState.BLUE_WON;
                break;
            case RED:
                gameState = GameState.RED_WON;
                break;
            case EMPTY:
                gameState = GameState.TIE;
                break;
            default:
                throw new IllegalArgumentException("Winner can't be null.");
        }
    }

    private void checkFullBoard() {
        if (numberOfTurns == NUMBER_OF_ROWS * NUMBER_OF_COLUMNS) {
            endGame(CellType.EMPTY);
        }
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public CellType getTurnColor() {
        return turnColor;
    }

    private void changeStartingColor() {
        if (startingColor == CellType.BLUE) {
            startingColor = CellType.RED;
        } else if (startingColor == CellType.RED) {
            startingColor = CellType.BLUE;
        } else {
            throw new RuntimeException(
                    "Starting color is '" + startingColor + "' which is not a valid starting color.");
        }
    }
}
