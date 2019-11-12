package control.commands;

import control.ImageProcessingCommand;
import model.ImageModel;

/**
 * This class is an macro which does sepia color transformation on images. It contains the
 * sepia color transformation matrix. The model applies this filter and the model stores the
 * updated image pixels.
 */
public class Sepia implements ImageProcessingCommand {

  @Override
  public void go(ImageModel model) {
    double[][] transform = {
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}
    };
    model.transform(transform);
  }
}
