
package com.finance.loan;

import java.util.List;

public class FinancialReport {

    public static void generateReport(Loan loan) {

        List<AmortizationSchedule.PaymentEntry> schedule =
                AmortizationSchedule.generateSchedule(loan);

        double totalPrincipal = 0;

        double totalInterest = 0;

        double totalPayments = 0;

        for (AmortizationSchedule.PaymentEntry entry : schedule) {

            totalPrincipal += entry.getPrincipal();

            totalInterest += entry.getInterest();

            totalPayments += entry.getPayment();
        }

        double interestPercentage =
                totalPayments == 0
                        ? 0
                        : totalInterest / totalPayments * 100;

        System.out.println();

        System.out.println("===== FINANCIAL REPORT =====");

        System.out.printf(
                "Original principal: %.2f RON%n",
                loan.getPrincipal()
        );

        System.out.printf(
                "Principal repaid: %.2f RON%n",
                totalPrincipal
        );

        System.out.printf(
                "Total interest: %.2f RON%n",
                totalInterest
        );

        System.out.printf(
                "Total payments: %.2f RON%n",
                totalPayments
        );

        System.out.printf(
                "Interest share of payments: %.2f%%%n",
                interestPercentage
        );

        System.out.printf(
                "Number of payments: %d%n",
                schedule.size()
        );

        System.out.println("==========================");
    }
}
