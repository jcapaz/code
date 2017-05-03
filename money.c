// This program is a universal currency converter.  Enter the amount of 
// US dollars to convert and the conversion factor for a given system
// The output is the value of the US dollars in that currency.

#include <stdio.h>


float GetAmount()
// Function will return a the dollar amount to convert.  It must
// be greater than 0
{
  float amt;
  
  printf("Enter US dollar amount to convert: ");
  scanf("%f", &amt);
  while ( amt <= 0 )
  {
   printf("Enter US dollar amount to convert: ");
  scanf("%f", &amt);
 
  }
  return amt;
}

void DisplayConvert ( float amt, float factor )
// Compute and display the currency conversion amount
{
   float ans;
   ans = amt * factor;
   printf("The US dollar amount %f converts to %f using the factor %f",amt, ans, factor); 

}

int main()
{
   float amount_to_convert, convert_factor;
   char ok;
   ok = 'p';
   while(ok != 'n')
   {
     amount_to_convert = GetAmount();
printf("\nEnter conversion factor: ");
scanf("%f",&convert_factor);
     DisplayConvert( amount_to_convert, convert_factor);
    printf("enter n to stop\n");
    scanf(" %c", &ok);
}
   return 0;
}
