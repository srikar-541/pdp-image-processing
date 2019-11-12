package control.commands;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import control.ImageProcessingCommand;
import model.ImageModel;

/**
 * This class is an macro which generates a France flag of a given width and height.
 */
public class FranceFlag implements ImageProcessingCommand {

  private final int width;
  private List<Color> colorList;

  /**
   * This creates an object of FranceFlag by taking in width, height as inputs.
   * @param     width Width, height are the width and height of the flag to be generated.
   */
  public FranceFlag(int width) {
    this.width = width;
    colorList = new ArrayList<>(3);
    colorList.add(new Color(0, 85, 164));
    colorList.add(new Color(255, 255, 255));
    colorList.add(new Color(239, 65, 53));
  }

  @Override
  public void go(ImageModel model) {
    int stripSize = this.width / 3;
    int height = (2 * width) / 3;
    for (int i = 0; i < 3; i++) {
      model.drawVerticalBand(i * stripSize, 0, (i + 1) * stripSize - 1, height - 1,
              colorList.get(i));
    }
  }
}
