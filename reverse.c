#include<stdio.h>

int main()
{ int num = 0, rev = 0;
printf("\nEnter a number : \t"); 
scanf("%d", &num);
while(num)
{ rev *= 10;
 rev +=  num % 10;
 num /= 10; }
printf("\n\n Reverse = %d \n\n", rev); 
return 0;
}

