
package com.finance.loan;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Loan currentLoan = null;

        boolean running = true;

        while (running) {

            printMenu();

            int option = readInt("Choose an option: ");

            try {

                switch (option) {

                    case 1:

                        currentLoan = createLoan();

                        LoanCalculator.printLoanSummary(currentLoan);

                        break;

                    case 2:

                        if (currentLoan == null) {

                            System.out.println(
                                    "Please calculate a loan first."
                            );

                        } else {

                            AmortizationSchedule.printSchedule(
                                    currentLoan
                            );
                        }

                        break;

                    case 3:

                        if (currentLoan == null) {

                            System.out.println(
                                    "Please calculate a loan first."
                            );

                        } else {

                            simulateEarlyRepayment(currentLoan);
                        }

                        break;

                    case 4:

                        compareLoans();

                        break;

                    case 5:

                        if (currentLoan == null) {

                            System.out.println(
                                    "Please calculate a loan first."
                            );

                        } else {

                            FinancialReport.generateReport(
                                    currentLoan
                            );
                        }

                        break;

                    case 0:

                        running = false;

                        System.out.println(
                                "Thank you for using Loan Calculator!"
                        );

                        break;

                    default:

                        System.out.println(
                                "Invalid option. Please try again."
                        );
                }

            } catch (IllegalArgumentException e) {

                System.out.println(
                        "Error: " + e.getMessage()
                );

            } catch (Exception e) {

                System.out.println(
                        "Unexpected error: " + e.getMessage()
                );
            }
        }

        scanner.close();
    }

    private static void printMenu() {

        System.out.println();

        System.out.println(
                "========================================"
        );

        System.out.println(
                "       LOAN & MORTGAGE CALCULATOR"
        );

        System.out.println(
                "========================================"
        );

        System.out.println(
                "1. Calculate loan"
        );

        System.out.println(
                "2. View amortization schedule"
        );

        System.out.println(
                "3. Early repayment simulation"
        );

        System.out.println(
                "4. Compare loans"
        );

        System.out.println(
                "5. Financial summary"
        );

        System.out.println(
                "0. Exit"
        );

        System.out.println(
                "========================================"
        );
    }

    private static Loan createLoan() {

        System.out.println();

        System.out.println(
                "===== CREATE LOAN ====="
        );

        double principal = readDouble(
                "Loan amount (RON): "
        );

        double interestRate = readDouble(
                "Annual interest rate (%): "
        );

        int years = readInt(
                "Loan term (years): "
        );

        if (years <= 0 || years > 100) {

            throw new IllegalArgumentException(
                    "Loan term must be between 1 and 100 years."
            );
        }

        int months = years * 12;

        return new Loan(
                principal,
                interestRate,
                months
        );
    }

    private static void simulateEarlyRepayment(Loan loan) {

        System.out.println();

        System.out.println(
                "===== EARLY REPAYMENT ====="
        );

        int monthsPaid = readInt(
                "Number of months already paid: "
        );

        double extraPayment = readDouble(
                "Extra repayment amount (RON): "
        );

        EarlyRepaymentCalculator.simulateEarlyRepayment(
                loan,
                monthsPaid,
                extraPayment
        );
    }

    private static void compareLoans() {

        System.out.println();

        System.out.println(
                "===== LOAN 1 ====="
        );

        Loan loan1 = createLoan();

        System.out.println();

        System.out.println(
                "===== LOAN 2 ====="
        );

        Loan loan2 = createLoan();

        LoanComparison.compareLoans(
                loan1,
                loan2
        );
    }

    private static int readInt(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine();

            try {

                return Integer.parseInt(input.trim());

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid whole number."
                );
            }
        }
    }

    private static double readDouble(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine();

            input = input.trim().replace(',', '.');

            try {

                double value = Double.parseDouble(input);

                if (!Double.isFinite(value)) {

                    System.out.println(
                            "Please enter a finite number."
                    );

                    continue;
                }

                return value;

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }
}
