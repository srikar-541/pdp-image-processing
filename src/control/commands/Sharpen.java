package control.commands;

import control.ImageProcessingCommand;
import model.ImageModel;

/**
 * This class is an macro which does sharpening of images. It contains the kernel
 * specific to image sharpening. The model applies this filter and the model stores the updated
 * image pixels.
 */
public class Sharpen implements ImageProcessingCommand {
  @Override
  public void process(ImageModel model) {
    double[][] sharp = {
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1.0, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}
    };

    model.filter(sharp);
  }
}
