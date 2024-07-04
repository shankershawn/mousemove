/**
 *
 */
package com.shankarsan.mousemove.runners;

import java.awt.*;

/**
 * @author SHANKARSAN
 */
public class MouseMoveRunner implements Runnable {

    private int millis;
    private volatile boolean exit = false;

    public MouseMoveRunner(int millis) {
        this.millis = millis;
    }

    @Override
    public void run() {
        Point point = null;
        int x = 0, y = 0, counter = 0;
        Robot robot;
        while (!exit) {
            try {
                robot = new Robot();
                point = MouseInfo.getPointerInfo().getLocation();
                x = (int) point.getX();
                y = (int) point.getY();
                if (counter == 0 || counter == 3)
                    x += 1;
                else {
                    x -= 1;
                }
                if (counter == 0 || counter == 1)
                    y += 1;
                else
                    y -= 1;
                robot.mouseMove(x, y);
                Thread.sleep(millis);
                counter++;
                if (counter > 3) counter = 0;

            } catch (HeadlessException | InterruptedException | AWTException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        this.exit = true;
    }

}
