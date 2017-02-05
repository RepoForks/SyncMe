package operations.gdrive;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 05/02/2017.
 */
public class QueryFactory {
    public static String getParentsFromIdQuery(String id) {
        return "'0B4QoXuNqoXbFQkV6SEM2SzNNMTA' in parents";
    }

    public static String getElementsFromRootQuery() {
        return "'root' in parents";
    }

    public static String getElementWithNameQuery(String name) {
        return "name='" + name + "'";
    }
}
