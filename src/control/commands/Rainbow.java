package control.commands;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import control.ImageProcessingCommand;
import model.ImageModel;

public class Rainbow implements ImageProcessingCommand {
  private int height;
  private int width;
  List<Color> colorList;

  public Rainbow(int height, int width ){
    this.height=width;
    this.width=height;
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

    int i = 0;
    int stripSize = this.height ;
    for (; i < 6; i++) {
      model.drawHorizontalBand(0, i*stripSize, width, (i + 1) * stripSize + 1,
              colorList.get(i));
    }
    model.drawHorizontalBand(0, i * stripSize, width, (i + 1) * stripSize , colorList.get(6));
  }
}