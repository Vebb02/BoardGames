package no.vebb.boardgames.grid;

public class CellPosition {
    private int row;
    private int column;

    public CellPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + row;
        result = prime * result + column;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CellPosition other = (CellPosition) obj;
        if (row != other.row)
            return false;
        if (column != other.column)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CellPosition [row=" + row + ", column=" + column + "]";
    }
}
