module no.vebb.boardgames {
    requires javafx.controls;
    requires javafx.fxml;

    opens no.vebb.boardgames.connectfour to javafx.fxml;
    exports no.vebb.boardgames.connectfour;
}
