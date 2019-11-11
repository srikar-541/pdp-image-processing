package control.commands;

import control.ImageProcessingCommand;
import model.ImageModel;

public class FranceFlag implements ImageProcessingCommand {
  private final int width;

  public FranceFlag(int width){
    this.width=width;
  }
  @Override
  public void go(ImageModel model) {

  }
}
