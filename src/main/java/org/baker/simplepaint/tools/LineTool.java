package org.baker.simplepaint.tools;

import java.util.Optional;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import org.baker.simplepaint.PaintCanvasContext;

public class LineTool extends AbstractDrawTool {
  private Point2D nullableStartPoint;

  public LineTool(PaintCanvasContext paintCanvasContext) {
    super(paintCanvasContext);
    this.nullableStartPoint = null;
  }

  public void drawLine(
      GraphicsContext graphicsContext,
      MouseEvent mouseEvent,
      Runnable absentStartPointFunction
  ) {
    this.paintCanvasContext.clearTempGraphicsContext();
    Optional.ofNullable(this.nullableStartPoint).ifPresentOrElse(startPoint ->
        graphicsContext.strokeLine(
            startPoint.getX(),
            startPoint.getY(),
            mouseEvent.getX(),
            mouseEvent.getY()),
        absentStartPointFunction
    );
  }

  @Override
  public void handleMouseDragged(MouseEvent mouseEvent) {
    // draw line on temp canvas, set start point of new line if necessary
    this.drawLine(
        this.paintCanvasContext.getTempGraphicsContext(),
        mouseEvent,
        () -> this.nullableStartPoint = new Point2D(mouseEvent.getX(), mouseEvent.getY())
    );
  }

  @Override
  public void handleMouseReleased(MouseEvent mouseEvent) {
    // draw line on actual canvas
    this.drawLine(this.paintCanvasContext.getPrimaryGraphicsContext(), mouseEvent, () -> { });

    // reset start point
    this.nullableStartPoint = null;
  }
}
