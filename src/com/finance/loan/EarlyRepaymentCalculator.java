
package com.finance.loan;

public class EarlyRepaymentCalculator {

    public static void simulateEarlyRepayment(
            Loan loan,
            int monthsPaid,
            double extraPayment
    ) {

        if (monthsPaid < 0 ||
                monthsPaid >= loan.getTermMonths()) {

            throw new IllegalArgumentException(
                    "Invalid number of months paid."
            );
        }

        if (extraPayment <= 0) {

            throw new IllegalArgumentException(
                    "Extra payment must be greater than zero."
            );
        }

        double monthlyPayment =
                LoanCalculator.calculateMonthlyPayment(loan);

        double monthlyRate =
                loan.getMonthlyInterestRate();

        double balance = loan.getPrincipal();

        for (int month = 1; month <= monthsPaid; month++) {

            double interest = balance * monthlyRate;

            double principalPayment =
                    monthlyPayment - interest;

            balance -= principalPayment;

            if (balance <= 0) {
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

        balance -= extraPayment;

        if (balance < 0) {
            balance = 0;
        }

        double interestBeforeExtraPayment = 0;

        double interestAfterExtraPayment = 0;

        double originalBalance = originalRemainingBalance;

        for (int month = monthsPaid + 1;
             month <= loan.getTermMonths();
             month++) {

            double interest = originalBalance * monthlyRate;

            double principalPayment = monthlyPayment - interest;

            if (principalPayment > originalBalance) {
                principalPayment = originalBalance;
            }

            originalBalance -= principalPayment;

            interestBeforeExtraPayment += interest;

            if (originalBalance <= 0) {
                break;
            }
        }

        int newMonths = 0;

        while (balance > 0 && newMonths < 1000) {

            double interest = balance * monthlyRate;

            double principalPayment = monthlyPayment - interest;

            if (principalPayment > balance) {
                principalPayment = balance;
            }

            balance -= principalPayment;

            interestAfterExtraPayment += interest;

            newMonths++;

            if (balance < 0.000001) {
                balance = 0;
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
                extraPayment
        );

        System.out.printf(
                "New balance: %.2f RON%n",
                Math.max(0, originalRemainingBalance - extraPayment)
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
