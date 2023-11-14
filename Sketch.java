import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  PImage imgDesertBackground;
  PImage imgMartianSaucer;
  PImage imgMartianDrone;
  PImage imgMartianDrone2;

  // positions
  float fltMartianSaucerX = 0;
  float fltMartianSaucerY = 100;
  int intMartianSaucerSpeedX = 1;
  int intDegrees = 0;

  float fltMartianDroneX = 200;
  float fltMartianDroneY = 600;
  int intMartianDroneSpeedX = 1;
  boolean boolDescending = false;
  float fltPrevPosX = 0;
  float fltPrevPosY = 0;
  float fltMartianDroneRotation = 0;

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
    // put your size call here
    size(800, 600);
  }

  /**
   * Called once at the beginning of execution. Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    imgDesertBackground = loadImage("desertBackground.jpg");
    imgMartianSaucer = loadImage("Martian_Saucer.gif");
    imgMartianDrone = loadImage("Martian_Drone.png");
    imgMartianDrone2 = loadImage("Martian_Drone2.png");
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {

    image(imgDesertBackground, 0, 0);
    calculateSaucerPosition();
    calculateDronePosition();
    image(imgMartianSaucer, fltMartianSaucerX, fltMartianSaucerY);
    pushMatrix();

    translate(fltMartianDroneX, fltMartianDroneY);
    rotate(fltMartianDroneRotation);
    translate(-29, -16);
    if (intMartianDroneSpeedX < 0)
      image(imgMartianDrone, 0, 0);
    else {
      image(imgMartianDrone2, 0, 0);
    }
    popMatrix();

    intDegrees += 1;
  }

  // calculates the position of the UFO
  public void calculateSaucerPosition() {
    if (fltMartianSaucerX > width - 210 || fltMartianSaucerX < 0)
      intMartianSaucerSpeedX *= -1;

    fltMartianSaucerX += intMartianSaucerSpeedX;
    fltMartianSaucerY += cos(radians(intDegrees));

  }

  // calculaes the position of the smaller drone
  public void calculateDronePosition() {
    if (fltMartianDroneX > width - 29 || fltMartianDroneX < 0)
      intMartianDroneSpeedX *= -1;

    fltMartianDroneX += intMartianDroneSpeedX;

    if (fltMartianDroneY < 300)
      boolDescending = true;
    else if (fltMartianDroneY >= height - 32)
      boolDescending = false;

    if (boolDescending) {
      fltMartianDroneY += 2;
      fltMartianDroneRotation = 0;
    } else {
      fltMartianDroneY -= 2 * cos(radians(3 * intDegrees)) + fltMartianDroneX / 1000;
      fltMartianDroneRotation = (float) Math.atan((fltMartianDroneY - fltPrevPosY) / (fltMartianDroneX - fltPrevPosX));
    }
    fltMartianDroneY = Math.min(height - 32, fltMartianDroneY);

    fltPrevPosX = fltMartianDroneX;
    fltPrevPosY = fltMartianDroneY;

  }
}