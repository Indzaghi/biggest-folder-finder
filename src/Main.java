import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //String folderPath = "D:/Oksana";
        File file = new File("D:/Oksana");


        System.out.println(getFoldersList(file));

    }

    public static long getFolderSize(File folder) {
        if(folder.isFile()) {
            return folder.length();
        }
        long sum=0;
        File[] files = folder.listFiles();
        for(File file : files) {
            sum += getFolderSize(file);
        }
        return sum;
    }

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