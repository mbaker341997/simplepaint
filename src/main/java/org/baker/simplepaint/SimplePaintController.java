package org.baker.simplepaint;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SimplePaintController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}