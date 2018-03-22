PaymentTracker application
PaymentTracker is a simple command line application, which is processing given payments and prints a sum of amounts for each currency .

Commands for running:
mvn package
java -jar PaymentTracker-1.0-jar-with-dependencies.jar <file>

Parameters:
file .. File with payment records for initial loading (Optional parameter). Format of payments is the same as when added by command line (See usage below).

Usage:
Application accepts payments from the initial file (see parameter file) and from the command line.
Supported format of payments is a currency and payment value separated by a space (e.g. "CZK 1000"). Each payment must be on the sepparted line.
Currency must consist of three upper case letters, the payment value can be any number.
If user inserts an invalid payment application will print an error message and will continue in payment processing.

Example input:
CZK 500
USD -450
CZK -50
USD 300
EUR 1000


Example output:
CZK 450
USD -150
EUR 1000


Note:
Application is not printing results exactly in one-minute intervals. Application tries to reduce number of read/write conflicts.

How reducing of conflicts work:
(1) Printing of results is disabled in first 50 seconds after last printing.
(2) After that there is a 20 second interval in which printing is enabled only right after user input.
If user added a payment within this interval, the result will be printed without disturbing him.
(3) If user doesn't write a payment in this interval, result will be printed at the end of this iterval regardless user input.
