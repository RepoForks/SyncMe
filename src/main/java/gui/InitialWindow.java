package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 29/01/2017.
 */
public class InitialWindow {

    private JFrame frame;

    public InitialWindow() {
        frame = new JFrame("SyncMe: Initial window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        placeComponents(panel);

        frame.pack();
    }

    public void showWindow() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {

        panel.setLayout(new BorderLayout());

        JPanel buttonsPanel = new JPanel (new GridLayout(0,1));

        ImageIcon windowIcon = new ImageIcon("res\\syncme.png");

        JButton loginToDropbox = new JButton("Login to Dropbox");
        buttonsPanel.add(loginToDropbox);

        JButton loginToGoogleDrive = new JButton("Login to Google Drive");
        buttonsPanel.add(loginToGoogleDrive);

        JButton getAccessTokenButton = new JButton("Get access token");
        buttonsPanel.add(getAccessTokenButton);

        getAccessTokenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                DropboxAuthorizationWindow dropboxAuthWindow = new DropboxAuthorizationWindow();
                dropboxAuthWindow.showWindow();
                frame.dispose();
            }
        });

        frame.add(buttonsPanel, BorderLayout.CENTER);

        JLabel userLabel = new JLabel("Copyright 2017 Adrián Rodríguez Bazaga");
        frame.add(userLabel, BorderLayout.SOUTH);

        frame.setIconImage(windowIcon.getImage());
    }
}
