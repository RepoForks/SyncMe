package operations.dropbox;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxAuthInfo;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuth;
import com.dropbox.core.json.JsonReader;
import tools.security.StringHash;

import java.io.File;
import java.io.IOException;

public class Token {

    public static String argAppInfoFile = "syncme.app";
    public static String argAuthFileOutput = "syncme.auth";
    public static String authorizeUrl = null;
    public static DbxAppInfo appInfo;                         // Read app info file (contains app key and app secret)
    public static DbxRequestConfig requestConfig = new DbxRequestConfig("authorize");             // Dropbox API authorization process
    public static DbxWebAuth webAuth;
    public static DbxWebAuth.Request webAuthRequest = DbxWebAuth.newRequestBuilder().withNoRedirect().build();
    public static DbxAuthFinish authFinish;
    public static boolean initialized = false;

    private static boolean initialize() {
        try {
            if(!initialized) {
                appInfo = DbxAppInfo.Reader.readFromFile(argAppInfoFile);
                webAuth = new DbxWebAuth(requestConfig, appInfo);
                initialized = true;
                return initialized;
            } else {
                return initialized;
            }
        } catch (JsonReader.FileLoadException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getAuthUrl() {
        initialize();

        if(authorizeUrl != null) {
            return authorizeUrl;
        } else {
            authorizeUrl = webAuth.authorize(webAuthRequest);
            return authorizeUrl;
        }
    }

    public static boolean tryAuthorization(String code) {
        initialize();

        if (code == null) {
            return false;
        }

        code = code.trim();

        try {
            authFinish = webAuth.finishFromCode(code);
            return true;
        } catch (DbxException ex) {
            System.err.println("Error in DbxWebAuth.authorize: " + ex.getMessage());
            return false;
        }
    }

    public static void showAuthInformation() {
        initialize();

        System.out.println("Authorization complete.");
        System.out.println("- User ID: " + authFinish.getUserId());
        System.out.println("- Access Token: " + authFinish.getAccessToken());
    }

    public static boolean saveAuthInfoToFile() throws IOException {
        initialize();

        // Save auth information to output file.
        DbxAuthInfo authInfo = new DbxAuthInfo(StringHash.encrypt(authFinish.getAccessToken()), appInfo.getHost());
        File output = new File(argAuthFileOutput);
        try {
            DbxAuthInfo.Writer.writeToFile(authInfo, output);
            System.out.println("Saved authorization information to \"" + output.getCanonicalPath() + "\".");
            return true;
        } catch (IOException ex) {
            System.err.println("Error saving to <auth-file-out>: " + ex.getMessage());
            System.err.println("Dumping to stderr instead:");
            DbxAuthInfo.Writer.writeToStream(authInfo, System.err);
            return false;
        }
    }
}