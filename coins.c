/*
* Gets three input characters which are user's initials and displays
* them in a welcoming message. Then gets input of the quantities of
* each of the following coins, in the respective order, quarters,
* dimes, nickels, and pennies. Computes the total value of the
* coins, and then displays the value. Input is taken from a file
* provided through input redirection. Output can be redirected to
* a file if desired.
*/

#include <stdio.h>

int main(void)
{
   char first, middle, last; /* input - 3 initials */
   int pennies, nickels; /* input - count of each coin type */
   int dimes, quarters; /* input - count of each coin type */
   int change; /* output - change amount */
   int dollars; /* output - dollar amount */
   int total_cents; /* total cents */

/* Get and display the customer's initials. */
      scanf("%c%c%c", &first, &middle, &last);
      printf("\nHello %c%c%c, let's see what your coins are worth.\n",first, middle, last);

/* Get the count of each kind of coin. */
      scanf("%d", &quarters);
      printf("Number of quarters is %1d.\n", quarters);
      scanf("%d", &dimes);
      printf("Number of dimes is %1d.\n", dimes);
      scanf("%d", &nickels);
      printf("Number of nickels is %1d.\n", nickels);

      scanf("%d", &pennies);
      printf("Number of pennies is %1d.\n", pennies);

/* Compute the total value in cents. */

      total_cents = 25 * quarters + 10 * dimes + 5 * nickels + pennies;

/* Find the value in dollars and change. */
      dollars = total_cents / 100;
      change = total_cents % 100;

/* Display the value in dollars and change. */
      printf("\nYour coins are worth %d dollars and %d cents.\n", dollars, change);

return 0;
}
