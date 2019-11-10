package control.commands;

import java.awt.*;

import control.ImageProcessingCommand;
import model.ImageModel;

public class CheckeredBoard implements ImageProcessingCommand {

  private final int squareSize;
  private final int rowsCount;
  private final int colsCount;

  public CheckeredBoard(int rowsCount, int colsCount, int squareSize) {
    this.rowsCount = rowsCount;
    this.colsCount = colsCount;
    this.squareSize = squareSize;
  }

  @Override
  public void go(ImageModel model) {
    for (int i = 0; i < this.rowsCount; i++) {
      int boxNumber = i % 2 == 0 ? 0 : 1;
      for (int j = 0; j < this.colsCount; j++) {
        Color color;
        if (boxNumber % 2 == 0) {
          color = Color.WHITE;
        }
        else {
          color = Color.BLACK;
        }
        model.drawHorizontalBand(j * squareSize, i * squareSize, (j + 1) * squareSize - 1,
                (i + 1) * squareSize - 1, color);
        boxNumber++;
      }
    }
  }
}
