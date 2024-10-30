import java.util.HashMap;

public class SizeCalculator {

    private static char[] multipliers = {'B', 'K', 'M', 'G', 'T'};
    private static HashMap<Character, Integer> valueMultiplier = new HashMap<>();

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
        for(int i=0; i < multipliers.length; i++) {
            valueMultiplier.put(
                    multipliers[i],
                    (int) Math.pow(1024,i)
            );
        }
        return valueMultiplier;
    }
}
