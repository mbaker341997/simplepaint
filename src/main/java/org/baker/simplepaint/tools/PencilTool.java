package org.baker.simplepaint.tools;

import java.util.Optional;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import org.baker.simplepaint.PaintCanvasContext;

public class PencilTool extends AbstractDrawTool {

  private final Boolean isEraseMode;
  private Point2D nullablePreviousPoint;

  public PencilTool(PaintCanvasContext paintCanvasContext, Boolean isEraseMode) {
    super(paintCanvasContext);
    this.isEraseMode = isEraseMode;
    this.nullablePreviousPoint = null;
  }

  @Override
  public void handleMouseDragged(MouseEvent mouseEvent) {
    var graphicsContext = this.paintCanvasContext.getPrimaryGraphicsContext();
    if (this.isEraseMode) {
      graphicsContext.setStroke(this.paintCanvasContext.getEraseColor());
    }
    Optional.ofNullable(this.nullablePreviousPoint).ifPresentOrElse(
        previousPoint -> graphicsContext.strokeLine(
            previousPoint.getX(), previousPoint.getY(),
            mouseEvent.getX(), mouseEvent.getY()),
        () -> this.drawPoint(mouseEvent.getX(), mouseEvent.getY())
    );
    this.nullablePreviousPoint = new Point2D(mouseEvent.getX(), mouseEvent.getY());
  }

  @Override
  public void handleMouseReleased(MouseEvent mouseEvent) {
    if (this.isEraseMode) {
      this.paintCanvasContext.getPrimaryGraphicsContext()
          .setStroke(this.paintCanvasContext.getDrawColor());
    }
    this.nullablePreviousPoint = null;
  }
}
