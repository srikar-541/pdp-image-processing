package control.commands;

import control.ImageProcessingCommand;
import model.ImageModel;

public class GreyScale implements ImageProcessingCommand {
  @Override
  public void go(ImageModel model) {
        double [][] transform={
            {0.2126,0.7152,0.0722},
            {0.2126,0.7152,0.0722},
            {0.2126,0.7152,0.0722}
    };
        model.transform(transform);
  }
}
