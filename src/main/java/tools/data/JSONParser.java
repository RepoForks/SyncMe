package tools.data;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 29/01/2017.
 */
public class JSONParser {
    public static String parseDropboxTokenFromFile(String fileName) {
        try {
            String text = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
            JSONObject obj = new JSONObject(text);
            String token = obj.getString("access_token");
            System.out.println(token);
            return token;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
