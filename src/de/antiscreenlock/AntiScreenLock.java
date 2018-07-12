package de.antiscreenlock;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

public class AntiScreenLock {

   private final Robot robot;

   public AntiScreenLock() throws AWTException {
      this.robot = new Robot();
      antiScreenLock();
   }

   private void antiScreenLock(){
      while(true) {
         robot.delay(300000);
         Point mousePosition = MouseInfo.getPointerInfo().getLocation();
         robot.mouseMove(mousePosition.x + 1, mousePosition.y + 1);
         robot.mouseMove(mousePosition.x, mousePosition.y);
      }
   }
}
