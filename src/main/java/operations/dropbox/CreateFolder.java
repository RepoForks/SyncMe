package operations.dropbox;

import com.dropbox.core.v2.files.CreateFolderErrorException;
import com.dropbox.core.v2.files.FolderMetadata;
import gui.WarningDialog;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 29/01/2017.
 */
public class CreateFolder {
    public static boolean createFolder(String path, String name) {
        try {
            FolderMetadata folder = CoreManager.getClient().files().createFolder(path + name);
            System.out.println(folder.getName());
        } catch (CreateFolderErrorException err) {
            if (err.errorValue.isPath() && err.errorValue.getPathValue().isConflict()) {
                WarningDialog warningDialog = new WarningDialog("Something already exists at the path.");
                return false;
            } else {
                return false;
            }
        } catch (Exception err) {
            return false;
        }

        return false;
    }
}
