package gui;

import operations.dropbox.GetAccessToken;

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
public class DropboxAuthorizationWindow {
    private JFrame frame;

    public DropboxAuthorizationWindow() {
        frame = new JFrame("SyncMe: Authorize app in Dropbox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
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
            myPicture = ImageIO.read(new File("res\\dropbox-logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel picLabel = new JLabel(new ImageIcon(myPicture));

        frame.add(picLabel, BorderLayout.CENTER);

        JPanel bottomPart = new JPanel(new BorderLayout());
        bottomPart.setBackground(Color.WHITE);
        JLabel infoText = new JLabel("<html><br><center><p style=\"color: #000000; background-color: #ffffff\">In order to use SyncMe with Dropbox you need to authorize the app, follow the next steps:<br>" +
                                          "1. Go to the auth URL (read below) <br>" +
                                          "2. Click \"Allow\" (you have to log in first).<br>" +
                                          "3. Copy the authorization code.<br>" +
                                          "</p></center></html>");
        bottomPart.add(infoText, BorderLayout.CENTER);

        JPanel typeAuthCodePart = new JPanel(new BorderLayout());
        JPanel typeAuthCodePartBottom = new JPanel(new GridLayout(1,3));
        typeAuthCodePart.setBackground(Color.WHITE);
        typeAuthCodePartBottom.setBackground(Color.WHITE);

        JTextField f = new JTextField("Auth URL: " + GetAccessToken.getAuthUrl());
        f.setEditable(false);
        typeAuthCodePart.add(f, BorderLayout.CENTER);

        typeAuthCodePartBottom.add(new JLabel("Enter the authorization code here: "));

        JTextField authCodeTextField = new JTextField();
        typeAuthCodePartBottom.add(authCodeTextField);

        JButton acceptCodeButton = new JButton("CHECK");
        acceptCodeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                String authCode = authCodeTextField.getText();
                if(GetAccessToken.tryAuthorization(authCode)) {
                    try {
                        if(GetAccessToken.saveAuthInfoToFile()) {
                            showSuccessDialog();
                        } else {
                            showErrorDialog();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        showErrorDialog();
                    }
                } else {
                    showErrorDialog();
                }
            }
        });
        typeAuthCodePartBottom.add(acceptCodeButton);


        typeAuthCodePart.add(typeAuthCodePartBottom, BorderLayout.SOUTH);
        bottomPart.add(typeAuthCodePart, BorderLayout.SOUTH);

        frame.add(bottomPart, BorderLayout.SOUTH);

        frame.setIconImage(windowIcon.getImage());
    }

    public void showErrorDialog() {
        ErrorDialog errorDialog = new ErrorDialog();
        errorDialog.showWindow();
    }

    public void showSuccessDialog() {
        frame.dispose();
        SuccessDialog successDialog = new SuccessDialog();
        successDialog.showWindow();
    }
}
