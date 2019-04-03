package workshop.utils;

import java.text.DecimalFormat;

public class Utils {

    /**
     * @param pattern           the desired pattern to format
     * @param minFractionDigits maximum number of fraction digits
     * @param maxFractionDigits minimum number of fraction digits
     * @return DecimalFormat object created with desired format
     */
    public static DecimalFormat getDecimalFormat(String pattern, int minFractionDigits, int maxFractionDigits) {
        DecimalFormat df = new DecimalFormat(pattern);
        df.setMaximumFractionDigits(maxFractionDigits);
        df.setMinimumFractionDigits(minFractionDigits);
        return df;
    }
}
