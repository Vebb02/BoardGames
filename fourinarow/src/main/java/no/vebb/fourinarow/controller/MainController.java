package no.vebb.fourinarow.controller;

import java.io.IOException;

public class MainController {
    
    private ControllableModel model;

    public MainController(ControllableModel model) {
        this.model = model;
    }

    private void reset() throws IOException {
        model.reset();
    }

    private void placeZero() throws IOException {
        model.placePiece(0);
    }

    private void placeOne() throws IOException {
        model.placePiece(1);
    }

    private void placeTwo() throws IOException {
        model.placePiece(2);
    }

    private void placeThree() throws IOException {
        model.placePiece(3);
    }

    private void placeFour() throws IOException {
        model.placePiece(4);
    }

    private void placeFive() throws IOException {
        model.placePiece(5);
    }

    private void placeSix() throws IOException {
        model.placePiece(6);
    }
}
