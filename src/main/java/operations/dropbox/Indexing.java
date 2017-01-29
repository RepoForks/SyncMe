package operations.dropbox;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 29/01/2017.
 */
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;

import java.util.List;

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

    public static List<Metadata> getContentFromFolder(String path) {
        try {
            System.out.println(path);
            ListFolderResult result = CoreManager.getClient().files().listFolder(path);
            List<Metadata> entries = result.getEntries();
            return entries;
        } catch (DbxException exception) {
            System.exit(1);
        }

        return null;
    }
}
