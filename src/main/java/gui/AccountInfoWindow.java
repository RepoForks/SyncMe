package gui;

import operations.dropbox.Account;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 29/01/2017.
 */
public class AccountInfoWindow extends JFrame {
    public AccountInfoWindow() {
        setTitle("SyncMe: Dropbox Account Info");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);

        ImageIcon windowIcon = new ImageIcon("res\\syncme.png");
        setIconImage(windowIcon.getImage());

        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new URL(Account.getProfilePhotoUrl()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel picLabel = new JLabel(new ImageIcon(myPicture));

        add(picLabel, BorderLayout.NORTH);

        JLabel errorText = new JLabel("<html><b>Account name:</b> " + Account.getAccountName() +
                "<br><b>Account ID:</b> " + Account.getAccountId() +
                "<br><b>Account type:</b> " + Account.getAccountType() +
                "<br><b>Account email:</b> " + Account.getAccountEmail() +
                "<br><b>Is email verified?:</b> " + Account.getAccountEmailIsVerified() +
                "<br><b>Account country:</b> " + Account.getAccountCountry() + "</html>");
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });

        JPanel centerPart = new JPanel(new GridLayout(2,1));
        centerPart.setBackground(Color.WHITE);
        centerPart.add(errorText);
        centerPart.add(okButton);

        add(centerPart, BorderLayout.CENTER);
    }

    public void showWindow() {
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
