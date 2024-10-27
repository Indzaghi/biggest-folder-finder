import java.io.File;
import java.util.ArrayList;

//для хранения папок и их размеров
public class Node {

    private File folder;
    private ArrayList<Node> children; //у этого узла будут такие же узлы в потомках

    private long size;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }



    public Node(File folder) {
        this.folder=folder;
        children= new ArrayList<>();
    }

    public File getFolder() {
        return folder;
    }

    public void addChild(Node node) {
        children.add(node);
    }
    public ArrayList<Node> getChildren() {
        return children;
    }

}
