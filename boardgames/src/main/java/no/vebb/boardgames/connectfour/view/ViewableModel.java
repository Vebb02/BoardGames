package no.vebb.boardgames.connectfour.view;

import java.util.List;

import no.vebb.boardgames.connectfour.model.CFCell;
import no.vebb.boardgames.connectfour.model.CellType;
import no.vebb.boardgames.connectfour.model.GameState;

public interface ViewableModel {
    
    public List<CFCell> getBoardCells();

    public GameState getGameState();
    
    public CellType getTurnColor();

    public CFCell getShadowPiece(int column);

}
