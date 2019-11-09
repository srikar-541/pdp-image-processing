package control.commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import control.ImageProcessingCommand;
import model.ImageModel;

public class Save implements ImageProcessingCommand {
  private final String filepath;

  public Save(String filepath){
    this.filepath=filepath;
  }

  @Override
  public void go(ImageModel model) {
  BufferedImage image=model.getImage();
  writeImage(image,this.filepath);
  }
  private static void writeImage(BufferedImage img, String filepath){
    try{
      File output=new File(filepath);
      ImageIO.write(img,"png",output);
    }catch (IOException e){
      System.out.println(e.getMessage());
    }
    System.out.println("Image written to :"+filepath);
  }
}
