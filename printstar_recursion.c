#include<stdio.h>



void  PrintStars (/* in */  int   n);

int main()
{

int number;
scanf("%d",&number);

PrintStars(number);

return 0;
}







void  PrintStars (/* in */  int   n)	
//   Prints n asterisks, one to a line
//   Precondition:  n is assigned  
//   Postcondition: 
//       IF n > 0, call PrintSars
//       ELSE n stars have been written
{
    if  (n > 0)  // General case
    {	
        printf("%c\n", '*');
        PrintStars (n - 1);
    }
	  // Base case is empty else-clause	
}
