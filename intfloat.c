#include<stdio.h>

int main()
{
 int number1;
 int number2;
 int intQ;
 float floatQ;
 int intR;

 printf("input the first integer\n ");
 scanf("%d", &number1);

 printf("input the second integer\n ");
 scanf("%d", &number2);

 intQ = number1 / number2;
 floatQ = (float) number1 / number2;
 intR = number1 % number2;

 printf("integer quotient is %d / %d = %d \n", number1, number2, intQ);
 printf("float quotient is %d / %d = %f \n", number1, number2, floatQ);
 printf("integer remainder is %d \045% %d = %d \n", number1, number2, intR);

return 0;
}




