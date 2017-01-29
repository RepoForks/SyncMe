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

public class Listing {
    private static final String ACCESS_TOKEN = "Cgn8g1z7JVoAAAAAAAAqb62RrSIaVp4YG_o7qzE-TU6xt1zJ-p0IdCPQCq5Cfnub";

    public static void main(String args[]) throws DbxException, IOException {
        // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        // Get current account info
        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());

        // Get files and folder metadata from Dropbox root directory
        ListFolderResult result = client.files().listFolder("/");
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }

        // Upload "test.txt" to Dropbox
        try (InputStream in = new FileInputStream("C:\\Users\\QiCanarias23\\IdeaProjects\\syncme\\res\\test.txt")) {
            FileMetadata metadata = client.files().uploadBuilder("/test.txt")
                    .uploadAndFinish(in);
        }
    }
}
