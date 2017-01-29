package operations.dropbox;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 29/01/2017.
 */
public class CoreManager {
    private static String ACCESS_TOKEN = "EMPTY";
    // Create Dropbox client
    private static DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
    private static DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

    public static DbxClientV2 getClient() {
        return client;
    }

    public static DbxRequestConfig getConfig() {
        return config;
    }
}
