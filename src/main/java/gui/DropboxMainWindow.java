package gui;

import operations.dropbox.Indexing;
import structures.ContentTree;
import tools.helpers.StringHelper;
import tools.system.FileChecker;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
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
    private String lastSelectedNode = new String("/");
    public static String lastRootDirectory = "/";

    public DropboxMainWindow() {
        setTitle("SyncMe: Dropbox Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 800);
        setResizable(false);

        ImageIcon windowIcon = new ImageIcon("res\\syncme.png");
        setIconImage(windowIcon.getImage());

        setLayout(new BorderLayout());

        initializeElements();
        initializeTreeListener();

        //pack();
    }

    private void initializeTreeListener() {
        dropboxContentTree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e)
            {
                //Returns the last path element of the selection.
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) dropboxContentTree.getLastSelectedPathComponent();

                if (node == null)
                    //Nothing is selected.
                    return;

                String newPath = node.getUserObject().toString().split("\\[D\\]")[1];
                System.out.println(lastRootDirectory + newPath);
                lastSelectedNode = lastRootDirectory + newPath;
            }
        });
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
        JButton backButton = new JButton();
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if(StringHelper.countMatches(lastRootDirectory, "/") == 1 || StringHelper.malformedFirstDepthUrl(lastRootDirectory)) {
                    lastRootDirectory = "/";
                    dropboxContentTree.updateContentTree(Indexing.getContentFromFolder(""));
                } else {
                    String[] splittedPath = lastRootDirectory.split("/");
                    String newPath = "/";
                    for(int i = 0; i < splittedPath.length - 1; i++) {
                        newPath += splittedPath[i] + "/";
                    }
                    newPath = StringHelper.fixUrlPathFormat(newPath);
                    lastRootDirectory = newPath;
                    dropboxContentTree.updateContentTree(Indexing.getContentFromFolder(newPath));
                }
            }
        });

        JButton forwardButton = new JButton();
        forwardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                lastRootDirectory = lastSelectedNode + "/";
                dropboxContentTree.updateContentTree(Indexing.getContentFromFolder(lastSelectedNode));
            }
        });
        JButton deleteButton = new JButton();
        JButton createFolderButton = new JButton();
        createFolderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                CreateFolderWindow createFolderWindow = new CreateFolderWindow(lastRootDirectory);
                createFolderWindow.showWindow();
            }
        });

        JButton uploadFileButton = new JButton();
        uploadFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                UploadFileWindow uploadFileWindow = new UploadFileWindow(lastRootDirectory);
                uploadFileWindow.showFileChooser();
            }
        });

        JButton configureButton = new JButton();
        try {
            Image img = ImageIO.read(new File("res\\user-identity.png"));
            userInfoButton.setIcon(new ImageIcon(img));

            img = ImageIO.read(new File("res\\go-down.png"));
            backButton.setIcon(new ImageIcon(img));

            img = ImageIO.read(new File("res\\go-up.png"));
            forwardButton.setIcon(new ImageIcon(img));

            img = ImageIO.read(new File("res\\upload.png"));
            uploadFileButton.setIcon(new ImageIcon(img));

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
        topButtonsPanel.add(backButton);
        topButtonsPanel.add(forwardButton);
        topButtonsPanel.add(uploadFileButton);
        topButtonsPanel.add(createFolderButton);
        topButtonsPanel.add(deleteButton);
        topButtonsPanel.add(configureButton);

        add(topButtonsPanel, BorderLayout.NORTH);

        add(new JScrollPane(dropboxContentTree), BorderLayout.CENTER);

        add(new JLabel("SyncMe v2 - Copyright 2017 Adrián Rodríguez Bazaga"), BorderLayout.SOUTH);

        lastRootDirectory = "/";
        dropboxContentTree.updateContentTree(Indexing.getContentFromFolder(""));
        //dropboxContentTree.updateContentTree(Indexing.getAllContentRecursivelyFromRoot());
    }
}
