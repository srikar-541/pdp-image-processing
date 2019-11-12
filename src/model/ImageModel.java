package model;

import java.awt.image.BufferedImage;

/**
 * This interface represents an image model. It extends the functionality of image
 * manipulation and image generation. It also additional functionality of getting an
 * image after applying the scaling and coloring filters on a given image.
 */
public interface ImageModel extends ImageManipulation, ImageGenerator {

  /**
   * This method gets the image in the form of bufferedimage which can be stored in
   * any format in the model implementation.
   * @return    BufferedImage representation of the image.
   */
  BufferedImage getImage();
}
