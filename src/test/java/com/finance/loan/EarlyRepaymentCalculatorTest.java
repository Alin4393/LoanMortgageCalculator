
package com.finance.loan;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EarlyRepaymentCalculatorTest {

    @Test
    void shouldRejectNegativeMonthsPaid() {
        Loan loan = new Loan(100000, 5, 60);

        assertThrows(IllegalArgumentException.class,
                () -> EarlyRepaymentCalculator.simulateEarlyRepayment(
                        loan, -1, 1000));
    }

    @Test
    void shouldRejectZeroExtraPayment() {
        Loan loan = new Loan(100000, 5, 60);

        assertThrows(IllegalArgumentException.class,
                () -> EarlyRepaymentCalculator.simulateEarlyRepayment(
                        loan, 12, 0));
    }

    @Test
    void shouldRejectNaNExtraPayment() {
        Loan loan = new Loan(100000, 5, 60);

        assertThrows(IllegalArgumentException.class,
                () -> EarlyRepaymentCalculator.simulateEarlyRepayment(
                        loan, 12, Double.NaN));
    }

    @Test
    void shouldRejectInfiniteExtraPayment() {
        Loan loan = new Loan(100000, 5, 60);

        assertThrows(IllegalArgumentException.class,
                () -> EarlyRepaymentCalculator.simulateEarlyRepayment(
                        loan, 12, Double.POSITIVE_INFINITY));
    }

    @Test
    void shouldCloseLoanWhenExtraPaymentExceedsBalance() {
        Loan loan = new Loan(12000, 0, 12);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(output));

            EarlyRepaymentCalculator.simulateEarlyRepayment(
                    loan, 6, 10000
            );
        } finally {
            System.setOut(originalOut);
        }

        String result = output.toString();

        assertTrue(
                result.contains("Extra payment: 6000.00 RON")
        );

        assertTrue(
                result.contains("New balance: 0.00 RON")
        );

        assertTrue(
                result.contains("Remaining months after repayment: 0")
        );
    }

    @Test
    void shouldCalculateInterestSavingsWithPositiveInterestRate() {
        Loan loan = new Loan(1200, 12, 2);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(output));

            EarlyRepaymentCalculator.simulateEarlyRepayment(
                    loan, 0, 600
            );
        } finally {
            System.setOut(originalOut);
        }

        String result = output.toString();

        assertTrue(
                result.contains("New balance: 600.00 RON")
        );

        assertTrue(
                result.contains("Remaining months after repayment: 1")
        );

        assertTrue(
                result.contains("Interest after early repayment: 6.00 RON")
        );

        assertTrue(
                result.contains("Estimated interest saved: 12.03 RON")
        );
    }
}
