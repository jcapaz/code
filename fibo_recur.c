#include<stdio.h>


int fibonacci(int num);

int main()
{

int n, fib;

scanf("%d",&n);

fib = fibonacci(n);

printf("%d",fib);

}

int fibonacci(int num)
{

if (num == 1 || num == 2)
return 1;
else
return (fibonacci(num-1) + fibonacci(num-2));

}
