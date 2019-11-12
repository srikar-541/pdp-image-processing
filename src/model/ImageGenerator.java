package model;

import java.awt.Color;

public interface ImageGenerator {

  void setRed(int x, int y, int red);

  void setGreen(int x, int y, int green);

  void setBlue(int x, int y, int blue);

  void setPixel(int x, int y, int pixel);

  void drawHorizontalBand(int x1, int y1, int x2, int y2, Color bandColor);

  void drawHorizontalLine(int x1, int y1, int length, Color lineColor);

  void drawVerticalLine(int x1, int y1, int length, Color lineColor);

  void drawVerticalBand(int x1, int y1, int x2, int y2, Color bandColor);
}
