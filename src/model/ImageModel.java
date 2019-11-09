package model;

import java.awt.image.BufferedImage;

public interface ImageModel extends ImageManipulation, ImageGenerator {

  BufferedImage getImage();

}
