package no.vebb.fourinarow.controller;

public class MainController {
    
    private ControllableModel model;

    public MainController(ControllableModel model) {
        this.model = model;
    }

    public void reset() {
        model.reset();
    }

    public void placeZero() {
        model.placePiece(0);
    }

    public void placeOne() {
        model.placePiece(1);
    }

    public void placeTwo() {
        model.placePiece(2);
    }

    public void placeThree() {
        model.placePiece(3);
    }

    public void placeFour() {
        model.placePiece(4);
    }

    public void placeFive() {
        model.placePiece(5);
    }

    public void placeSix() {
        model.placePiece(6);
    }
}
