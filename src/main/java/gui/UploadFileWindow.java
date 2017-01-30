package gui;

import javax.swing.*;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 30/01/2017.
 */
public class UploadFileWindow extends JFrame {
    private String lastSelectedFilePath = null;

    public UploadFileWindow() {
        super();
    }

    public void showFileChooser() {
        JFileChooser choice = new JFileChooser();
        int option = choice.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            String selectedFilePath = choice.getSelectedFile().getPath();
            System.out.println(selectedFilePath);
            lastSelectedFilePath = selectedFilePath;
        }
    }

    public String getLastSelectedFilePath() {
        return lastSelectedFilePath;
    }
}
