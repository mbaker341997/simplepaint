package org.baker.simplepaint.tools;

import java.util.Optional;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import org.baker.simplepaint.PaintCanvasContext;

public class RectangleTool extends AbstractDrawTool {
  private Point2D nullableStartPoint;

  public RectangleTool(PaintCanvasContext paintCanvasContext) {
    super(paintCanvasContext);
    this.nullableStartPoint = null;
  }

  public void drawRect(
      GraphicsContext graphicsContext,
      MouseEvent mouseEvent,
      Runnable absentStartPointFunction
  ) {
    this.paintCanvasContext.clearTempGraphicsContext();
    Optional.ofNullable(this.nullableStartPoint).ifPresentOrElse(startPoint -> {
          // mouse bottom right
          if (mouseEvent.getX() > startPoint.getX() && mouseEvent.getY() > startPoint.getY()) {
            graphicsContext.strokeRect(
                startPoint.getX(),
                startPoint.getY(),
                mouseEvent.getX() - startPoint.getX(),
                mouseEvent.getY() - startPoint.getY());
          }
          // mouse top right
          else if (mouseEvent.getX() > startPoint.getX() && mouseEvent.getY() < startPoint.getY()) {
            graphicsContext.strokeRect(
                startPoint.getX(),
                mouseEvent.getY(),
                mouseEvent.getX() - startPoint.getX(),
                startPoint.getY() - mouseEvent.getY());
          }
          // mouse top left
          else if (mouseEvent.getX() < startPoint.getX() && mouseEvent.getY() < startPoint.getY()) {
            graphicsContext.strokeRect(
                mouseEvent.getX(),
                mouseEvent.getY(),
                startPoint.getX() - mouseEvent.getX(),
                startPoint.getY() - mouseEvent.getY());
          }
          // mouse bottom left
          else if (mouseEvent.getX() < startPoint.getX() && mouseEvent.getY() > startPoint.getY()) {
            graphicsContext.strokeRect(
                mouseEvent.getX(),
                startPoint.getY(),
                startPoint.getX() - mouseEvent.getX(),
                mouseEvent.getY() - startPoint.getY());
          }
        },
        absentStartPointFunction
    );
  }

  @Override
  public void handleMouseDragged(MouseEvent mouseEvent) {
    // draw rect on temp canvas, set start point of new rect if necessary
    this.drawRect(
        this.paintCanvasContext.getTempGraphicsContext(),
        mouseEvent,
        () -> this.nullableStartPoint = new Point2D(mouseEvent.getX(), mouseEvent.getY()));
  }

  @Override
  public void handleMouseReleased(MouseEvent mouseEvent) {
    this.drawRect(
        this.paintCanvasContext.getPrimaryGraphicsContext(),
        mouseEvent,
        () ->  { });
    // reset rect param
    this.nullableStartPoint = null;
  }
}
