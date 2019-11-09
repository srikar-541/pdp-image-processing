import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.ImageProcessor;

public class BT {

  public static BufferedImage readImage(String filepath){
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


  public static void writeImage(BufferedImage img,String filepath){
    try{
      File output=new File(filepath);
      ImageIO.write(img,"png",output);
    }catch (IOException e){
      System.out.println(e.getMessage());
    }
    System.out.println("Image written to :"+filepath);
  }

  public static void main(String [] args){
    BufferedImage img=readImage("input/ACBig.jpg");
    ImageProcessor f=new ImageProcessor(img);

    //    greyscale

//    double [][] transform={
//            {0.2126,0.7152,0.0722},
//            {0.2126,0.7152,0.0722},
//            {0.2126,0.7152,0.0722}
//    };

    // sepia
//
    double [][] transform={
            {0.393,0.769,0.189},
            {0.349,0.686,0.168},
            {0.272,0.534,0.131}
    };

//    f.transform(transform);

//    double[][] blur={
//            {1.0/16,1.0/8,1.0/16},
//            {1.0/8,1.0/4,1.0/8},
//            {1.0/16,1.0/8,1.0/16}
//    };

//    double[][] sharp={
//            {-0.125,-0.125,-0.125,-0.125,-0.125},
//            {-0.125,0.25,0.25,0.25,-0.125},
//            {-0.125,0.25,1.0,0.25,-0.125},
//            {-0.125,0.25,0.25,0.25,-0.125},
//            {-0.125,-0.125,-0.125,-0.125,-0.125}
//    };


//    f.filter(blur);
//    f.filter(blur);
//    f.filter(blur);
//    f.filter(sharp);
////    f.filter(blur);
    f.transform(transform);
//    f.filter(sharp);
    BufferedImage result=f.getImage();

    writeImage(result,"output/ACsepiaaa.png");
  }
}
