#include <stdio.h>
#include<stdbool.h>

bool isLeapYear(int year);

int main()
{
   int year;
   bool result;
   
   printf("Enter a four digit year: \n"); 
   scanf("%d",&year);
   
   result = isLeapYear(year);
   if (result==true)
   {
      printf("Leap Year \n");
   }
   else
   {
      printf("Not a leap year!\n");
   }
   
   return 0;
}

bool isLeapYear(int year)
{
   
   // determine whether year is a leap year as follows:
   //    if year is multiple of 400, then yes
   //    otherwise if year is multiple of 4 BUT NOT multiple of 100, then yes
   //    finally, no
   if ((year % 400) == 0)
   {
      return true;
   }
   else if ((year % 4) == 0 && (year % 100) != 0)
   {
      return true;
   }
   else
   {
      return false;
   }
}

