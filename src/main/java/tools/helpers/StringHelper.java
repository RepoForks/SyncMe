package tools.helpers;


import org.apache.commons.lang3.StringUtils;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 29/01/2017.
 */
public class StringHelper {
    public static int countMatches(String text, String symbol) {
        return StringUtils.countMatches(text, symbol);
    }

    public static String fixUrlPathFormat(String path) {
        if(path.charAt(1) == '/') {
            StringBuilder sb = new StringBuilder(path);
            sb.deleteCharAt(1);
            return sb.toString();
        } else {
            return path;
        }
    }

    public static boolean malformedFirstDepthUrl(String path) {
        String[] splittedPath = path.split("/");
        if(splittedPath.length == 2) {
            return true;
        } else {
            return false;
        }
    }

    public static String fixSlashAtEndOfString(String str) {
        StringBuilder sb = new StringBuilder(str);
        if(sb.charAt(sb.length() - 1) == '/') {
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
        return str;
    }

    public static String getLastWord(String str) {
        String[] splittedStr = str.split("/");
        return splittedStr[splittedStr.length - 1];
    }
}
