package org.baker.simplepaint.tools;

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
}
