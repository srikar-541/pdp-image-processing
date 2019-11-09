package control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import control.commands.Blur;
import control.commands.CheckeredBoard;
import control.commands.GreyScale;
import control.commands.HorizontalRainbow;
import control.commands.Save;
import control.commands.Sepia;
import control.commands.Sharpen;
import control.commands.VerticalRainbow;
import model.ImageModel;
import model.ImageProcessor;

public class CommandController {

  private static BufferedImage readImage(String filepath){
    BufferedImage image=null;
    File f=null;
    try{
      f=new File(filepath);
      image= ImageIO.read(f);
    }catch (IOException e){
      System.out.println(e.getMessage());
    }
    System.out.println("Read image from "+filepath+" of : ");
    System.out.println("width: "+image.getWidth());
    System.out.println("height: "+image.getHeight());
    return image;
  }

//  main start
  public static void main(String[]  args){

    Scanner s = new Scanner(System.in);
    ImageModel model=null;
    ImageProcessingCommand cmd=null;
    while(s.hasNext()){
      String in = s.nextLine();
      try{
        switch(in){
          case "q":
          case "quit":
            return;
          case "load":
            BufferedImage img=readImage("input/manhattan-small.png");
//            BufferedImage img=readImage("input/mario.png");
            model=new ImageProcessor(img);
            break;
          case "sepia":
            cmd= new Sepia();
            break;
          case "greyscale":
            cmd=new GreyScale();
            break;
          case "blur":
            cmd=new Blur();
            break;
          case "sharpen":
            cmd=new Sharpen();
            break;
          case "save":
            cmd=new Save("output/model.png");
            break;
          case "rainbow h":
            int thickness = s.nextInt();
            int length = s.nextInt();
            model=new ImageProcessor(length, thickness *7);
            cmd=new HorizontalRainbow(length, thickness);
            break;
          case "rainbow v":
            int height = s.nextInt();
            int bandwidth = s.nextInt();
            model=new ImageProcessor(bandwidth * 7, height);
            cmd=new VerticalRainbow(height, bandwidth);
            break;
          case "checkeredBoard":
            int size = s.nextInt();
            model = new ImageProcessor(8 * size, 8 * size);
            cmd = new CheckeredBoard(size);
            break;
        }
        if (cmd != null) {
          cmd.go(model);
          System.out.println(".....Done.");
          cmd = null;
        }
      }catch(InputMismatchException ime){
        System.out.println("Bad length to " + in);
      }
    }
  }
}
