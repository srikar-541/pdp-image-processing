package control.commands;

import java.awt.*;

import control.ImageProcessingCommand;
import model.ImageModel;

public class Switzerland implements ImageProcessingCommand {
  private final int width;

  public Switzerland(int width){
    this.width=width;
  }
  @Override
  public void go(ImageModel model) {
    model.drawVerticalBand(0,0,width-1, width -1, Color.RED);
    model.drawHorizontalBand((6*width)/32,(13*width)/32,(26*width)/32,(19*width)/32,Color.WHITE);
    model.drawVerticalBand((13*width)/32,(6*width)/32,(19*width)/32,(26*width)/32,Color.WHITE);
  }
}
