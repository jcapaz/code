#include<stdio.h>


int factorial (int n);
int main()
{

int num, fact;
scanf("%d", &num);
fact = factorial(num);
printf("%d",fact);
return 0;
}

int factorial (int n)
{
if(n==0)
return 1;
else
return n*factorial(n-1);
}


