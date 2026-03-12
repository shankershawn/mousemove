/**
 *
 */
package com.shankarsan.mousemove.runners;

import java.awt.*;

/**
 * @author SHANKARSAN
 */
public class MouseMoveRunner implements Runnable {

    private final int millis;
    private final Thread parentThread;
    private volatile boolean exit = false;

    public MouseMoveRunner(int millis, Thread parentThread) {
        this.millis = millis;
        this.parentThread = parentThread;
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

            } catch (Exception e) {
                e.printStackTrace();
                stop();
            }
        }
        synchronized (parentThread) {
            parentThread.notify();
        }
    }

    public void stop() {
        this.exit = true;
    }

}
