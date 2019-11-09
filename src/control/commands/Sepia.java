package control.commands;

import control.ImageProcessingCommand;
import model.ImageModel;

public class Sepia implements ImageProcessingCommand {

  @Override
  public void go(ImageModel model) {
    double [][] transform={
            {0.393,0.769,0.189},
            {0.349,0.686,0.168},
            {0.272,0.534,0.131}
    };
    model.transform(transform);
  }
}
