package control.commands;

import control.ImageProcessingCommand;
import model.ImageModel;

public class Blur implements ImageProcessingCommand {
  @Override
  public void go(ImageModel model) {

    double[][] blur={
            {1.0/16,1.0/8,1.0/16},
            {1.0/8,1.0/4,1.0/8},
            {1.0/16,1.0/8,1.0/16}
    };
    model.filter(blur);
  }
}
