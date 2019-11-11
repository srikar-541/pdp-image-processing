package model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageProcessor implements ImageModel {

  private int[][] reds;
  private int[][] greens;
  private int[][] blues;
  private final int height;
  private final int width;
//  private int[][] alphas;

  public ImageProcessor(BufferedImage image) {
    this.width = image.getWidth();
    this.height = image.getHeight();

    this.reds = new int[height][width];
    this.greens = new int[height][width];
    this.blues = new int[height][width];
//    this.alphas=new int [height][height];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int pixel = image.getRGB(j, i);
        Color color = new Color(pixel);
        reds[i][j] = color.getRed();
        greens[i][j] = color.getGreen();
        blues[i][j] = color.getBlue();

//        alphas[i][j]=color.getAlpha();
      }
    }
//    System.out.println(this.height);
//    System.out.println(this.width);
//    System.out.println(reds.length);
//    System.out.println(reds[0].length);
  }

  public ImageProcessor(int width, int height) {
    this.width = width;
    this.height = height;

    this.reds = new int[height][width];
    this.greens = new int[height][width];
    this.blues = new int[height][width];
//    this.alphas=new int [height][height];
  }

  private int clamp(int value) {
    if (value < 0) {
      return 0;
    }
    return Math.min(255, value);
  }

  private int pixelVal(int alpha, int red, int green, int blue) {
    return (alpha << 24) | (red << 16) | (green << 8) | blue;
  }

  private int pixelVal2(int red, int green, int blue) {
    return (red << 16) | (green << 8) | blue;
  }

  @Override
  public BufferedImage getImage() {
    BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
//        int pixel= pixelVal(this.alphas[i][j],this.reds[i][j],this.greens[i][j],this.blues[i][j]);
//        int pixel= pixelVal(255,this.reds[i][j],this.greens[i][j],this.blues[i][j]);
        int pixel = pixelVal2(this.reds[i][j], this.greens[i][j], this.blues[i][j]);

        result.setRGB(j, i, pixel);
      }
    }
    return result;
  }


  @Override
  public void transform(double[][] transformer) {
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        int r = this.reds[i][j];
        int g = this.greens[i][j];
        int b = this.blues[i][j];

        this.reds[i][j] = clamp((int) Math.floor(r * transformer[0][0] +
                g * transformer[0][1] + b * transformer[0][2]));

        this.greens[i][j] = clamp((int) Math.floor(r * transformer[1][0] +
                g * transformer[1][1] + b * transformer[1][2]));

        this.blues[i][j] = clamp((int) Math.floor(r * transformer[2][0] +
                g * transformer[2][1] + b * transformer[2][2]));
      }
    }
  }

  @Override
  public void filter(double[][] filter) {
    int[][] tempR = new int[this.height][this.width];
    int[][] tempG = new int[this.height][this.width];
    int[][] tempB = new int[this.height][this.width];

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        tempR[i][j] = clamp((int) Math.round(transformHelper(this.reds, filter, i, j)));
        tempG[i][j] = clamp((int) Math.round(transformHelper(this.greens, filter, i, j)));
        tempB[i][j] = clamp((int) Math.round(transformHelper(this.blues, filter, i, j)));
      }
    }
    this.reds = tempR;
    this.greens = tempG;
    this.blues = tempB;
  }

  private double transformHelper(int[][] original, double[][] kernel, int rowIndex, int colIndex) {

    int mid = kernel.length / 2;
    double sum = 0;
    int kIStart = 0;
    int kJStart = 0;
    int kIEnd = kernel.length - 1;
    int kJEnd = kernel.length - 1;
    int i = rowIndex;
    int j = colIndex;

    while (i - mid < 0) {
      kIStart++;
      i++;
    }

    while (j - mid < 0) {
      kJStart++;
      j++;
    }

    i = rowIndex;
    j = colIndex;

    while (i + mid >= original.length) {
      kIEnd--;
      i--;
    }

    while (j + mid >= original[0].length) {
      kJEnd--;
      j--;
    }

    int oI = Math.max(0, rowIndex - mid);
    int oJ = Math.max(0, colIndex - mid);

    for (i = kIStart; i <= kIEnd; i++) {
      for (j = kJStart; j <= kJEnd; j++) {
        sum = sum + kernel[i][j] * original[oI][oJ];
        oJ++;
      }
      oI++;
      oJ = Math.max(0, colIndex - mid);
    }

    return sum;
  }

  @Override
  public void setRed(int x, int y, int red) {
    try {
      this.reds[y][x] = clamp(red);
    } catch (IndexOutOfBoundsException iob) {
      throw iob;
    }
  }

  @Override
  public void setGreen(int x, int y, int green) {
    try {
      this.greens[y][x] = clamp(green);
    } catch (IndexOutOfBoundsException iob) {
      throw iob;
    }
  }

  @Override
  public void setBlue(int x, int y, int blue) {
    try {
      this.blues[y][x] = clamp(blue);
    } catch (IndexOutOfBoundsException iob) {
      throw iob;
    }
  }

  @Override
  public void setAlpha(int x, int y, int alpha) {
//    try {
//    this.alphas[x][y]=clamp(alpha);
//  } catch(IndexOutOfBoundsException iob){
//        throw iob;
//        }
  }

  @Override
  public void setPixel(int x, int y, int pixel) {
    Color color = new Color(pixel);
    int red = color.getRed();
    int green = color.getGreen();
    int blue = color.getBlue();
    int alpha = color.getAlpha();
    try {
      this.reds[x][y] = clamp(red);
      this.greens[x][y] = clamp(green);
      this.blues[x][y] = clamp(blue);
//      this.alphas[x][y]=clamp(alpha);
    } catch (IndexOutOfBoundsException iob) {
      throw iob;
    }
  }

  public void setPixel(int x, int y, Color pixelColor) {
    int red = pixelColor.getRed();
    int green = pixelColor.getGreen();
    int blue = pixelColor.getBlue();
    int alpha = pixelColor.getAlpha();
    try {
      this.reds[x][y] = clamp(red);
      this.greens[x][y] = clamp(green);
      this.blues[x][y] = clamp(blue);
//      this.alphas[x][y]=clamp(alpha);
    } catch (IndexOutOfBoundsException iob) {
      throw iob;
    }
  }

  @Override
  public void drawVerticalLine(int x1, int y1, int length, Color lineColor) {
    int red = lineColor.getRed();
    int green = lineColor.getGreen();
    int blue = lineColor.getBlue();

    if (y1 + length > this.height) {
      throw new IllegalArgumentException("Illegal argument of length while line draw");
    }

    for (int j = y1; j < y1 + length; j++) {
      this.setBlue(x1, j, blue);
      this.setRed(x1, j, red);
      this.setGreen(x1, j, green);
    }
  }

  @Override
  public void drawHorizontalLine(int x1, int y1, int length, Color lineColor) {
    int red = lineColor.getRed();
    int green = lineColor.getGreen();
    int blue = lineColor.getBlue();

    if (x1 + length > this.width) {
      throw new IllegalArgumentException("Illegal argument of length while line draw");
    }

    for (int i = x1; i < x1 + length; i++) {
      this.setBlue(i, y1, blue);
      this.setRed(i, y1, red);
      this.setGreen(i, y1, green);
    }
  }

  @Override
  public void drawHorizontalBand(int x1, int y1, int x2, int y2, Color bandColor) {
    for (int i = 0; i <= Math.abs(y1 - y2); i++) {
      drawHorizontalLine(x1, i + y1, Math.abs(x2 - x1) + 1, bandColor);
    }
  }

  @Override
  public void drawVerticalBand(int x1, int y1, int x2, int y2, Color bandColor) {
    for (int i = 0; i <= Math.abs(x1 - x2); i++) {
      drawVerticalLine(i + x1, y1, Math.abs(y2 - y1) + 1, bandColor);
    }
  }
}
