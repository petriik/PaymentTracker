## PaymentTracker

PaymentTracker is a simple command line application, which is processing given payments and prints sums of amounts for each currency.

#### Commands for running:
```
mvn package
java -jar PaymentTracker-1.0-jar-with-dependencies.jar <file>
```
#### Parameters:
* file .. A file with payments for initial loading (Optional parameter).

#### Usage:
Application accepts payments from the initial file (see parameter file) and from the command line.

#### Supported input format
Supported format of payments is:
```
CCC PPPPP  
```
where
* CCC .. Payment currency (exactly three upper case letters)
* PPPPP ..Payment amount (Any number)  

A currency and a value must be separated by a space. Each payment must be on the new line.

#### Example input:
CZK 500  
USD -450  
CZK -50  
USD 300  
EUR 1000

#### Example output:
CZK 450  
USD -150  
EUR 1000

#### Notes:
* Invalid payments are ignored (An error message will be printed and application will continue in payment processing.
* Application is not printing results exactly in one-minute intervals. Application tries to reduce number of read/write conflicts.

##### How reducing of conflicts works:
1. Printing of results is disabled in first 50 seconds after last printing.
2. After that there is a 20 second interval in which printing is enabled only right after user input.
If user added a payment within this interval, the result will be printed without disturbing him.
3. If user doesn't write a payment in this interval, result will be printed at the end of this interval regardless user input.
