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
public class SuccessDialog extends JFrame {
    public SuccessDialog() {
        setTitle("An error occured");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);

        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("res\\success.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel picLabel = new JLabel(new ImageIcon(myPicture));

        add(picLabel, BorderLayout.CENTER);

        JLabel successText = new JLabel("Operation completed successfully");
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });

        JPanel bottomPart = new JPanel(new GridLayout(2,1));
        bottomPart.setBackground(Color.WHITE);
        bottomPart.add(successText);
        bottomPart.add(okButton);

        add(bottomPart, BorderLayout.SOUTH);

        pack();
    }

    public void showWindow() {
        setVisible(true);
    }
}
