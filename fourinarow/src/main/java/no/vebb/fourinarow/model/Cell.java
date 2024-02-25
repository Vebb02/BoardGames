package no.vebb.fourinarow.model;

public class Cell {

    private CellType color;
    private CellPosition cellPosition;

    public Cell(CellType color, CellPosition cellPosition) {
        this.color = color;
        this.cellPosition = cellPosition;
    }

    public CellType getColor() {
        return color;
    }

    public CellPosition getCellPosition() {
        return cellPosition;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + ((cellPosition == null) ? 0 : cellPosition.hashCode());
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
        Cell other = (Cell) obj;
        if (color != other.color)
            return false;
        if (cellPosition == null) {
            if (other.cellPosition != null)
                return false;
        } else if (!cellPosition.equals(other.cellPosition))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Cell [color=" + color + ", cellPosition=" + cellPosition + "]";
    }


}
