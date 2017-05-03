#include<stdio.h>

int main()
{
	float newnum;
	float small;
	float large;
	float avg = 0.0;
	float sum = 0.0;
	int count = 1;
	float negative = 0.0, positive = 0.0;

printf("enter a number # %d \t", count);
scanf("%f", &newnum);

small = newnum;
large = newnum;

while( count <= 10)
{
  if(newnum > large)
	large = newnum;
  if(newnum < small)
	small = newnum;
  if (newnum > 0)
	positive++;
  else if (newnum < 0)
	negative++;
   count++;
 
if (count < 11)
{
  printf("enter a number # %d \t", count);
  scanf("%f", &newnum);
}
}


printf("the number of negative values is %f \n", negative);
printf("the number of positive values is %f \n", positive);
printf("the largest values is %f \n", large);
printf("the smallest values is %f \n", small);
if(large < 0)
	printf("all are negative");
else if(small > 0)
	printf("all are positive");
else
	printf("mixed");
return 0;
}

	
