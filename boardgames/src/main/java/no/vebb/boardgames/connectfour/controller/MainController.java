package no.vebb.boardgames.connectfour.controller;

public class MainController {
    
    private ControllableModel model;

    public MainController(ControllableModel model) {
        this.model = model;
    }

    public void reset() {
        model.reset();
    }

    public void placePiece(int i) {
        model.placePiece(i);
    }
}
