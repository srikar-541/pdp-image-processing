package control;

import model.ImageModel;

public interface ImageProcessingCommand {

  void go(ImageModel model);
}
