/**
 *
 */
package com.shankarsan.mousemove.runners;

/**
 * @author SHANKARSAN
 *
 */
public class ProgressUIRunner implements Runnable {
  private volatile boolean exit = false;
  private final Thread parentThread;

  public ProgressUIRunner(Thread parentThread) {
    this.parentThread = parentThread;
  }

  @Override
  public void run() {
    try {
      String animElements = "/|\\-";
      for (int index = 0; index < animElements.length() && !exit; index++) {
        System.out.print("\b");
        System.out.print(animElements.charAt(index));
        System.out.flush();
        if (index == animElements.length() - 1) {
          index = -1;
        }
        Thread.sleep(500);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      synchronized (parentThread) {
        parentThread.notify();
      }
    }
  }

  public void stop() {
    this.exit = true;
  }
}
