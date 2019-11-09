package control.commands;

import control.ImageProcessingCommand;
import model.ImageModel;

public class Sharpen implements ImageProcessingCommand {
  @Override
  public void go(ImageModel model) {
    double[][] sharp={
            {-0.125,-0.125,-0.125,-0.125,-0.125},
            {-0.125,0.25,0.25,0.25,-0.125},
            {-0.125,0.25,1.0,0.25,-0.125},
            {-0.125,0.25,0.25,0.25,-0.125},
            {-0.125,-0.125,-0.125,-0.125,-0.125}
    };

    model.filter(sharp);
  }
}
