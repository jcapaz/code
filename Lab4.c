//---------------------------------------------------------------------
// Name: >>>1. Jeanne Capaz<<<
// 
//
//
// Purpose: This program reads a list of non-negative values and
//          computes and displays the following statistics: the total 
//          numbers of grades, the average, and the number of grades below the average.
//
// Input:   A list of non-negative values started by the number of 
//          grades to be read followed by the grades.
//          
// Output:  The total number of grades, average, standard deviation, 
//          and the number of grades below the average.
//---------------------------------------------------------------------

#include <stdio.h>


#define NUMSIZE 100


// 2. DO: Complete the following two prototypes 
//        Hint: see headers below.

float Average(const int grades[],  int size);
int BelowAverage(const int grades[], int size, float ave);


int main()
{
   int grades[NUMSIZE];
   float average, standarDeviation;
   int count, belowAverage;
   
   scanf("%d",&count);
   int i;
   for (i = 0; i < count; i ++){
       scanf("%d", &grades[i]);
   }       
   // 3. DO: Complete the call to Average to calculate the average of the grades
   //        and store it in variable average.
    
   average = Average(grades, count);  

                     
   // 4. DO: Complete the call to BelowAverage to calculate the number  
   //        of grades that are below average, assigning result to belowAverage.

   belowAverage = BelowAverage(grades, count, average);    

       // NOTE: DO NOT MAKE **ANY** OTHER CHANGES TO MAIN
      // (That is, make no changes except to call
      //  Average and BelowAverage.)
      
   printf("Total integers in the array %d: \n", count);
   printf("Average of all array integers %f    : \n", average);
   printf("Count of grades below average %d   : \n",belowAverage);
   return 0;
}


// 5. DO: Complete Average so it returns 0 if size is 0 and the average 
//        otherwise.  Hint: you will need to use a loop.


float Average(const int grades[],  int size)
{
	int i;
	float sum = 0.0;
	for(i = 0; i < size; i++)
	{
		sum += grades[i];
	}	
	return sum / size;
}       
         

// 6. DO: Complete the the body of this function.
//    The function is to count the number of values below
//    the average.
//    Don't count numbers which are equal to average.


int BelowAverage(const int grades[], int size, float ave)                        
{

	int i;
	int count = 0;

	for(i = 0; i < size; i++)
	{
		if(grades[i] < ave)
			count++;
	}
	return count;

}
