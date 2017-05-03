#include<stdio.h>


void Print(const int data[], int first, int last);

int main()
{
int data[10];
int i;
for (i = 0; i < 10; i++)
{
data[i] = 2*i;
}

int first=0, last = 9;
Print(data, first, last);
}









void Print(const int data[], int first, int last)
{

if (first <= last)
{
printf("%d\n",data[first]);
Print(data, first + 1, last);

}
}
