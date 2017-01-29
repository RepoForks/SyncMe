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
public class SplashScreen {

    private JFrame frame;

    public SplashScreen() {
        frame = new JFrame("SyncMe: Splash screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.WHITE);

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

        BufferedImage myPicture = null;
        ImageIcon windowIcon = new ImageIcon("res\\syncme.png");
        try {
            myPicture = ImageIO.read(new File("res\\syncme.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel picLabel = new JLabel(new ImageIcon(myPicture));

        frame.add(picLabel, BorderLayout.CENTER);

        JPanel bottomPart = new JPanel(new BorderLayout());
        JLabel infoText = new JLabel("<html><center><p style=\"color: #000000; background-color: #ffffff\">This is a prototype of <b>SyncMe v2</b>, ported to<br>the Java language and fixed to work with the newest<br>versions of Dropbox (v2) and Google Drive (v3) APIs.<br>If you have interest in collaborate with the project,<br>please don't hesitate to do it,remember to<br>read the project basis.</p></center></html>");
        bottomPart.add(infoText, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        JButton licenseButton = new JButton("LICENSE");

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                InitialWindow initialWindow = new InitialWindow();
                initialWindow.showWindow();
                frame.dispose();
            }
        });

        licenseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
            }
        });

        JPanel buttonsPart = new JPanel(new GridLayout(1,2));

        buttonsPart.add(okButton);
        buttonsPart.add(licenseButton);

        bottomPart.add(buttonsPart, BorderLayout.SOUTH);

        frame.add(bottomPart, BorderLayout.SOUTH);

        frame.setIconImage(windowIcon.getImage());
    }
}
