package control.commands;

import control.ImageProcessingCommand;
import model.ImageModel;

/**
 * This class is an macro which does blurring of images. It contains the kernel
 * specific to image blurring. The model applies this filter and the model stores the updated
 * image pixels.
 */
public class Blur implements ImageProcessingCommand {

  @Override
  public void go(ImageModel model) {

    double[][] blur = {
            {1.0 / 16, 1.0 / 8, 1.0 / 16},
            {1.0 / 8, 1.0 / 4, 1.0 / 8},
            {1.0 / 16, 1.0 / 8, 1.0 / 16}
    };

    model.filter(blur);
  }
}
