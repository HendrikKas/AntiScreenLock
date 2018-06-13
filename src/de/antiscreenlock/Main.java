package de.antiscreenlock;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.net.URL;
import javax.swing.ImageIcon;

public class Main {

   public static void main(String[] args) {

      if(SystemTray.isSupported()){
         final TrayIcon trayIcon = new TrayIcon(createImage("image/monitor_lock.png", "AntiScreenLock"));
         final SystemTray systemTray = SystemTray.getSystemTray();
         trayIcon.setToolTip("AntiScreenLock");
         final PopupMenu popupMenu = new PopupMenu();
         final MenuItem exit = new MenuItem("Exit");
         popupMenu.add(exit);
         exit.addActionListener(e -> System.exit(0));
         trayIcon.setPopupMenu(popupMenu);

         try {
            systemTray.add(trayIcon);
         } catch (AWTException ignored){
            // Tray icon could not be added
         }
      }



      try {
         new AntiScreenLock();
      } catch (AWTException e) {
         throw new RuntimeException("AWTException", e);
      }
      System.exit(0);
   }

   //Obtain the image URL
   protected static Image createImage(String path, String description) {
      URL imageURL = Main.class.getResource(path);

      if (imageURL == null) {
         System.err.println("Resource not found: " + path);
         return null;
      } else {
         return new ImageIcon(imageURL, description).getImage();
      }
   }
}
