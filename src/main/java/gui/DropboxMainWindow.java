package gui;

import javax.imageio.ImageIO;
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
        setSize(800, 600);
        setResizable(false);

        setLayout(new BorderLayout());

        initializeElements();

        pack();
    }

    public void showWindow() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeElements() {
        JPanel topButtonsPanel = new JPanel(new GridLayout(1,0));

        JButton userInfoButton = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("res\\user-identity.png"));
            userInfoButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        topButtonsPanel.add(userInfoButton);

        add(topButtonsPanel, BorderLayout.NORTH);

    }
}
