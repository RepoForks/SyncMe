package gui;

import structures.ContentTree;
import tools.system.FileChecker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static com.sun.org.apache.xalan.internal.utils.SecuritySupport.getResourceAsStream;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 29/01/2017.
 */
public class DropboxMainWindow extends JFrame {

    private ContentTree dropboxContentTree = new ContentTree();

    public DropboxMainWindow() {
        setTitle("SyncMe: Dropbox Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 800);
        setResizable(false);

        setLayout(new BorderLayout());

        initializeElements();

        //pack();
    }

    public void showWindow() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeElements() {
        JPanel topButtonsPanel = new JPanel(new GridLayout(1,0));

        JButton userInfoButton = new JButton();
        userInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                AccountInfoWindow accInfoWindow = new AccountInfoWindow();
                accInfoWindow.showWindow();
            }
        });
        JButton downButton = new JButton();
        JButton upButton = new JButton();
        JButton deleteButton = new JButton();
        JButton createFolderButton = new JButton();
        JButton configureButton = new JButton();
        try {
            Image img = ImageIO.read(new File("res\\user-identity.png"));
            userInfoButton.setIcon(new ImageIcon(img));

            img = ImageIO.read(new File("res\\go-down.png"));
            downButton.setIcon(new ImageIcon(img));

            img = ImageIO.read(new File("res\\go-up.png"));
            upButton.setIcon(new ImageIcon(img));

            img = ImageIO.read(new File("res\\delete.png"));
            deleteButton.setIcon(new ImageIcon(img));

            img = ImageIO.read(new File("res\\folder-blue.png"));
            createFolderButton.setIcon(new ImageIcon(img));

            img = ImageIO.read(new File("res\\configure.png"));
            configureButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        topButtonsPanel.add(userInfoButton);
        topButtonsPanel.add(downButton);
        topButtonsPanel.add(upButton);
        topButtonsPanel.add(deleteButton);
        topButtonsPanel.add(createFolderButton);
        topButtonsPanel.add(configureButton);

        add(topButtonsPanel, BorderLayout.NORTH);

        add(new JScrollPane(dropboxContentTree), BorderLayout.CENTER);

        add(new JLabel("SyncMe v2 - Copyright 2017 Adrián Rodríguez Bazaga"), BorderLayout.SOUTH);

        dropboxContentTree.addChild("Test", false);
        dropboxContentTree.addChild("Test2", false);
        dropboxContentTree.addChild("Test3", false);
        dropboxContentTree.addChild("Test4", true);
        dropboxContentTree.addChild("Test5", false);
        dropboxContentTree.addChild("Test6", true);

    }
}
