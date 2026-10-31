
package com.finance.loan;

public class EarlyRepaymentCalculator {

    public static void simulateEarlyRepayment(
            Loan loan,
            int monthsPaid,
            double extraPayment
    ) {
        if (loan == null) {
            throw new IllegalArgumentException(
                    "Loan cannot be null."
            );
        }

        if (monthsPaid < 0 ||
                monthsPaid >= loan.getTermMonths()) {
            throw new IllegalArgumentException(
                    "Invalid number of months paid."
            );
        }

        if (!Double.isFinite(extraPayment) ||
                extraPayment <= 0) {
            throw new IllegalArgumentException(
                    "Extra payment must be a finite positive number."
            );
        }

        double monthlyPayment =
                LoanCalculator.calculateMonthlyPayment(loan);

        double monthlyRate =
                loan.getMonthlyInterestRate();

        double balance = loan.getPrincipal();

        // Calculate balance after payments already made
        for (int month = 1; month <= monthsPaid; month++) {
            double interest = balance * monthlyRate;
            double principalPayment =
                    Math.min(balance, monthlyPayment - interest);

            balance -= principalPayment;

            if (balance < 0.000001) {
                balance = 0;
                break;
            }
        }

        if (balance == 0) {
            System.out.println(
                    "The loan is already fully repaid."
            );
            return;
        }

        double originalRemainingBalance = balance;

        // Do not allow repayment to exceed remaining balance
        double actualExtraPayment =
                Math.min(extraPayment, originalRemainingBalance);

        double newBalance =
                originalRemainingBalance - actualExtraPayment;

        if (newBalance < 0.000001) {
            newBalance = 0;
        }

        // Interest without early repayment
        double interestBeforeExtraPayment = 0;
        double originalBalance = originalRemainingBalance;

        for (int month = monthsPaid + 1;
             month <= loan.getTermMonths() && originalBalance > 0;
             month++) {

            double interest = originalBalance * monthlyRate;
            double principalPayment =
                    Math.min(originalBalance, monthlyPayment - interest);

            originalBalance -= principalPayment;
            interestBeforeExtraPayment += interest;

            if (originalBalance < 0.000001) {
                originalBalance = 0;
            }
        }

        // Interest after early repayment
        double interestAfterExtraPayment = 0;
        int newMonths = 0;
        double remainingBalance = newBalance;

        int maximumMonths = loan.getTermMonths() - monthsPaid;

        while (remainingBalance > 0 &&
                newMonths < maximumMonths) {

            double interest = remainingBalance * monthlyRate;
            double principalPayment =
                    Math.min(remainingBalance, monthlyPayment - interest);

            remainingBalance -= principalPayment;
            interestAfterExtraPayment += interest;
            newMonths++;

            if (remainingBalance < 0.000001) {
                remainingBalance = 0;
            }
        }

        double interestSaved =
                interestBeforeExtraPayment -
                        interestAfterExtraPayment;

        System.out.println();
        System.out.println("===== EARLY REPAYMENT =====");

        System.out.printf(
                "Original remaining balance: %.2f RON%n",
                originalRemainingBalance
        );

        System.out.printf(
                "Extra payment: %.2f RON%n",
                actualExtraPayment
        );

        System.out.printf(
                "New balance: %.2f RON%n",
                newBalance
        );

        System.out.printf(
                "Remaining months after repayment: %d%n",
                newMonths
        );

        System.out.printf(
                "Interest before early repayment: %.2f RON%n",
                interestBeforeExtraPayment
        );

        System.out.printf(
                "Interest after early repayment: %.2f RON%n",
                interestAfterExtraPayment
        );

        System.out.printf(
                "Estimated interest saved: %.2f RON%n",
                interestSaved
        );

        System.out.println("==========================");
    }
}
