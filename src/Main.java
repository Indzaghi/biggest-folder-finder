import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        File file = new File("D:/Oksana");
        Node root = new Node(file);
        System.out.println(root.toString());


        /*
        //String folderPath = "D:/Oksana";


        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println(size);
        System.out.println(SizeCalculator.getHumanReadableSize(size));

*/


    }

  /*  public static long getFolderSize(Node node) {
        File folder = node.getFolder();
        if(folder.isFile()) {
            return folder.length();
        }
        long sum=0;
        File[] files = folder.listFiles();
        for(File file : files) {
            sum += getFolderSize(file);
        }
        node.setSize(sum);
        return sum;
    }*/

    public static List<String> getFoldersList(File folder) {
        List<String> foldersList = new ArrayList<>();
        if(folder.isFile()) {
            System.out.println("it's not a directory");
            return null;
        }
        try {
            File[] files = folder.listFiles();
            for(File file : files) {
                if(file.isDirectory()) {
                    foldersList.add(file.getName());
                }
            }
         } catch (Exception ex) {
            System.out.println(ex.fillInStackTrace());
        }
        return foldersList;
    }



}