package gui;

import operations.dropbox.CreateFolder;
import operations.dropbox.Token;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 29/01/2017.
 */
public class CreateFolderWindow extends JFrame {
    public CreateFolderWindow(String currentPath) {
        setTitle("SyncMe: Create folder in current path");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);

        ImageIcon windowIcon = new ImageIcon("res\\syncme.png");
        setIconImage(windowIcon.getImage());

        JPanel folderInputBottomPart = new JPanel(new BorderLayout());
        JPanel folderInputPart = new JPanel(new GridLayout(1,2));
        folderInputPart.setBackground(Color.WHITE);
        folderInputBottomPart.setBackground(Color.WHITE);

        folderInputPart.add(new JLabel("Enter folder name: "));

        JTextField folderNameTextField = new JTextField();
        folderInputPart.add(folderNameTextField);

        JButton createFolderButton = new JButton("CREATE");
        createFolderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                String folderName = folderNameTextField.getText();
                if(folderName != null) {
                    if(CreateFolder.createFolder(currentPath, folderName)) {
                        SuccessDialog successDialog = new SuccessDialog();
                        successDialog.showWindow();
                        dispose();
                    } else {
                        ErrorDialog errorDialog = new ErrorDialog();
                        errorDialog.showWindow();
                    }
                } else {
                    WarningDialog warningDialog = new WarningDialog("Folder name cannot be empty");
                    warningDialog.showWindow();
                }
                //
            }
        });
        folderInputBottomPart.add(createFolderButton);

        add(folderInputPart, BorderLayout.NORTH);
        add(folderInputBottomPart, BorderLayout.SOUTH);
    }
}
