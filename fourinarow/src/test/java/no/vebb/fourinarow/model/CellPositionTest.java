package no.vebb.fourinarow.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class CellPositionTest {
    
    @Test
    void equalsTest() {
        assertEquals(new CellPosition(1, 5), new CellPosition(1, 5));
        assertNotEquals(new CellPosition(1, 2), new CellPosition(2, 1));
    }
}
