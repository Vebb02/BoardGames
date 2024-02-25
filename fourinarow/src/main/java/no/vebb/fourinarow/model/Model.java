package no.vebb.fourinarow.model;

import java.util.ArrayList;
import java.util.List;

import no.vebb.fourinarow.controller.ControllableModel;
import no.vebb.fourinarow.view.ViewableModel;

public class Model implements ControllableModel, ViewableModel {

    private Board board;
    private CellType turnColor;
    private GameState gameState;
    private int numberOfTurns;

    private final int NUMBER_OF_ROWS;
    private final int NUMBER_OF_COLUMNS;

    public Model(int rows, int columms) {
        this.NUMBER_OF_ROWS = rows;
        this.NUMBER_OF_COLUMNS = columms;
        reset();
    }

    public void reset() {
        this.board = new Board(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS);
        this.turnColor = CellType.BLUE;
        this.gameState = GameState.IN_GAME;
        numberOfTurns = 0;
    }

    @Override
    public List<Cell> getBoardCells() {
        List<Cell> boardCells = new ArrayList<>();
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

    private boolean isValidColumn(int column) {
        return column < 0 || column >= NUMBER_OF_COLUMNS;
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
        for (List<Cell> line : board.getLinesFromCell(placedPosition)) {
            Cell lastCell = null;
            int counter = 1;
            for (Cell cell : line) {
                if (lastCell != null) {
                    CellType currentColor = cell.getColor();
                    boolean hasSamecolor = lastCell.getColor() == currentColor;
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
}
