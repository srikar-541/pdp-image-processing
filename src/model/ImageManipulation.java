package model;

import java.awt.image.BufferedImage;

public interface ImageManipulation {

  void transform(double[][] transformer);

  void filter(double[][] filter);
}

