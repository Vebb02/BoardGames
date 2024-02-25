package no.vebb.fourinarow.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board implements Iterable<Cell>{
    private int numberOfRows;
    private int numberOfColumns;

    private List<Cell> boardCells;

    public Board(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        initializeBoard();
    }

    private void initializeBoard() {
        int numberOfCells = numberOfRows * numberOfColumns;
        boardCells = new ArrayList<>(numberOfCells);

        for (int column = 0; column < numberOfColumns; column++) {
            for (int row = 0; row < numberOfRows; row++) {
                set(row, column, new Cell(CellType.EMPTY, new CellPosition(row, column)));
            }
        }
    }

    private int coordinatesToIndex(int row, int column) {
        return row + column * numberOfRows;
    }

    private void set(int row, int column, Cell cell) {
        int index = coordinatesToIndex(row, column);
        boardCells.set(index, cell);
    }

    private Cell get(int row, int column) {
        int index = coordinatesToIndex(row, column);
        return boardCells.get(index);
    }

    @Override
    public Iterator<Cell> iterator() {
        return boardCells.iterator();
    }

    public List<Cell> getRow(int row) {
        List<Cell> cells = new ArrayList<>();
        
        for (int column = 0; column < numberOfColumns; column++) {
            cells.add(get(row, column));
        }

        return cells;
    }

    public List<List<Cell>> getAllRows() {
        List<List<Cell>> listOfRows = new ArrayList<>();
        for (int row = 0; row < numberOfRows; row++) {
            listOfRows.add(getRow(row));
        }
        return listOfRows;
    }

    public List<Cell> getColumn(int column) {
        List<Cell> cells = new ArrayList<>();
        
        for (int row = 0; row < numberOfRows; row++) {
            cells.add(get(row, column));
        }

        return cells;
    }

    public List<List<Cell>> getAllColumns() {
        List<List<Cell>> listOfColumns = new ArrayList<>();
        for (int column = 0; column < numberOfColumns; column++) {
            listOfColumns.add(getColumn(column));
        }
        return listOfColumns;
    }

    // Returns the two diagonals from the given position
    public List<List<Cell>> getDiagonalsFromCell(CellPosition cellPosition) {
        // TODO: implement
        return null;
    }

    // Return the four lines from the given position
    public List<List<Cell>> getLinesFromCell(CellPosition cellPosition) {
        // TODO: implement
        return null;
    }   
}
