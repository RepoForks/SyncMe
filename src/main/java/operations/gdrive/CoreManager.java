package operations.gdrive;


import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.DriveScopes;

import java.util.Arrays;
import java.util.List;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 05/02/2017.
 */
public class CoreManager {
    /** Application name. */
    private static final String APPLICATION_NAME = "SyncMe v2 GDrive Core";

    /** Directory to store user credentials for SyncMe. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.dir"), ".syncme-gdrive-credentials/v2");

    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required.
     */
    private static final List<String> SCOPES = Arrays.asList(DriveScopes.DRIVE_FILE, DriveScopes.DRIVE_METADATA, DriveScopes.DRIVE_APPDATA);
}
