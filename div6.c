#include<stdio.h>

int main()
{
  int sum = 0;
  int i = 1;
 
  while(i <= 30)
  {
  	if(i % 6 != 0)
		 sum = sum + i;
        i++;
  }
printf("the sum is %d \n", sum);

return 0;
}
