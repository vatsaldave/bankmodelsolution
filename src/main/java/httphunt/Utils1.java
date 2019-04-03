package httphunt;

import java.util.HashMap;
import java.util.Map;

public class Utils1 {
    public static void main(String[] args) {

        System.out.println(getNewString("F KFRTZX JCUQTWJW TSHJ XFNI, YMFY YMJ JCYWFTWINSFWD NX NS BMFY BJ IT, STY BMT BJ FWJ. LT JCUQTWJ!", 5));

    }

    public static String getNewString(String str, int offset) {
        StringBuilder builder = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isLetter(c)) {
                int position = Integer.valueOf(getCharToPos(c + ""));
                int destVal = getNewVal(position, offset);
                builder.append(getPosToChar(destVal + ""));
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    public static int getNewVal(int current, int key) {
        int max = 26;
        int min = 1;
        if (current - key > 0) {
            return current - key;
        }
        return (current - key) + max;
    }

    public static String getCharToPos(String str) {
        Map<String, String> mapVal = new HashMap<String, String>();
        mapVal.put("A", "1");
        mapVal.put("B", "2");
        mapVal.put("C", "3");
        mapVal.put("D", "4");
        mapVal.put("E", "5");
        mapVal.put("F", "6");
        mapVal.put("G", "7");
        mapVal.put("H", "8");
        mapVal.put("I", "9");
        mapVal.put("J", "10");
        mapVal.put("K", "11");
        mapVal.put("L", "12");
        mapVal.put("M", "13");
        mapVal.put("N", "14");
        mapVal.put("O", "15");
        mapVal.put("P", "16");
        mapVal.put("Q", "17");
        mapVal.put("R", "18");
        mapVal.put("S", "19");
        mapVal.put("T", "20");
        mapVal.put("U", "21");
        mapVal.put("V", "22");
        mapVal.put("W", "23");
        mapVal.put("X", "24");
        mapVal.put("Y", "25");
        mapVal.put("Z", "26");
        return mapVal.get(str.toUpperCase());
    }

    public static String getPosToChar(String str) {
        Map<String, String> mapVal = new HashMap<String, String>();
        mapVal.put("1", "A");
        mapVal.put("2", "B");
        mapVal.put("3", "C");
        mapVal.put("4", "D");
        mapVal.put("5", "E");
        mapVal.put("6", "F");
        mapVal.put("7", "G");
        mapVal.put("8", "H");
        mapVal.put("9", "I");
        mapVal.put("10", "J");
        mapVal.put("11", "K");
        mapVal.put("12", "L");
        mapVal.put("13", "M");
        mapVal.put("14", "N");
        mapVal.put("15", "O");
        mapVal.put("16", "P");
        mapVal.put("17", "Q");
        mapVal.put("18", "R");
        mapVal.put("19", "S");
        mapVal.put("20", "T");
        mapVal.put("21", "U");
        mapVal.put("22", "V");
        mapVal.put("23", "W");
        mapVal.put("24", "X");
        mapVal.put("25", "Y");
        mapVal.put("26", "Z");
        return mapVal.get(str);
    }
}
