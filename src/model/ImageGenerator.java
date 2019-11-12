package model;

import java.awt.Color;

/**
 * ImageGenerator is an interface which contains all the functionality concerning
 * generating of images like setting green color, blue color, red color at a
 * particular pixel, drawing basic horizontal, vertical lines and bands.
 * Future enhancements to Image Generations can be made by extending this
 * interface and having own implementation.
 */
public interface ImageGenerator {

  /**
   * This function sets updates the red color value of a pixel at an x and y cartesian
   * co ordinate passed.
   * @param     x The x co ordinate of the image plane.
   * @param     y The y co ordinate of the image plane.
   * @param     red The value of the red that has to be updated at this pixel.
   *                It can go from 0-255.
   */
  void setRed(int x, int y, int red);

  /**
   * This function sets updates the green color value of a pixel at an x and y cartesian
   * co ordinate passed.
   * @param     x The x co ordinate of the image plane.
   * @param     y The y co ordinate of the image plane.
   * @param     green The value of the green that has to be updated at this pixel
   *                  which can go from 0-255.
   */
  void setGreen(int x, int y, int green);

  /**
   * This function sets updates the blue color value of a pixel at an x and y cartesian
   * co ordinate passed.
   * @param     x The x co ordinate of the image plane.
   * @param     y The y co ordinate of the image plane.
   * @param     blue The value of the blue that has to be updated at this pixel.
   *               It can go from 0-255.
   */
  void setBlue(int x, int y, int blue);

  /**
   * This function sets updates the pixel which is a 32 bit integer value containing
   * r,b,g,a color values in a pixel at an x and y cartesian co ordinate passed.
   * @param     x The x co ordinate of the image plane.
   * @param     y The y co ordinate of the image plane.
   * @param     pixel The value of the pixel that has to be updated at this pixel.
   */
  void setPixel(int x, int y, int pixel);

  /**
   * This function is used to draw horizontal box/band on a given image.
   * It takes in two co ordinates ie starting x,y co ordinate and ending
   * x,y co ordinates of the band/box.
   * @param     x1 The x co ordinate of the point from which the box has to be drawn.
   * @param     y1 The y co ordinate of the point from which the box has to be drawn.
   * @param     x2 The x co ordinate of the point from which the box has to end.
   * @param     y2 The y co ordinate of the point from which the box has to end
   * @param     bandColor The Color which has to be filled in this box. It accepts a color object.
   *                      which contains r,b,g values.
   */
  void drawHorizontalBand(int x1, int y1, int x2, int y2, Color bandColor);

  /**
   * This function is used to draw horizontal line ie parallel to the x axis on a given image.
   * It takes in a co ordinates ie starting x,y co ordinate from which the line has to
   * be drawn. It projects a horizontal line of a specified length from this 2D point.
   * @param     x1 The x co ordinate of the point from which the line has to be drawn.
   * @param     y1 The y co ordinate of the point from which the line has to be drawn.
   * @param     length The length of the line that has to be drawn from the given point.
   * @param     lineColor The Color which has to be filled in this line. It accepts a color object.
   *                      which contains r,b,g values.
   */
  void drawHorizontalLine(int x1, int y1, int length, Color lineColor);

  /**
   * This function is used to draw vertical line ie parallel to the y axis on a given image.
   * It takes in a co ordinates ie starting x,y co ordinate from which the line has to
   * be drawn. It projects a vertical line of a specified length from this 2D point.
   * @param     x1 The x co ordinate of the point from which the line has to be drawn.
   * @param     y1 The y co ordinate of the point from which the line has to be drawn.
   * @param     length The length of the line that has to be drawn from the given point.
   * @param     lineColor The Color which has to be filled in this line. It accepts a color object.
   *                      which contains r,b,g values.
   */
  void drawVerticalLine(int x1, int y1, int length, Color lineColor);

  /**
   * This function is used to draw vertical box/band on a given image.
   * It takes in two co ordinates ie starting x,y co ordinate and ending
   * x,y co ordinates of the band/box.
   * @param     x1 The x co ordinate of the point from which the box has to be drawn.
   * @param     y1 The y co ordinate of the point from which the box has to be drawn.
   * @param     x2 The x co ordinate of the point from which the box has to end.
   * @param     y2 The y co ordinate of the point from which the box has to end
   * @param     bandColor The Color which has to be filled in this box. It accepts a color object.
   *                      which contains r,b,g values.
   */
  void drawVerticalBand(int x1, int y1, int x2, int y2, Color bandColor);
}
