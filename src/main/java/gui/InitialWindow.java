package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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
    }

    public void showWindow() {
        frame.setVisible(true);
        frame.pack();
    }

    private void placeComponents(JPanel panel) {

        panel.setLayout(new BorderLayout());

        JPanel buttonsPanel = new JPanel (new GridLayout(0,1));

        BufferedImage myPicture = null;
        ImageIcon windowIcon = new ImageIcon("C:\\Users\\QiCanarias23\\IdeaProjects\\syncme\\res\\syncme.png");
        try {
            myPicture = ImageIO.read(new File("C:\\Users\\QiCanarias23\\IdeaProjects\\syncme\\res\\syncme.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel picLabel = new JLabel(new ImageIcon(myPicture));

        JButton loginToDropbox = new JButton("Login to Dropbox");
        buttonsPanel.add(loginToDropbox);

        JButton loginToGoogleDrive = new JButton("Login to Google Drive");
        buttonsPanel.add(loginToGoogleDrive);

        JButton getAccessTokenButton = new JButton("Get access token");
        buttonsPanel.add(getAccessTokenButton);

        frame.add(buttonsPanel, BorderLayout.NORTH);

        frame.add(picLabel, BorderLayout.CENTER);

        JLabel userLabel = new JLabel("SyncMe prototype v2");
        frame.add(userLabel, BorderLayout.SOUTH);

        frame.setIconImage(windowIcon.getImage());
    }
}
