package control.commands;

import control.ImageProcessingCommand;
import model.ImageModel;

public class GaussianBlur implements ImageProcessingCommand {
  @Override
  public void go(ImageModel model) {

    double[][] blur=
            {
                    {0.077847, 0.123317, 0.077847},
                    {0.123317, 0.195346, 0.123317},
                    {0.077847, 0.123317, 0.077847},
            };
    model.filter(blur);
  }
}
