package control.commands;

import java.awt.*;

import control.ImageProcessingCommand;
import model.ImageModel;

/**
 * This class is an macro which generates a Greece flag of a given width and height.
 */
public class GreeceFlag implements ImageProcessingCommand {

  private final int height;
  private final int width;

  /**
   * This creates an object of GreeceFlag by taking in width, height as inputs.
   * @param     width Width, height are the width and height of the flag to be generated.
   */
  public GreeceFlag(int width, int height) {
    this.height = height;
    this.width = width;
  }

  @Override
  public void go(ImageModel model) {
    int thickness = height / 9;
    int bandWidth = width / 15;
    Color blueColor = new Color(13, 94, 175);
    Color whiteColor = Color.WHITE;
    for (int i = 0; i * thickness <= this.height - thickness; i++) {
      Color color = i % 2 == 0 ? blueColor : whiteColor;
      model.drawHorizontalBand(0, i * thickness, width - 1,
              (i + 1) * thickness - 1, color);
    }

    model.drawHorizontalBand(0, 0, 5 * bandWidth - 1, 2 * thickness - 1, blueColor);
    model.drawHorizontalBand(0, 0, 5 * bandWidth - 1, 5 * thickness - 1, blueColor);
    model.drawHorizontalBand(0, 2 * thickness,
            5 * bandWidth - 1, 3 * thickness - 1, whiteColor);
    model.drawVerticalBand(2 * bandWidth, 0,
            3 * bandWidth - 1, 5 * thickness - 1, whiteColor);
  }
}
