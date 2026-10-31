
package com.finance.loan;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanCalculatorTest {

    @Test
    void shouldCalculateMonthlyPaymentWithoutInterest() {
        Loan loan = new Loan(12000, 0, 12);

        double result = LoanCalculator.calculateMonthlyPayment(loan);

        assertEquals(1000.0, result, 0.001);
    }

    @Test
    void shouldCalculateMonthlyPaymentWithInterest() {
        Loan loan = new Loan(100000, 12, 12);

        double result = LoanCalculator.calculateMonthlyPayment(loan);

        assertEquals(8884.88, result, 0.01);
    }

    @Test
    void shouldCalculateTotalPaymentWithoutInterest() {
        Loan loan = new Loan(12000, 0, 12);

        double result = LoanCalculator.calculateTotalPayment(loan);

        assertEquals(12000.0, result, 0.001);
    }

    @Test
    void shouldCalculateTotalInterestWithoutInterestRate() {
        Loan loan = new Loan(12000, 0, 12);

        double result = LoanCalculator.calculateTotalInterest(loan);

        assertEquals(0.0, result, 0.001);
    }

    @Test
    void shouldRejectNegativeLoanAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new Loan(-1000, 5, 12));
    }

    @Test
    void shouldRejectZeroLoanTerm() {
        assertThrows(IllegalArgumentException.class,
                () -> new Loan(10000, 5, 0));
    }
}
