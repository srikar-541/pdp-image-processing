package control.commands;

import control.ImageProcessingCommand;
import model.ImageModel;

/**
 * This class is an macro which does greyscaling of images. It contains the kernel
 * specific to image greyscaling. The model applies this filter and the model stores the updated
 * image pixel.
 */
public class GreyScale implements ImageProcessingCommand {

  @Override
  public void go(ImageModel model) {
    double[][] transform = {
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}
    };
    model.transform(transform);
  }
}
