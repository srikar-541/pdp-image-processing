package control.commands;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import control.ImageProcessingCommand;
import model.ImageModel;

/**
 * This class is an macro which generates a Horizontal rainbow by taking the
 * length of the flag to be generated and the thickness of each rainbow color band.
 */
public class HorizontalRainbow implements ImageProcessingCommand {
  private int length;
  private int thickness;
  List<Color> colorList;

  /**
   * This constructor creates an object of HorizontalRainbow by taking parameters
   * as the length of the image and thickness of each color band.
   * @param     length The length/width of the flag to be generated.
   * @param     thickness The thickness of each color band.
   */
  public HorizontalRainbow(int length, int thickness) {
    this.length = length;
    this.thickness = thickness;

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
  public void go(ImageModel model) {

    int stripSize = this.thickness;
    for (int i = 0; i < 7; i++) {
      model.drawHorizontalBand(0, i * stripSize, length - 1, (i + 1) * stripSize - 1,
              colorList.get(i));
    }
  }
}