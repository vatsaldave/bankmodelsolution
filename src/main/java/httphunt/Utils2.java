package httphunt;

import java.util.ArrayList;
import java.util.List;

public class Utils2 {
    public static void main(String[] args) {


    }

    public static List<String> getListOfChars(String str) {
        List<String> retVal = new ArrayList<String>();
        for (char c : str.toCharArray()) {
            retVal.add(c + "");
        }
        return retVal;
    }

    public static boolean containsAll(List<String> superSet, String str) {
        List<String> subSet = getListOfChars(str);
        return superSet.containsAll(subSet);
    }

}
