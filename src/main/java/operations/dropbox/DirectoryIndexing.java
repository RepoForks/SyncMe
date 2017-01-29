package operations.dropbox;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 29/01/2017.
 */
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

import java.util.List;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

public class DirectoryIndexing {

    public static void main(String args[]) throws DbxException, IOException {
        CoreManager.updateAccessTokenFromFile();

        // Get current account info
        FullAccount account = CoreManager.getClient().users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());

        // Get files and folder metadata from Dropbox root directory
        ListFolderResult result = CoreManager.getClient().files().listFolder("");
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }

            result = CoreManager.getClient().files().listFolderContinue(result.getCursor());
        }
    }
}
