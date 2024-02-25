package no.vebb.fourinarow.view;

import java.util.List;

import no.vebb.fourinarow.model.Cell;
import no.vebb.fourinarow.model.CellType;
import no.vebb.fourinarow.model.GameState;

public interface ViewableModel {
    
    public List<Cell> getBoardCells();

    public GameState getGameState();
    
    public CellType getTurnColor();

}
