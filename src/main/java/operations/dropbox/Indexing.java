package operations.dropbox;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 29/01/2017.
 */
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

import java.util.List;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

public class Indexing {

    public static List<Metadata> getAllContentRecursivelyFromRoot() {
        try {
            ListFolderResult result = CoreManager.getClient().files().listFolderBuilder("").withIncludeDeleted(false).withRecursive(true).withIncludeMediaInfo(true).start();
            List<Metadata> entries = result.getEntries();
            return entries;
        } catch (DbxException exception) {
            System.exit(1);
        }

        return null;
    }

    public static List<Metadata> getContentFromRoot() {
        try {
            ListFolderResult result = CoreManager.getClient().files().listFolder("");
            List<Metadata> entries = result.getEntries();
            return entries;
        } catch (DbxException exception) {
            System.exit(1);
        }

        return null;
    }
}
