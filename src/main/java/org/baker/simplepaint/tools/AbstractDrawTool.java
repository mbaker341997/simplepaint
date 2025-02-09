package org.baker.simplepaint.tools;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import org.baker.simplepaint.PaintCanvasContext;

public abstract class AbstractDrawTool implements Tool {
  protected final PaintCanvasContext paintCanvasContext;

  protected AbstractDrawTool(PaintCanvasContext paintCanvasContext) {
    this.paintCanvasContext = paintCanvasContext;
  }

  @Override
  public void handleMouseClicked(MouseEvent mouseEvent) {
    this.drawPoint(mouseEvent.getX(), mouseEvent.getY());
  }

  public void drawPoint(Double eventX, Double eventY) {
    var graphicsContext = this.paintCanvasContext.getPrimaryGraphicsContext();
    var lineWidth = graphicsContext.getLineWidth();
    graphicsContext.fillRect(
        eventX - (lineWidth / 2),
        eventY - (lineWidth / 2),
        lineWidth, lineWidth);
  }

  public record BoundingBoxInfo(
      Point2D topLeftCorner,
      Double width,
      Double height) {

  }

  public BoundingBoxInfo getBoundingBox(Point2D startPoint, MouseEvent mouseEvent) {
    var topLeftX = Math.min(mouseEvent.getX(), startPoint.getX());
    var topLeftY = Math.min(mouseEvent.getY(), startPoint.getY());
    var width = mouseEvent.getX() < startPoint.getX()
        ? startPoint.getX() - mouseEvent.getX()
        : mouseEvent.getX() - startPoint.getX();
    var height = mouseEvent.getY() < startPoint.getY()
        ? startPoint.getY() - mouseEvent.getY()
        : mouseEvent.getY() - startPoint.getY();
    return new BoundingBoxInfo(new Point2D(topLeftX, topLeftY), width, height);
  }
}
