package tools.helpers;


import org.apache.commons.lang3.StringUtils;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 29/01/2017.
 */
public class StringHelper {
    public static int countMatchs(String text, String symbol) {
        return StringUtils.countMatches(text, symbol);
    }
}
