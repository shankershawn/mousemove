/**
 *
 */
package com.shankarsan.mousemove;

import com.shankarsan.mousemove.runners.MouseMoveRunner;
import com.shankarsan.mousemove.runners.ProgressUIRunner;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Scanner;

/**
 * @author SHANKARSAN
 *
 */
public class MouseMove {

  /**
   * @param args
   */
  public static void main(String[] args) {
    try {
      ProgressUIRunner progressUIRunner = null;
      MouseMoveRunner mouseMoveRunner = null;
      Thread progressThread = null, mouseMoveThread = null, exitThread = null, parentThread = Thread.currentThread();
      if (ArrayUtils.isNotEmpty(args) && ArrayUtils.getLength(args) == 1 && NumberUtils.isParsable(args[0])) {
        System.out.println("Mouse move started with a period gap of " + args[0] + " milliseconds! ... Enter any key followed by return to stop the execution.");
        progressUIRunner = new ProgressUIRunner(parentThread);
        mouseMoveRunner = new MouseMoveRunner(NumberUtils.toInt(args[0]), parentThread);
        mouseMoveThread = new Thread(mouseMoveRunner);
        progressThread = new Thread(progressUIRunner);
        exitThread = new Thread(getExitThread(parentThread));
        mouseMoveThread.start();
        progressThread.start();
        exitThread.start();
        synchronized (parentThread) {
          parentThread.wait();
        }
        mouseMoveRunner.stop();
        progressUIRunner.stop();
      } else {
        System.out.println("Invalid parameters! Please try again with java -jar <jarname>.jar <milliseconds> or in case of docker then, docker run -tid shankershawn/mousemove <milliseconds>");
        Thread.sleep(2000);
      }
      System.out.println("Exiting mouse move execution!");
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }

  private static Runnable getExitThread(final Thread parentThread) {
    return () -> {
      Scanner scanner = new Scanner(System.in);
      if (scanner.hasNext()) {
        scanner.close();
        synchronized (parentThread) {
          parentThread.notify();
        }
      }
    };
  }

}
