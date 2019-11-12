package model;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageProcessor implements ImageModel {

  private int[][] reds;
  private int[][] greens;
  private int[][] blues;
  private final int height;
  private final int width;

  public ImageProcessor(BufferedImage image) {
    this.width = image.getWidth();
    this.height = image.getHeight();

    this.reds = new int[height][width];
    this.greens = new int[height][width];
    this.blues = new int[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int pixel = image.getRGB(j, i);
        Color color = new Color(pixel);
        reds[i][j] = color.getRed();
        greens[i][j] = color.getGreen();
        blues[i][j] = color.getBlue();

      }
    }
  }

  public ImageProcessor(int width, int height) {
    this.width = width;
    this.height = height;
    this.reds = new int[height][width];
    this.greens = new int[height][width];
    this.blues = new int[height][width];
  }

  private int clamp(int value) {
    if (value < 0) {
      return 0;
    }
    return Math.min(255, value);
  }

  private int pixelVal(int red, int green, int blue) {
    return (red << 16) | (green << 8) | blue;
  }

  @Override
  public BufferedImage getImage() {
    BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        int pixel = pixelVal(this.reds[i][j], this.greens[i][j], this.blues[i][j]);

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
    this.reds = convolute(filter, this.reds);
    this.greens = convolute(filter, this.greens);
    this.blues = convolute(filter, this.blues);
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

  }

  @Override
  public void setPixel(int x, int y, int pixel) {
    Color color = new Color(pixel);
    int red = color.getRed();
    int green = color.getGreen();
    int blue = color.getBlue();
    try {
      this.reds[x][y] = clamp(red);
      this.greens[x][y] = clamp(green);
      this.blues[x][y] = clamp(blue);

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

  private int[][] convolute(double[][] kernel, int[][] channel) {
    int kernelSize = kernel.length;
    int padding = (kernelSize - 1) / 2;
    double[][] paddedMatrix = padChannel(channel, padding, this.height, this.width);
    int[][] convolutedChannel = convolutionHelper(paddedMatrix, kernel, padding);
    return removePadding(convolutedChannel, padding);
  }

  private int[][] removePadding(int[][] convolutedMatrix, int padding) {
    int[][] noPadding = new int[convolutedMatrix.length - 2 * padding][convolutedMatrix[0]
            .length - 2 * padding];

    int end_i = convolutedMatrix.length - padding;
    int end_j = convolutedMatrix[0].length - padding;
    int x = 0;
    int y = 0;

    for (int i = padding; i < end_i; i++) {
      for (int j = padding; j < end_j; j++) {
        noPadding[x][y] = convolutedMatrix[i][j];
        y++;
      }
      y = 0;
      x++;
    }
    return noPadding;
  }

  private int[][] convolutionHelper(double[][] paddedMatrix, double[][] kernel, int padding) {
    int[][] convolutedChannel = new int[paddedMatrix.length][paddedMatrix[0].length];
    int kernelSize = kernel.length;
    int end_i = paddedMatrix.length - padding;
    int end_j = paddedMatrix[end_i - 1].length - padding;

    for (int i = padding; i < end_i; i++) {
      for (int j = padding; j < end_j; j++) {

        int select_index_start_i = i - padding;
        int select_index_start_j = j - padding;

        int select_index_end_i = i + padding + 1;
        int select_index_end_j = j + padding + 1;

        double[][] selectedMatrix = new double[kernelSize][kernelSize];
        int index_i = 0;
        int index_j = 0;

        for (int x = select_index_start_i; x < select_index_end_i; x++) {
          for (int y = select_index_start_j; y < select_index_end_j; y++) {
            selectedMatrix[index_i][index_j] = paddedMatrix[x][y];
            index_j++;
          }
          index_j = 0;
          index_i++;
        }

        int val = (int) Math.round(convolutedValue(selectedMatrix, kernel));
        convolutedChannel[i][j] = clamp(val);
      }
    }
    return convolutedChannel;
  }


  private double convolutedValue(double[][] selectedMatrix, double[][] kernel) {
    double sum = 0;
    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel.length; j++) {
        sum = sum + selectedMatrix[i][j] * kernel[i][j];
      }
    }
    return sum;
  }

  private double[][] padChannel(int[][] channel, int padding, int height, int width) {
    int channelWithPaddingHeight = height + 2 * padding;
    int channelWithPaddingWidth = width + 2 * padding;
    double[][] channelWithPadding = new double[channelWithPaddingHeight][channelWithPaddingWidth];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        channelWithPadding[i + padding][j + padding] = channel[i][j];
      }
    }
    return channelWithPadding;
  }

}
