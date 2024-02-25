package no.vebb.fourinarow.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

public class BoardTest {
    
    private Board board;


    @BeforeAll
	static void setUpBeforeAll() {
    
    }

	@BeforeEach
	void setUpBeforeEach() {

	}


	@Test
	void createBoardTest() {
        illegalBoardExceptionTest();

        board = new Board(2, 2);

        List<Cell> expectedBoard = Arrays.asList(
            new Cell(CellType.EMPTY, new CellPosition(0, 0)),
            new Cell(CellType.EMPTY, new CellPosition(1, 0)),
            new Cell(CellType.EMPTY, new CellPosition(0, 1)),
            new Cell(CellType.EMPTY, new CellPosition(1, 1)));

        int sizeCounter = 0;
        for (Cell cell : board) {
            sizeCounter++;
            assertTrue(expectedBoard.contains(cell));
        }
        assertEquals(expectedBoard.size(), sizeCounter);
	}

    private void illegalBoardExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> new Board(0, 0));
        assertThrows(IllegalArgumentException.class, () -> new Board(0, 1));
        assertThrows(IllegalArgumentException.class, () -> new Board(1, 0));
        assertThrows(IllegalArgumentException.class, () -> new Board(-1, 1));
        assertThrows(IllegalArgumentException.class, () -> new Board(1, -1));
    }

}
