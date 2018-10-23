package de.antiscreenlock;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.net.URL;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;

public class Main {

   public static void main(String[] args) {
      ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
      AntiScreenLock antiScreenLock = null;
      try {
         antiScreenLock = new AntiScreenLock();
      } catch (AWTException e) {
         throw new RuntimeException("AWTException", e);
      }
      ScheduledFuture antiScreenLockHandle = scheduledExecutorService.scheduleAtFixedRate(antiScreenLock::antiScreenLock, 5, 5,
            TimeUnit.MINUTES);
      if(SystemTray.isSupported()){
         final TrayIcon trayIcon = new TrayIcon(createImage("image/monitor_lock.png", "AntiScreenLock"));
         final SystemTray systemTray = SystemTray.getSystemTray();
         trayIcon.setToolTip("AntiScreenLock");
         final PopupMenu popupMenu = new PopupMenu();
         final MenuItem exit = new MenuItem("Exit");
         popupMenu.add(exit);
         exit.addActionListener(e -> antiScreenLockHandle.cancel(true));
         trayIcon.setPopupMenu(popupMenu);

         try {
            systemTray.add(trayIcon);
         } catch (AWTException ignored){
            // Tray icon could not be added
         }
      }
      try {
         antiScreenLockHandle.get();
      } catch (InterruptedException | ExecutionException | CancellationException ignored) {
         // This is expected! Program will terminate!
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
