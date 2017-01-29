package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 29/01/2017.
 */
public class DropboxMainWindow extends JFrame {

    public DropboxMainWindow() {
        setTitle("SyncMe: Dropbox Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        placeComponents(panel);

        pack();
    }

    public void showWindow() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void placeComponents(JPanel panel) {

    }
}
