import processing.core.PApplet;

/**
 * A desert background with a Martian drone and saucer flying around(following
 * paths related to sin waves). the saucer periodically shoots lasers
 * 
 * @author: Gordon Z
 */
class Main {
  public static void main(String[] args) {

    String[] processingArgs = { "MySketch" };
    Sketch mySketch = new Sketch();
    PApplet.runSketch(processingArgs, mySketch);
  }

}