package de.antiscreenlock;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

public class AntiScreenLock {

   private final Robot robot;

   public AntiScreenLock() throws AWTException {
      this.robot = new Robot();
   }

   public void antiScreenLock() {
      Point mousePosition = MouseInfo.getPointerInfo().getLocation();
      robot.mouseMove(mousePosition.x + 1, mousePosition.y + 1);
      robot.mouseMove(mousePosition.x, mousePosition.y);
   }
}
