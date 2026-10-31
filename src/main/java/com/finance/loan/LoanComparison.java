
package com.finance.loan;

public class LoanComparison {

    public static void compareLoans(
            Loan loan1,
            Loan loan2
    ) {

        double payment1 =
                LoanCalculator.calculateMonthlyPayment(loan1);

        double payment2 =
                LoanCalculator.calculateMonthlyPayment(loan2);

        double total1 =
                LoanCalculator.calculateTotalPayment(loan1);

        double total2 =
                LoanCalculator.calculateTotalPayment(loan2);

        double interest1 =
                LoanCalculator.calculateTotalInterest(loan1);

        double interest2 =
                LoanCalculator.calculateTotalInterest(loan2);

        System.out.println();

        System.out.println("===== LOAN COMPARISON =====");

        System.out.println();

        System.out.println("LOAN 1");

        System.out.printf(
                "Monthly payment: %.2f RON%n",
                payment1
        );

        System.out.printf(
                "Total interest: %.2f RON%n",
                interest1
        );

        System.out.printf(
                "Total repayment: %.2f RON%n",
                total1
        );

        System.out.println();

        System.out.println("LOAN 2");

        System.out.printf(
                "Monthly payment: %.2f RON%n",
                payment2
        );

        System.out.printf(
                "Total interest: %.2f RON%n",
                interest2
        );

        System.out.printf(
                "Total repayment: %.2f RON%n",
                total2
        );

        System.out.println();

        double difference = Math.abs(total1 - total2);

        System.out.printf(
                "Total repayment difference: %.2f RON%n",
                difference
        );

        if (total1 < total2) {

            System.out.println(
                    "Loan 1 has the lower total repayment."
            );

        } else if (total2 < total1) {

            System.out.println(
                    "Loan 2 has the lower total repayment."
            );

        } else {

            System.out.println(
                    "Both loans have the same total repayment."
            );
        }

        System.out.println("==========================");
    }
}
