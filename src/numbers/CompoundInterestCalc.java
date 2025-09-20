package numbers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class CompoundInterestCalc {
    // Any variable that is going to be a field should be made final as much as possible
    private static final NumberFormat MONEY_FORMATTER = NumberFormat.getCurrencyInstance(Locale.UK);
    private static final NumberFormat PERCENT_FORMATTER = NumberFormat.getCompactNumberInstance() .getPercentInstance();

    /**
     *
     * @param principal
     * @param rate
     * @param period
     * @param contribution
     * @return g
     */
    public static BigDecimal calculate (String principal, String rate, int period, String contribution) throws ParseException {
        String rateAsPercent = PERCENT_FORMATTER.parse(rate).toString();

        // Balance(Y) = p(1 + r)^Y + c[ ((1 + r)^Y -1 ) / r]
        BigDecimal a = BigDecimal.ONE.add(new BigDecimal(rateAsPercent)); // (1 + r)
        BigDecimal b = a.pow(period); // (1 + r)^Y NB: int is used for period bc it does not require any precision
        BigDecimal c = b.subtract(BigDecimal.ONE); // (1 + r)^Y -1 )
        BigDecimal d = c.divide(new BigDecimal(rateAsPercent)); // ((1 + r)^Y -1 ) / r
        BigDecimal e = d.multiply(new BigDecimal((MONEY_FORMATTER.parse(contribution).toString()))); // c[ ((1 + r)^Y -1 ) / r]
        BigDecimal f = b.multiply(new BigDecimal(MONEY_FORMATTER.parse(principal).toString())); // p(1 + r)^Y
        BigDecimal g = f.add(e);

        return g;
    }

    public static void main(String[] args) throws Exception {
        // after the semicolon represents everything positive number, while the negative number is rep by after semicolon
        DecimalFormat df = new DecimalFormat("N0,000.00;N(#)");
        DecimalFormat pf = new DecimalFormat("#%");
        BigDecimal balance = CompoundInterestCalc.calculate("£10,000", "18.5%", 10, "£1,000");
        System.out.println(MONEY_FORMATTER.format(balance));

        // Using Decimal Format object
        System.out.println(df.format(balance));
        // This is a negative number of the balance
        System.out.println(df.format(balance.negate()));
        System.out.println(pf.format(.08));

        /*
         Additional Ways to format Numbers,
         When using printf -- %n specifies to print a new line
         the %.2f - floating number with 2 significant figure after the period
         the comma, is a flag that indicates we need to add a  comma separated number
         the parenthesis is also a flag that tells the format string how to handle negative numbers and puts in a parenthesis
         Highlighting a method and pressing F1 shows the javadoc for that method.
         System.out.printf(), String.format() and System.out.format() are the same methods
        */
        System.out.printf("£%,.2f %n", balance);
        System.out.printf("£%,(.2f %n", balance.negate());
        // System.out.format("£%,(.2f %n", balance);
        String myMoney = String.format("£%,(.2f %n", 389900000.59944);
        System.out.println(myMoney);
    }
}

