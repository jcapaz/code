#include<stdio.h>

void selection(int ary[], int size);
void printary(int ary[], int size);


int main()
{
  int ary[] = {42, 101, 2, 16, 7, 8, 9, 2};

	printf("the unsorted array is \n");
	printary(ary,8);
        printf("\n");
	selection(ary, 8);
	 printf("the sorted array is \n");
	printary(ary,8);
return 0;
}


void selection(int ary[], int size)
{
  int pass, count;
  for(pass = 0; pass < size -1; pass++)
    {
	int minIndex = pass;
	for(count = pass + 1; count < size; count++)
	    if(ary[count] < ary[minIndex])
		minIndex = count;
	//swap
             int temp = ary[minIndex];
	     ary[minIndex] = ary[pass];
             ary[pass] = temp;
    }
}

void printary(int ary[], int size)
{
 int i;
 for(i = 0; i < size; i++)
   printf("%d\n", ary[i]);
}

