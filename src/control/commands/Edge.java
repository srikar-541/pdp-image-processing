package control.commands;

import control.ImageProcessingCommand;
import model.ImageModel;

public class Edge implements ImageProcessingCommand {
  @Override
  public void go(ImageModel model) {
double [][]edge=
      {
        {-1, -1, -1},
        {-1,  8, -1},
        {-1, -1, -1}
    };
model.filter(edge);
  }
}
