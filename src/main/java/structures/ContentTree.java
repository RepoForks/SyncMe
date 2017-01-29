package structures;

import com.dropbox.core.v2.files.ListFolderResult;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 29/01/2017.
 */
public class ContentTree extends JTree {

    public ContentTree() {
        super(new DefaultMutableTreeNode("dir"));
    }

    public void changeRootName(String newName) {
        DefaultTreeModel model = (DefaultTreeModel) this.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        root.setUserObject(newName);
        model.nodeChanged(root);
    }

    /**
     * Add a child to the content tree
     * @param name The name of the child
     * @param type The type of the child (0 = Directory, 1 = File)
     */
    public void addChild(String name, boolean type) {
        if(type) {
            //[F]
            DefaultTreeModel model = (DefaultTreeModel) this.getModel();
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
            model.insertNodeInto(new DefaultMutableTreeNode("[F]" + name), root, root.getChildCount());

        } else {
            //[D]
            DefaultTreeModel model = (DefaultTreeModel) this.getModel();
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
            model.insertNodeInto(new DefaultMutableTreeNode("[D]" + name), root, root.getChildCount());
        }
    }

    /**
     * Remove a child to the content tree
     * @param name The name of the child to remove
     */
    public void removeChild(String name) {
        DefaultTreeModel model = (DefaultTreeModel) this.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        model.removeNodeFromParent(new DefaultMutableTreeNode(name));
    }

    public void clearTree() {
        DefaultTreeModel model = (DefaultTreeModel) this.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        root.removeAllChildren();           // This removes all nodes
        model.reload();                     // This notifies the listeners and changes the GUI
    }

    private void parseTreeNodesNames(ListFolderResult nodes) {

    }

    public void updateContentTree(ListFolderResult nodes) {

    }
}
