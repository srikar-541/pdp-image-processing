package control.commands;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import control.ImageProcessingCommand;
import model.ImageModel;

public class VerticalRainbow implements ImageProcessingCommand {

  private int height;
  private int bandwidth;
  private List<Color> colorList;

  public VerticalRainbow(int height, int bandwidth){
    this.height=height;
    this.bandwidth=bandwidth;
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

    int stripSize = this.bandwidth;
    for (int i = 0; i < 7; i++) {
      model.drawVerticalBand(i * stripSize, 0, (i + 1) * stripSize - 1, height - 1,
              colorList.get(i));
    }
  }
}
