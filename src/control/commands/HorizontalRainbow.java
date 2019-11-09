package control.commands;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import control.ImageProcessingCommand;
import model.ImageModel;

public class HorizontalRainbow implements ImageProcessingCommand {
  private int length;
  private int thickness;
  List<Color> colorList;

  public HorizontalRainbow(int length, int thickness){
    this.length=length;
    this.thickness=thickness;
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