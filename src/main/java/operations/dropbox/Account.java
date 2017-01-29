package operations.dropbox;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 29/01/2017.
 */

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.users.AccountType;
import com.dropbox.core.v2.users.FullAccount;


public class Account
{
    private static FullAccount account = null;

    public static void initialize() {
        try {
            account = CoreManager.getClient().users().getCurrentAccount();
        } catch (DbxException e) {
            e.printStackTrace();
        }
    }

    public static String getAccountName() {
        if(account == null) initialize();

        return account.getName().getDisplayName();
    }

    public static AccountType getAccountType() {
        if(account == null) initialize();

        return account.getAccountType();
    }

    public static String getAccountEmail() {
        if(account == null) initialize();

        return account.getEmail();
    }

    public static boolean getAccountEmailIsVerified() {
        if(account == null) initialize();

        return account.getEmailVerified();
    }

    public static String getAccountCountry() {
        if(account == null) initialize();

        return account.getCountry();
    }

    public static String getAccountId() {
        if(account == null) initialize();

        return account.getAccountId();
    }
}
