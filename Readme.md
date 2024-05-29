# Atm Implementation

# Objective

## Problem Statement

- Write compiling code for "Cash Withdrawal" function, from an ATM which based on user specified amount,
  dispenses bank-notes.

Ensure that the code takes care of the following:

- Minimum number of bank-notes are used while dispensing the amount
- Availability of various denominations in the ATM machine is maintained
- Code should be flexible to take care of any bank denomination as long as it is multiple of 10
- Code should support parallel Cash Withdrawals i.e. two or more customer's should be able to withdraw money
- Takes care of exceptional situations
- Write appropriate unit test cases too
- State any assumptions made
- Write the compiling code using choice of your IDE (Eclipse, IntelliJ)
- Provide Unit Test Cases using JUNIT (if you are not conversant with JUNIT, just list down unit test cases)

## NFRs

- Duration of this exercise is 90 minutes. Please manage your time accordingly,
- Make any necessary assumption, and clearly state the assumptions made.
- Do not seek any help online or through any other source.

## Evaluation Criteria

- **Code Completeness/ Correctness**
- **Code Structure**: Modularity, usage of 00Ps principles and design patterns, size of classes, and functions, n-path
  complexity of functions.
- **Code Quality**: class/function/variable naming conventions, package/class structure, log messages - please do not
  provide comments unless necessary
- **Choice of data structures**
- **Efficiency of code** (leverage multi-threading wherever it makes sense)
- **Code test Cases and follow TDD** (if know)

# Solution

> Code starts from Main class which initialises and calls the ATM class
> Main class calls withdrawCash with desired amount and transaction id as input
> Amount is validated before the actual cash withdrawal logic
> Program calculates the balance after the transaction is completed
> 
> ### Assumptions:

1. Denominations in the ATM should be multiples of 100.
3. Amount to be withdrawn should be multiple of 100
4. Test case for withdrawing using multiple threads is included in the test class.

### Tested with JDK 21 and Intellij IDEA
- Clone the repository
- Resolve maven dependencies
- Run `mvn clean` and if this was success
- Now Run the app with `mvn exec:java` and check console for results
- if there are issues with the above commands, please run it directly from the main class