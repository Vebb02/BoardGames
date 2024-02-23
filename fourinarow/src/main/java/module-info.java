module no.vebb.fourinarow {
    requires javafx.controls;
    requires javafx.fxml;

    opens no.vebb.fourinarow to javafx.fxml;
    exports no.vebb.fourinarow;
}
