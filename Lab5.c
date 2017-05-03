#include <stdio.h>

int euclidGCD(int m, int n);

int main()
{
	int num1, num2, answer;
	printf("give me two numbers to find the greatest common divisor\n");
	scanf("%d%d", &num1, &num2);

	answer = euclidGCD(num1, num2);

	printf("The answer is: %d", answer);

return 0;
}



int euclidGCD(int m, int n)
{
	if(m % n == 0) //base case
		return n;  
	else
		return euclidGCD(n, m % n);   
}
