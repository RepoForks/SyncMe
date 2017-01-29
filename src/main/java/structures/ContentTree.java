package structures;

import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import gui.DropboxMainWindow;
import operations.dropbox.Indexing;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 29/01/2017.
 */
public class ContentTree extends JTree {

    public ContentTree() {
        super(new DefaultMutableTreeNode("/"));
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
        expandAllNodes(0);
    }

    public void addChildWithParent(String parentName, String name, boolean type) {
        DefaultTreeModel model = (DefaultTreeModel)this.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

        Enumeration children = root.children();
        while(children.hasMoreElements()){
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) children.nextElement();
            if(node.toString().equals(parentName)){
                if(type) {
                    //[F]
                    node.add(new DefaultMutableTreeNode("[F]" + name));

                } else {
                    //[D]
                    node.add(new DefaultMutableTreeNode("[D]" + name));
                }
                model.nodesWereInserted(node, new int[]{node.getChildCount()-1,node.getChildCount()-2});
                break;
            }
        }

        expandAllNodes(0);
    }

    public void exploreDirectory(String name) {
        List<Metadata> directoryData = Indexing.getContentFromFolder("/" + name);
        for(Metadata node : directoryData) {
            if(node instanceof FolderMetadata) {
                addChildWithParent(name, node.getName(), false);
                exploreDirectory(node.getPathLower());
            } else {
                addChildWithParent(name, node.getName(), true);
            }
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

    private void parseTreeNodesNames(List<Metadata> nodes) {

    }

    public void updateContentTree(List<Metadata> nodes) {
        clearTree();        // First we clear the tree

        for(Metadata node : nodes) {
            if(node instanceof FolderMetadata) {
                addChild(node.getName(), false);
            } else {
                addChild(node.getName(), true);
            }
        }
        changeRootName(DropboxMainWindow.lastRootDirectory);
    }

    private void expandAllNodes(int startingIndex){
        for(int i=startingIndex;i<this.getRowCount();++i){
            this.expandRow(i);
        }
    }
}
