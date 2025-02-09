package org.baker.simplepaint.tools;

import java.util.Optional;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import org.baker.simplepaint.PaintCanvasContext;

public class OvalTool extends AbstractDrawTool {
  private Point2D nullableStartPoint;

  public OvalTool(PaintCanvasContext paintCanvasContext) {
    super(paintCanvasContext);
    this.nullableStartPoint = null;
  }

  public void drawOval(
      GraphicsContext graphicsContext,
      MouseEvent mouseEvent,
      Runnable absentStartPointFunction
  ) {
    this.paintCanvasContext.clearTempGraphicsContext();
    Optional.ofNullable(this.nullableStartPoint).ifPresentOrElse(startPoint -> {
          var boundingBoxInfo = this.getBoundingBox(startPoint, mouseEvent);
          graphicsContext.strokeOval(
              boundingBoxInfo.topLeftCorner().getX(),
              boundingBoxInfo.topLeftCorner().getY(),
              boundingBoxInfo.width(),
              boundingBoxInfo.height());
        },
        absentStartPointFunction
    );
  }

  @Override
  public void handleMouseDragged(MouseEvent mouseEvent) {
    // draw rect on temp canvas, set start point of new rect if necessary
    this.drawOval(
        this.paintCanvasContext.getTempGraphicsContext(),
        mouseEvent,
        () -> this.nullableStartPoint = new Point2D(mouseEvent.getX(), mouseEvent.getY()));
  }

  @Override
  public void handleMouseReleased(MouseEvent mouseEvent) {
    this.drawOval(
        this.paintCanvasContext.getPrimaryGraphicsContext(),
        mouseEvent,
        () ->  { });
    // reset rect param
    this.nullableStartPoint = null;
  }
}
