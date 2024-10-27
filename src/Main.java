import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {



        //String folderPath = "D:/Oksana";
        File file = new File("D:/Oksana");
        Node root = new Node(file);

        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println(size);




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


    //TODO: 24B, 234Kb, 36Mb, 34Gb
    public static String getHumanReadableSize(long size) {
        String strSize=" ";
        final int BYTE = 1024;
        //DecimalFormat df = new DecimalFormat("#.00");
        for(int factor=3; factor>0; factor--) {
            if(size/Math.pow(BYTE,factor)>=1) {
                strSize = String.valueOf(size/Math.pow(BYTE,factor));
                if(factor==3) {strSize += "Gb"; }
                else if(factor==2) {strSize += "Mb"; }
                else if (factor==1) {strSize += "Kb"; }
                break;
            } else  {
                return size + "B";
            }
        }
        return strSize;

    }

    //TODO: 24B, 234Kb, 36Mb, 34Gb
    // 235K => 240640
    public static long getSizeFromHumanReadableSize(String size) {
        HashMap<Character, Integer> valueMultiplier = valueMultiplier();
        char sizeFactor = size
                .replaceAll("[^a-zA-Z]+", "")
                .charAt(0);
        int multiplier = valueMultiplier.get(sizeFactor);
        double doubleSize= Double.parseDouble(size.replaceAll("[a-zA-Z]+", ""));

        long longSize = multiplier * (long)doubleSize;

        return longSize;

    }

    /*private static HashMap<Character, Integer> valueMultiplier() {
        HashMap<Character, Integer> valueMultiplier = new HashMap<>();
        valueMultiplier.put('B', 1);
       valueMultiplier.put('K', 1024);
       valueMultiplier.put('M', 1024*1024);
       valueMultiplier.put('G', 1024*1024*1024);
        return valueMultiplier;
    } */

    private static HashMap<Character, Integer> valueMultiplier() {
        Character[] multipliers = {'B', 'K', 'M', 'G'};
        HashMap<Character, Integer> valueMultiplier = new HashMap<>();
        for(int i=0; i < multipliers.length; i++) {
            valueMultiplier.put(
                    multipliers[i],
                    (int) Math.pow(1024,i)
            );
        }
        return valueMultiplier;
    }

}