package control.commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import control.ImageProcessingCommand;
import model.ImageModel;

/**
 * This class writes the pixel values generated by the model after any operation
 * to a file. This functionality resides in the model.
 */
public class Save implements ImageProcessingCommand {
  private final String filepath;

  /**
   * This constructor is used to create an object of Save which will can be used
   * to write images to files by calling methods in model.
   * @param     filepath The filepath to which the file has to be written.
   */
  public Save(String filepath) {
    this.filepath = filepath;
  }

  @Override
  public void process(ImageModel model) {
    BufferedImage image = model.getImage();
    writeImage(image, this.filepath);
  }

  private static void writeImage(BufferedImage img, String filepath) {
    try {
      File output = new File(filepath);
      ImageIO.write(img, "png", output);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    System.out.println("Image written to :" + filepath);
  }
}
