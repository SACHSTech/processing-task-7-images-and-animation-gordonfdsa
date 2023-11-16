import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  // initializing images
  PImage imgDesertBackground;
  PImage imgMartianSaucer;
  PImage imgMartianDrone;
  PImage imgMartianDrone2;
  PImage imgLaser;

  // initializing positions/variables

  // Martian Saucer/UFO thing
  float fltMartianSaucerX = 0;
  float fltMartianSaucerY = 100;
  int intMartianSaucerSpeedX = 1;
  int intDegrees = 0;

  // Martian Drone
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
    imgLaser = loadImage("Laser.jpg");
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {

    // drawing desert background
    image(imgDesertBackground, 0, 0);

    // calculates and updates the Saucer and Drone positions.
    calculateSaucerPosition();
    calculateDronePosition();

    // drawing martian drone
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

    // only want to draw the laser periodically
    if (intDegrees % 400 < 180) {
      noStroke();
      fill(255);
      rect(fltMartianSaucerX + 92, fltMartianSaucerY + 60, 25, 600);
    }
    // drawing martian saucer
    image(imgMartianSaucer, fltMartianSaucerX, fltMartianSaucerY);

    intDegrees += 1;
  }

  /**
   * Description: calculates the position of the saucer/UFO, uses global veriables
   * to modify
   * 
   * No param, no return
   */
  public void calculateSaucerPosition() {
    // If the UFO is about to go out of the screen, make it bounce and travel the
    // other way
    if (fltMartianSaucerX > width - 210 || fltMartianSaucerX < 0)
      intMartianSaucerSpeedX *= -1;

    // Modify its (x,y) position
    fltMartianSaucerX += intMartianSaucerSpeedX;
    fltMartianSaucerY += cos(radians(intDegrees));
  }

  /**
   * Description: calculaes the position of the smaller drone uses global
   * veriables to modify
   * 
   * No param, no return
   */
  public void calculateDronePosition() {
    // If the Drone is about to go out of the screen, make it bounce and travel the
    // other way
    if (fltMartianDroneX > width - 29 || fltMartianDroneX < 0)
      intMartianDroneSpeedX *= -1;

    // Modify its (x,y) position
    fltMartianDroneX += intMartianDroneSpeedX;

    // If the Drone is above the middle of the screen, set it to descending
    // animation, when it touches the ground, make it ascend again
    if (fltMartianDroneY < 300)
      boolDescending = true;
    else if (fltMartianDroneY >= height - 32)
      boolDescending = false;

    // If it is in the descending phase
    if (boolDescending) {
      fltMartianDroneY += 2;
      // set the drone rotation to 0, meaning the drone is parrallel to the ground
      fltMartianDroneRotation = 0;
    }
    // If it is in the ascending phase
    else {
      fltMartianDroneY -= 2 * cos(radians(3 * intDegrees)) + fltMartianDroneX / 1000;
      // set angle of drone rotation to: arctan(current slope)
      fltMartianDroneRotation = (float) Math.atan((fltMartianDroneY - fltPrevPosY) / (fltMartianDroneX - fltPrevPosX));
    }

    // saves the current iteration (x,y) values to calculate slope with the next
    // iteration's values.
    fltPrevPosX = fltMartianDroneX;
    fltPrevPosY = fltMartianDroneY;

  }
}