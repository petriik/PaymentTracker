PaymentTracker application
PaymentTracker is a simple command line application, which processes given payments and prints the current state of the account for each currency.

Command for running:
mvn package
java -jar PaymentTracker-1.0-jar-with-dependencies.jar <file>

Parameters:
file .. File with payment records for initial loading (Optional parameter). Format of payments is the same as when added by command line (See usage below).

Usage:
Application accepts payments from the command line or from the initial file (see parameter file).
Supported format of payments is a currency and payment value separated by a space (e.g. "CZK 1000"). Each payment must be on the sepparted line.
Currency must consist of three upper case letters, the payment value can be any number.
If user inserts an invalid payment application will print an error message and will continue in payment processing.


