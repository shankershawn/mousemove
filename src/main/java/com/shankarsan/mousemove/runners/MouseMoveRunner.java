/**
 * 
 */
package com.shankarsan.mousemove.runners;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

/**
 * @author SHANKARSAN
 *
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
		int x = 0, y = 0;
		try {
			Robot robot = new Robot();
			while(!exit) {
				point = MouseInfo.getPointerInfo().getLocation();				
				x = (int) point.getX();
				y = (int) point.getY();
				x += 1;
				y += 1;
				robot.mouseMove(x, y);
				Thread.sleep(millis);
			}
		} catch (HeadlessException|InterruptedException|AWTException e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		this.exit = true;
	}

}
