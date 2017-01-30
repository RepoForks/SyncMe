package gui;

import operations.dropbox.UploadFile;

import javax.swing.*;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 30/01/2017.
 */
public class UploadFileWindow extends JFrame {
    private String lastSelectedFilePath = null;
    private String lastRootDirectory = null;

    public UploadFileWindow(String lastRootDirectory) {
        super();
        this.lastRootDirectory = lastRootDirectory;
    }

    public void showFileChooser() {
        JFileChooser choice = new JFileChooser();
        choice.setDialogTitle("Upload file to current path");
        int option = choice.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            String selectedFilePath = choice.getSelectedFile().getPath();
            System.out.println(selectedFilePath);
            lastSelectedFilePath = selectedFilePath;

            if(UploadFile.uploadFile(lastSelectedFilePath, lastRootDirectory)) {
                SuccessDialog successWindow = new SuccessDialog();
                successWindow.showWindow();
            } else {
                ErrorDialog errorDialog = new ErrorDialog();
                errorDialog.showWindow();
            }
        }
    }

    public String getLastSelectedFilePath() {
        return lastSelectedFilePath;
    }
}
