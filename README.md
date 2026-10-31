
# Loan & Mortgage Calculator

A Java console application for calculating loan payments, generating amortization schedules, simulating early repayments, and comparing loan options.

## Features

- Calculate monthly loan payments
- Calculate total repayment amounts and interest costs
- Generate amortization schedules
- Simulate early repayments and estimate interest savings
- Compare loan scenarios
- Display financial reports in the console
- Validate financial inputs

## Technologies

- Java 21
- Maven
- JUnit 5
- IntelliJ IDEA
- Git and GitHub

## Project Structure

```text
src/
├── main/java/com/finance/loan/
│   ├── Main.java
│   ├── Loan.java
│   ├── LoanCalculator.java
│   ├── AmortizationSchedule.java
│   ├── EarlyRepaymentCalculator.java
│   ├── LoanComparison.java
│   └── FinancialReport.java
└── test/java/com/finance/loan/
    ├── LoanCalculatorTest.java
    └── EarlyRepaymentCalculatorTest.java
```

## How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/Alin4393/LoanMortgageCalculator.git
   ```

2. Open the project in IntelliJ IDEA.
3. Use Java 21 as the project SDK.
4. Import the Maven project and allow dependencies to download.
5. Run `Main.java` to start the console application.

## Running Tests

Run the JUnit tests through IntelliJ IDEA using **Maven → Lifecycle → test**.

The project currently includes 12 automated unit tests covering loan calculations and early repayment behavior.

## Notes

- Financial calculations currently use Java `double` values and are intended for educational and demonstration purposes.
- Results are estimates and should not be used as professional financial advice.

## Author

GitHub: [Alin4393](https://github.com/Alin4393)
