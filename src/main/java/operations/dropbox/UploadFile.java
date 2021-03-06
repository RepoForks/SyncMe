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
    public static boolean uploadFile(String pathToFile, String pathToUpload) {
        // Upload to Dropbox
        try (InputStream in = new FileInputStream(pathToFile)) {
            FileMetadata metadata = CoreManager.getClient().files().uploadBuilder(pathToUpload).uploadAndFinish(in);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (UploadErrorException e) {
            e.printStackTrace();
            return false;
        } catch (DbxException e) {
            e.printStackTrace();
            return false;
        }
    }
}
