#include<stdio.h>

int Minimum( int first, int second, int third);
int Square(int count);

int main()
{
int num1, num2, num3, min, square;

scanf("%d%d%d", &num1,&num2,&num3);

min = Minimum (num1, num2, num3);
square = Square(min);

printf("%d \t %d", min, square);

}

int Minimum( int first, int second, int third)
{
if (first <= second && first <= third)
return first;
else if (second <= third)
return second;
else return third;
}

int Square(int count)
{
return count * count;
}


