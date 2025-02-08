package org.baker.simplepaint.tools;

import javafx.scene.input.MouseEvent;

public interface Tool {
  void handleMouseDragged(MouseEvent mouseEvent);

  void handleMouseReleased(MouseEvent mouseEvent);

  void handleMouseClicked(MouseEvent mouseEvent);
}
