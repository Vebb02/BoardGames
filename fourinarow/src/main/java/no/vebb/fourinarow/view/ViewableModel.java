package no.vebb.fourinarow.view;

import java.util.List;

import no.vebb.fourinarow.model.CFCell;
import no.vebb.fourinarow.model.CellType;
import no.vebb.fourinarow.model.GameState;

public interface ViewableModel {
    
    public List<CFCell> getBoardCells();

    public GameState getGameState();
    
    public CellType getTurnColor();

    public CFCell getShadowPiece(int column);

}
