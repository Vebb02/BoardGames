package no.vebb.boardgames.grid;

public class Cell<T> {

    private T value;
    private CellPosition cellPosition;

    public Cell(T value, CellPosition cellPosition) {
        this.value = value;
        this.cellPosition = cellPosition;
    }

    public T getValue() {
        return value;
    }

    public CellPosition getCellPosition() {
        return cellPosition;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        Cell<?> other = (Cell<?>) obj;
        if (value != other.value)
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
        return "Cell [color=" + value + ", cellPosition=" + cellPosition + "]";
    }


}
