package logic;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 29/01/2017.
 */

import gui.InitialWindow;
import gui.SplashScreen;
import tools.data.JSONParser;

import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                try {
                    SplashScreen frame = new SplashScreen();
                    frame.showWindow();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
