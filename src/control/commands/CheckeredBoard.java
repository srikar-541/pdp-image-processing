package control.commands;

import java.awt.*;

import control.ImageProcessingCommand;
import model.ImageModel;

public class CheckeredBoard implements ImageProcessingCommand {

  private final int squareSize;
  private final int length;

  public CheckeredBoard(int squareSize) {
    this.squareSize = squareSize;
    this.length = 8;
  }

  @Override
  public void go(ImageModel model) {
    for (int i = 0; i < this.length; i++) {
      int boxNumber = i % 2 == 0 ? 0 : 1;
      for (int j = 0; j < this.length; j++) {
        Color color;
        if (boxNumber % 2 == 0) {
          color = Color.WHITE;
        }
        else {
          color = Color.BLACK;
        }
        model.drawHorizontalBand(i * squareSize, j * squareSize, (i + 1) * squareSize - 1,
                (j + 1) * squareSize - 1, color);
        boxNumber++;
      }
    }
  }
}
