package wk3_ct;
import java.util.Scanner;

public class GroceryCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1) Expect coupon as integer.
        int couponPercent = getCouponPercent(scanner);
        double coupon = couponPercent / 100.0;

        // 2) Weekly bills expected as numeric values and >= 0.
        double[] weeklyBills = new double[4];
        for (int i = 0; i < 4; i++) {
            weeklyBills[i] = weeklyBillCheck(
                scanner,
                "Enter grocery bill for week " + (i + 1) + ": "
            );
        }

        // 3) Calculations
        double monthlyTotal = 0;
        for (double bill : weeklyBills) monthlyTotal += bill;
        double weeklyAverage = monthlyTotal / 4.0;

        double monthlyWithCoupon = monthlyTotal * (1 - coupon);
        double weeklyWithCoupon = monthlyWithCoupon / 4.0;

        // 4) Display values
        System.out.println("\n=== Grocery Bill Summary ===");
        System.out.printf("Monthly Total (without using coupon):   $%.2f%n", monthlyTotal);
        System.out.printf("Weekly  Average (without using coupon): $%.2f%n", weeklyAverage);
        System.out.printf("Monthly Total (if using %d%% off coupon):    $%.2f%n", couponPercent, monthlyWithCoupon);
        System.out.printf("Weekly  Average (if using %d%% off coupon):  $%.2f%n", couponPercent, weeklyWithCoupon);

        scanner.close();
    }

    /**
     * Prompt user for coupon integer. "20" means 20% off.)
     */
    private static int getCouponPercent(Scanner scanner) {
        while (true) {
            System.out.print("Enter the coupon you have for this month: ");
            String line = scanner.nextLine().trim();

            try {
                int couponValue = Integer.parseInt(line);
                if (couponValue >= 1 && couponValue <= 100) {
                    return couponValue;
                } 
                else {
                    System.out.println("Sorry, it seems like you entered an invalid coupon value, please enter your coupon value (between 1 and 100).");
                }
            }
            catch (NumberFormatException ex) {
                System.out.println("Sorry. it seems like you entered an invalid coupon value. Please try again.");
            }
        }
    }

    /**
     * Prompt until the user enters a numeric value >= 0.
     */
    private static double weeklyBillCheck(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();

            try {
                double weekValue = Double.parseDouble(line);
                if (weekValue >= 0) {
                    return weekValue;
                } else {
                    System.out.println("Please enter a weekly value of zero or more. Please try again.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Sorry, that doesn't appear to be a valid weekly amount, please try again.");
            }
        }
    }
}
