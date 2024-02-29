package no.vebb.boardgames.connectfour.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModelTest {
    private Model model;
    private GameState[] winner = new GameState[] { GameState.BLUE_WON, GameState.RED_WON };

    @BeforeAll
    static void setUpBeforeAll() {

    }

    @BeforeEach
    void setUpBeforeEach() {
        model = new Model(6, 7);
    }

    @Test
    void horizontalFourInARowTest() {
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                assertEquals(model.getGameState(), GameState.IN_GAME);
                model.placePiece(i);
                model.placePiece(i);
            }
            assertEquals(model.getGameState(), winner[j]);
            model.reset();
        }
    }

    @Test
    void verticalFourInARowTest() {
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                assertEquals(model.getGameState(), GameState.IN_GAME);
                model.placePiece(0);
                model.placePiece(1);
            }
            assertEquals(model.getGameState(), winner[j]);
            model.reset();
        }
    }

    @Test
    void topToBottomFourInARowTest() {
        int[] placements = new int[] { 3, 2, 2, 1, 0, 1, 1, 0, 6, 0, 0 };
        for (int j = 0; j < 2; j++) {
            for (int i : placements) {
                assertEquals(model.getGameState(), GameState.IN_GAME);
                model.placePiece(i);
            }
            assertEquals(model.getGameState(), winner[j]);
            model.reset();
        }
    }

    @Test
    void bottomToTopFourInARowTest() {
        int[] placements = new int[] { 3, 4, 4, 5, 6, 5, 5, 6, 0, 6, 6 };
        for (int j = 0; j < 2; j++) {
            for (int i : placements) {
                assertEquals(model.getGameState(), GameState.IN_GAME);
                model.placePiece(i);
            }
            assertEquals(model.getGameState(), winner[j]);
            model.reset();
        }
    }
}
