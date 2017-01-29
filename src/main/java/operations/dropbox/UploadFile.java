package operations.dropbox;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import tools.helpers.StringHelper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 29/01/2017.
 */
public class UploadFile {
    public void uploadFile(String pathToFile, String pathToUpload) {
        // Upload to Dropbox
        try (InputStream in = new FileInputStream(pathToFile)) {
            FileMetadata metadata = CoreManager.getClient().files().uploadBuilder(StringHelper.fixSlashAtEndOfString(pathToUpload)).uploadAndFinish(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UploadErrorException e) {
            e.printStackTrace();
        } catch (DbxException e) {
            e.printStackTrace();
        }
    }
}
