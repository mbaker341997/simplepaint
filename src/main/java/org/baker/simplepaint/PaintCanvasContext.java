package org.baker.simplepaint;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.baker.simplepaint.tools.ToolName;

public class PaintCanvasContext {
  private final Canvas primaryCanvas;
  private final Canvas tempCanvas;
  private final Color drawColor;
  private final Color eraseColor;
  private ToolName selectedToolName;

  public PaintCanvasContext(
      Canvas primaryCanvas,
      Canvas tempCanvas,
      Color drawColor,
      Color eraseColor,
      Double initialLineWidth,
      ToolName selectedToolName
  ) {
    this.drawColor = drawColor;
    this.eraseColor = eraseColor;
    this.selectedToolName = selectedToolName;

    this.primaryCanvas = primaryCanvas;
    this.primaryCanvas.getGraphicsContext2D().setFill(this.drawColor);
    this.primaryCanvas.getGraphicsContext2D().setStroke(this.drawColor);
    this.primaryCanvas.getGraphicsContext2D().setLineWidth(initialLineWidth);

    this.tempCanvas = tempCanvas;
    this.tempCanvas.getGraphicsContext2D().setFill(this.drawColor);
    this.tempCanvas.getGraphicsContext2D().setStroke(this.drawColor);
    this.tempCanvas.getGraphicsContext2D().setLineWidth(initialLineWidth);
  }

  public Color getDrawColor() {
    return drawColor;
  }

  public Color getEraseColor() {
    return eraseColor;
  }

  public GraphicsContext getPrimaryGraphicsContext() {
    return primaryCanvas.getGraphicsContext2D();
  }
  public ToolName getSelectedToolName() {
    return selectedToolName;
  }

  public GraphicsContext getTempGraphicsContext() {
    return this.tempCanvas.getGraphicsContext2D();
  }

  public void clearTempGraphicsContext() {
    this.tempCanvas.getGraphicsContext2D().clearRect(0, 0,
        this.tempCanvas.getWidth(), this.tempCanvas.getHeight());
  }

  public void setSelectedToolName(ToolName selectedToolName) {
    this.selectedToolName = selectedToolName;
  }
}
