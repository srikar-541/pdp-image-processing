package control.commands;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import control.ImageProcessingCommand;
import model.ImageModel;

/**
 * This class is an macro which generates a Vertical rainbow by taking the
 * height of the flag to be generated and the width of each rainbow color band.
 */
public class VerticalRainbow implements ImageProcessingCommand {

  private int height;
  private int bandwidth;
  private List<Color> colorList;

  /**
   * This constructor creates an object of VerticalRainbow by taking parameters
   * as the height of the image and the width of each color band.
   * @param     height The height of the flag to be generated.
   * @param     bandwidth The width of each color band.
   */
  public VerticalRainbow(int height, int bandwidth) {
    this.height = height;
    this.bandwidth = bandwidth;

    colorList = new ArrayList<>(7);
    colorList.add(new Color(148, 0, 211));
    colorList.add(new Color(75, 0, 130));
    colorList.add(new Color(0, 0, 255));
    colorList.add(new Color(0, 255, 0));
    colorList.add(new Color(255, 255, 0));
    colorList.add(new Color(255, 127, 0));
    colorList.add(new Color(255, 0, 0));
  }

  @Override
  public void process(ImageModel model) {

    int stripSize = this.bandwidth;
    for (int i = 0; i < 7; i++) {
      model.drawVerticalBand(i * stripSize, 0, (i + 1) * stripSize - 1, height - 1,
              colorList.get(i));
    }
  }
}
