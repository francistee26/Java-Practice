import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalculator {
    private static final byte MONTHS_IN_YEAR = 12;
    private static final byte PERCENT = 100;

    private static float monthlyInterest;
    private static short numberOfPayments;

    private static int numberOfPaymentsMade = 1;

    public static void main(String[] args) {
        final int principalLowerLimit = 1000;
        final int principalUpperLimit = 1_000_000;

        final int lowerLimiter = 1;
        final int upperLimiter = 30;

        int principal = (int) readNumber("Principal: ", principalLowerLimit, principalUpperLimit);
        float annualInterest = (float) readNumber("Annual Interest: ", lowerLimiter, upperLimiter);
        byte years = (byte) readNumber("Period (Years): ", lowerLimiter, upperLimiter);

        monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
        numberOfPayments = (short) (years * MONTHS_IN_YEAR);

        // returned value of mortgage assigned to mortgage variable
        double mortgage = calculateMortgage(principal, annualInterest, years);

        // formart mortgage to currency
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("\nMORTGAGE\n--------\nMonthly Payments: " + mortgageFormatted + "\n");

        System.out.println("PAYMENT SCHEDULE\n----------------");

        while (numberOfPaymentsMade <= numberOfPayments) {
            double balance = calculateRemainingMortgage(principal, annualInterest, years);
            
            // formart balance mortgage to currency
            String balanceMortgageFormatted = NumberFormat.getCurrencyInstance().format(balance);
            System.out.println(balanceMortgageFormatted);
        }

    }

    // reads in prompt
    public static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between" + min + " and " + max);
            scanner.close();
        }
        return value;
    }

    // Calculate mortgage
    public static double calculateMortgage(int principal, float annualInterest, byte years) {

        // calculates the mortgage 
        double mortgage = ((principal) * ((monthlyInterest * Math.pow((1 + monthlyInterest), (numberOfPayments))))
                / ((Math.pow((1 + monthlyInterest), (numberOfPayments))) - 1));

        return mortgage;
    }

    public static double calculateRemainingMortgage(int principal, float annualInterest, byte years) {

        // calculates the mortgage balance
        double balance = ((principal) * (Math.pow((1 + monthlyInterest), numberOfPayments)
                - Math.pow((1 + monthlyInterest), numberOfPaymentsMade)))
                / (Math.pow((1 + monthlyInterest), numberOfPayments) - 1);

        numberOfPaymentsMade++;

        return balance;
    }

}