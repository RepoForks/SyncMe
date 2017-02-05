package operations.gdrive;

import com.google.api.services.drive.model.About;

import java.io.IOException;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 05/02/2017.
 */
public class Account {

    public static About account = null;

    public static void initialize() {
        try {
            account = CoreManager.getDriveService().about().get().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getAccountName() {
        if(account == null) initialize();

        return account.getUser().getDisplayName();
    }

    public static String getAccountType() {
        if(account == null) initialize();

        return account.getUser().getKind();
    }

    public static String getAccountEmail() {
        if(account == null) initialize();

        return account.getUser().getEmailAddress();
    }

    public static String getAccountId() {
        if(account == null) initialize();

        return account.getUser().getPermissionId();
    }

    public static String getProfilePhotoUrl() {
        if(account == null) initialize();

        return account.getUser().getPhotoLink();
    }
}
