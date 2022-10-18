/**
 * 
 */
package com.shankarsan.mousemove;

import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.shankarsan.mousemove.runners.MouseMoveRunner;
import com.shankarsan.mousemove.runners.ProgressUIRunner;

/**
 * @author SHANKARSAN
 *
 */
public class MouseMove {
	
	static {
		System.setProperty("java.awt.headless", "false");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ProgressUIRunner progressUIRunner = null;
			MouseMoveRunner mouseMoveRunner = null;
			Thread progressThread = null, mouseMoveThread = null;
			if(ArrayUtils.isNotEmpty(args) && ArrayUtils.getLength(args) == 1 && NumberUtils.isParsable(args[0])) {
				System.out.print("Mouse move started with a period gap of " + args[0] + " milliseconds! ... ");
				progressUIRunner = new ProgressUIRunner();
				mouseMoveRunner = new MouseMoveRunner(NumberUtils.toInt(args[0]));
				mouseMoveThread = new Thread(mouseMoveRunner);
				progressThread = new Thread(progressUIRunner);
				mouseMoveThread.start();
				progressThread.start();
				
				Scanner scanner = new Scanner(System.in);
				if(scanner.hasNext()) {
					mouseMoveRunner.stop();
					progressUIRunner.stop();
				}
				scanner.close();
				System.out.println("Exiting mouse move execution!");
			}else {
				System.out.println("Invalid parameters! Please try again with java -jar <jarname>.jar <milliseconds> or in case of docker then, docker run -tid shankershawn/mousemove <milliseconds>");
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
