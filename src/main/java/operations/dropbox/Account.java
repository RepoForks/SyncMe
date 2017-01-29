package operations.dropbox;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 29/01/2017.
 */

import com.dropbox.core.DbxAuthInfo;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.json.JsonReader;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Account
{
    public static void main(String[] args)
            throws IOException
    {
        // Only display important log messages.
        Logger.getLogger("").setLevel(Level.WARNING);

        String argAuthFile = "syncme.auth";
        // Get current account info
        FullAccount account = null;
        try {
            account = CoreManager.getClient().users().getCurrentAccount();
        } catch (DbxException e) {
            e.printStackTrace();
        }
        System.out.println(account.getName().getDisplayName());

        // Read auth info file.
        DbxAuthInfo authInfo;
        try {
            authInfo = DbxAuthInfo.Reader.readFromFile(argAuthFile);
        }
        catch (JsonReader.FileLoadException ex) {
            System.err.println("Error loading <auth-file>: " + ex.getMessage());
            System.exit(1); return;
        }

        // Create a DbxClientV1, which is what you use to make API calls.
        DbxRequestConfig requestConfig = new DbxRequestConfig("examples-account-info");
        DbxClientV2 dbxClient = new DbxClientV2(requestConfig, authInfo.getAccessToken(), authInfo.getHost());

        // Make the /account/info API call.
        FullAccount dbxAccountInfo;
        try {
            dbxAccountInfo = dbxClient.users()
                    .getCurrentAccount();
        }
        catch (DbxException ex) {
            System.err.println("Error making API call: " + ex.getMessage());
            System.exit(1); return;
        }

        System.out.print(dbxAccountInfo.toStringMultiline());
    }
}
