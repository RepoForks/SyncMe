package operations.dropbox;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import tools.data.JSONParser;
import tools.security.StringHash;
import tools.system.FileChecker;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 29/01/2017.
 */
public class CoreManager {
    private static String ACCESS_TOKEN = "EMPTY";
    // Create Dropbox client
    private static DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
    private static DbxClientV2 client;

    public static DbxClientV2 getClient() {
        if(client == null) {
            client = new DbxClientV2(config, ACCESS_TOKEN);
        }
        return client;
    }

    public static DbxRequestConfig getConfig() {
        return config;
    }

    public static void setAccessToken(String newAccessToken) {
        CoreManager.ACCESS_TOKEN = StringHash.decrypt(newAccessToken);
    }

    public static void updateAccessTokenFromFile() {
        if(FileChecker.fileExists("syncme.auth")) {
            setAccessToken(JSONParser.parseDropboxTokenFromFile("syncme.auth"));
        }
    }
}
