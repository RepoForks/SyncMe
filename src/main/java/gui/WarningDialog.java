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
public class WarningDialog extends JFrame {
    public WarningDialog(String text) {
        setTitle("A warning occurred");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);

        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("res\\warning.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel picLabel = new JLabel(new ImageIcon(myPicture));

        add(picLabel, BorderLayout.CENTER);

        JLabel errorText = new JLabel(text);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });

        JPanel bottomPart = new JPanel(new GridLayout(2,1));
        bottomPart.setBackground(Color.WHITE);
        bottomPart.add(errorText);
        bottomPart.add(okButton);

        add(bottomPart, BorderLayout.SOUTH);

        pack();
    }

    public void showWindow() {
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
