package no.vebb.boardgames.connectfour.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board implements Iterable<CFCell> {
    private final int NUMBER_OF_ROWS;
    private final int NUMBER_OF_COLUMNS;

    private List<CFCell> boardCells;

    public Board(int numberOfRows, int numberOfColumns) {
        if (numberOfRows <= 0 || numberOfColumns <= 0) {
            throw new IllegalArgumentException("Number of rows and columns must be greater than zero");
        }
        this.NUMBER_OF_ROWS = numberOfRows;
        this.NUMBER_OF_COLUMNS = numberOfColumns;
        initializeBoard();
    }

    private void initializeBoard() {
        int numberOfCells = NUMBER_OF_ROWS * NUMBER_OF_COLUMNS;
        boardCells = new ArrayList<>(numberOfCells);

        for (int column = 0; column < NUMBER_OF_COLUMNS; column++) {
            for (int row = 0; row < NUMBER_OF_ROWS; row++) {
                boardCells.add(new CFCell(CellType.EMPTY, new CellPosition(row, column)));
            }
        }
    }

    private int coordinatesToIndex(CellPosition cellPos) {
        return cellPos.getRow() + cellPos.getColumn() * NUMBER_OF_ROWS;
    }

    private void set(CFCell cell) {
        int index = coordinatesToIndex(cell.getCellPosition());
        boardCells.set(index, cell);
    }

    public CFCell get(CellPosition cellPos) {
        validPositionCheck(cellPos);
        int index = coordinatesToIndex(cellPos);
        return boardCells.get(index);
    }

    private void validPositionCheck(CellPosition cellPos) {
        if (!isValidPosition(cellPos)) {
            throw new IllegalArgumentException("The cell position '" + cellPos + "' is not legal on a board with "
                    + NUMBER_OF_ROWS + " rows and " + NUMBER_OF_COLUMNS + " columns.");
        }
    }

    private boolean isValidPosition(CellPosition cellPos) {
        int row = cellPos.getRow();
        int column = cellPos.getColumn();
        boolean isValidRow = row >= 0 && row < NUMBER_OF_ROWS;
        boolean isValidColumn = column >= 0 && column < NUMBER_OF_COLUMNS;
        return isValidRow && isValidColumn;
    }

    @Override
    public Iterator<CFCell> iterator() {
        return boardCells.iterator();
    }

    private List<CFCell> getRow(int row) {
        List<CFCell> cells = new ArrayList<>();

        for (int column = 0; column < NUMBER_OF_COLUMNS; column++) {
            cells.add(get(new CellPosition(row, column)));
        }

        return cells;
    }

    public List<List<CFCell>> getAllRows() {
        List<List<CFCell>> listOfRows = new ArrayList<>();
        for (int row = 0; row < NUMBER_OF_ROWS; row++) {
            listOfRows.add(getRow(row));
        }
        return listOfRows;
    }

    private List<CFCell> getColumn(int column) {
        List<CFCell> cells = new ArrayList<>();

        for (int row = 0; row < NUMBER_OF_ROWS; row++) {
            cells.add(get(new CellPosition(row, column)));
        }

        return cells;
    }

    public List<List<CFCell>> getAllColumns() {
        List<List<CFCell>> listOfColumns = new ArrayList<>();
        for (int column = 0; column < NUMBER_OF_COLUMNS; column++) {
            listOfColumns.add(getColumn(column));
        }
        return listOfColumns;
    }

    private List<CFCell> getDiagonal(CellPosition cellPosition, boolean fromTop) {
        int i = -1;
        if (fromTop) {
            i = 1;
        }
        List<CFCell> diagonal = new ArrayList<>();
        CellPosition topPos = cellPosition;
        while (true) {
            CellPosition tempPos = new CellPosition(topPos.getRow() + i, topPos.getColumn() - 1);
            if (isValidPosition(tempPos)) {
                topPos = tempPos;
            } else {
                break;
            }
        }

        while (isValidPosition(topPos)) {
            diagonal.add(get(topPos));
            topPos = new CellPosition(topPos.getRow() - i, topPos.getColumn() + 1);
        }

        return diagonal;
    }

    // Returns the two diagonals from the given position
    private List<List<CFCell>> getDiagonalsFromCell(CellPosition cellPosition) {
        List<List<CFCell>> diagonals = new ArrayList<>();
        diagonals.add(getDiagonal(cellPosition, true));
        diagonals.add(getDiagonal(cellPosition, false));
        return diagonals;
    }

    // Return the four lines from the given position
    public List<List<CFCell>> getLinesFromCell(CellPosition cellPosition) {
        validPositionCheck(cellPosition);
        List<List<CFCell>> lines = new ArrayList<>();
        lines.add(getColumn(cellPosition.getColumn()));
        lines.add(getRow(cellPosition.getRow()));
        lines.addAll(getDiagonalsFromCell(cellPosition));
        return lines;
    }

    private void playerTypeCheck(CellType type) {
        if (type == CellType.EMPTY) {
            throw new IllegalArgumentException("The player can not be of the empty type.");
        }
    }

    public CellPosition placePiece(int column, CellType playerColor) {
        playerTypeCheck(playerColor);
        CellPosition positionToPlace = getPlacementPosition(column);
        set(new CFCell(playerColor, positionToPlace));
        return positionToPlace;
    }

    public CellPosition getPlacementPosition(int column) {
        if (column < 0 || column >= NUMBER_OF_COLUMNS) {
            throw new IllegalArgumentException(
                    "The column '" + column + "' is not on a board with " + NUMBER_OF_COLUMNS + " columns");
        }
        for (CFCell cell : getColumn(column)) {
            if (cell.getValue() == CellType.EMPTY) {
                CellPosition positionToPlace = cell.getCellPosition();
                return positionToPlace;
            }
        }

        return null;
    }
}
