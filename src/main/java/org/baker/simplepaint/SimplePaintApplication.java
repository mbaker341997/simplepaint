package org.baker.simplepaint;

import java.util.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.baker.simplepaint.tools.LineTool;
import org.baker.simplepaint.tools.PencilTool;
import org.baker.simplepaint.tools.RectangleTool;
import org.baker.simplepaint.tools.ToolName;

public class SimplePaintApplication extends Application {
    @Override
    public void start(Stage stage) {
      stage.setTitle("Paint");

      var root = new Pane();
      root.setBackground(Background.fill(Color.GRAY));

      var stackPane = new StackPane();
      stackPane.setStyle("-fx-background-color: white");

      var width = 400;
      var height = 300;
      var primaryCanvas = new Canvas(width, height);
      var tempCanvas = new Canvas(width, height);
      primaryCanvas.setFocusTraversable(true); // enable key press events
      tempCanvas.setFocusTraversable(true);
      var paintCanvasContext = new PaintCanvasContext(
          primaryCanvas,
          tempCanvas,
          Color.BLACK,
          Color.WHITE,
          1.0,
          ToolName.RECTANGLE);

      // add tools
      var toolsMap = Map.of(
          ToolName.PENCIL, new PencilTool(paintCanvasContext, false),
          ToolName.ERASER, new PencilTool(paintCanvasContext, true),
          ToolName.LINE, new LineTool(paintCanvasContext),
          ToolName.RECTANGLE, new RectangleTool(paintCanvasContext)
      );

      // add listeners
      stackPane.addEventHandler(MouseEvent.MOUSE_DRAGGED,
              mouseEvent -> toolsMap.get(paintCanvasContext.getSelectedToolName())
                  .handleMouseDragged(mouseEvent));
      stackPane.addEventHandler(MouseEvent.MOUSE_RELEASED,
              mouseEvent -> toolsMap.get(paintCanvasContext.getSelectedToolName())
                  .handleMouseReleased(mouseEvent));
      stackPane.addEventHandler(MouseEvent.MOUSE_CLICKED,
              mouseEvent -> toolsMap.get(paintCanvasContext.getSelectedToolName())
                  .handleMouseClicked(mouseEvent));

      stackPane.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
        if (keyEvent.isShiftDown() && paintCanvasContext.getSelectedToolName() == ToolName.LINE) {
          System.out.println("now eraser");
          paintCanvasContext.setSelectedToolName(ToolName.ERASER);
        } else if (keyEvent.isShiftDown()) {
          System.out.println("now pencil");
          paintCanvasContext.setSelectedToolName(ToolName.LINE);
        }
      });

      stackPane.getChildren().add(primaryCanvas);
      stackPane.getChildren().add(tempCanvas);
      root.getChildren().add(stackPane);
      stage.setScene(new Scene(root));
      stage.show();
    }

    // https://docs.oracle.com/javafx/2/canvas/jfxpub-canvas.htm
    private void drawShapes(GraphicsContext gc) {
      // TODO: rectangle draw onMouseDragEvent
      // TODO: color selection SIMPLE
      // TODO: size selection simple
      // TODO: text box
      // TODO: select box
      // TODO: delete
      // TODO: save
      // TODO: cut
      // TODO: copy
      // TODO: paste
      // TODO: undo/redo
      // TODO: invert colors
      // TODO: resize canvas
      // TODO: fill box
      // TODO: remove color - direct
      // TODO: remove color - range
      // TODO: layers
      // TODO: zoom
      // TODO: color selection advanced
      // todo: size selection advanced
      // TODO: opacity control
      // TODO: little blop of paint follows mouse around
    }

    public static void main(String[] args) {
        launch();
    }
}