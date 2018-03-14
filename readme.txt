PaymentTracker application

Command for rurning:
java -jar PaymentTracker-1.0-jar-with-dependencies.jar <file>

Parameters:
file.. File with payment record for initial loading (Optional parameter)

Usage:
Supported format of payments is a currency and payment value separated by a space (e.g. "CZK 1000"). Currency must consist of three upper case letters, the payment value can be any number.
If user inserts an invalid payment input application will print an error message and will continue in payment processing.


