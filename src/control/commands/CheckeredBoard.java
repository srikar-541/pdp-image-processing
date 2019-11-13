package control.commands;

import java.awt.Color;

import control.ImageProcessingCommand;
import model.ImageModel;

/**
 * This class is an macro which generates a checkerboard of a given unit square size.
 * The checkerboard is an 8X8 box containing squares.
 */
public class CheckeredBoard implements ImageProcessingCommand {

  private final int squareSize;

  /**
   * This creates an object of the CheckeredBoard image generator by taking in param
   * as the unit square size.
   * @param     squareSize The pixel size of the unit square on a square board.
   */
  public CheckeredBoard(int squareSize) {
    this.squareSize = squareSize;
  }

  @Override
  public void process(ImageModel model) {
    for (int i = 0; i < 8; i++) {
      int boxNumber = i % 2 == 0 ? 0 : 1;
      for (int j = 0; j < 8; j++) {
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
