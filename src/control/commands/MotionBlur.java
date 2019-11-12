package control.commands;

import control.ImageProcessingCommand;
import model.ImageModel;

public class MotionBlur implements ImageProcessingCommand {
  @Override
  public void go(ImageModel model) {

    double[][] blur={
            {1.0/9, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1.0/9, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1.0/9, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1.0/9, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1.0/9, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1.0/9, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1.0/9, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1.0/9, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1.0/9},
    };
    model.filter(blur);
  }
}
