package gui;

import tools.system.FileChecker;

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

        JButton loginToDropbox = new JButton("Access to Dropbox account");
        buttonsPanel.add(loginToDropbox);
        loginToDropbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if(FileChecker.fileExists("syncme.auth")) {
                    DropboxMainWindow dropboxMainWindow = new DropboxMainWindow();
                    dropboxMainWindow.showWindow();
                    frame.dispose();
                } else {
                    WarningDialog warningDialog = new WarningDialog("Before accessing to Dropbox window you need to auth your acc with the app.");
                    warningDialog.showWindow();
                }
            }
        });

        JButton loginToGoogleDrive = new JButton("Access to Google Drive account");
        loginToGoogleDrive.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                WarningDialog warningDialog = new WarningDialog("This functionality is not available yet");
                warningDialog.showWindow();
            }
        });
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
