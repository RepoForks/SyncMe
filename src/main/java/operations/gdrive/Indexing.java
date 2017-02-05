package operations.gdrive;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import operations.dropbox.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 05/02/2017.
 */
public class Indexing {
    public static List<File> getContentFromRoot() {
        FileList result = null;
        try {
            result = CoreManager.getDriveService().files().list()
                    .setQ(QueryFactory.getElementsFromRootQuery())
                    .setSpaces("drive")
                    //.setFields("nextPageToken, files(id, name)")
                    .execute();
            List<File> files = result.getFiles();
            return files;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
