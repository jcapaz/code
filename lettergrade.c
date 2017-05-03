// Program to determine letter grade. Three scores are entered
// and the average computed. If the average is greater than or equal to
// 90, the grade is an A, if the average is greater than or equal to
// 80 and less than 90, the grade is a B.  If the average is greater 
// than or equal to 70 and less than 80, the grade is a C.  If the 
// average is greater than or equal to 60 and less than 70, then the
// grade is a D.  Otherwise the grades is an F.
//
#include <stdio.h> 


#define ASCORE 90
#define BSCORE 80
#define CSCORE 70
#define DSCORE 60

int main ()
{
   float score1, score2, score3, average;

   printf( "Enter the three scores - separated by a space\n");
   scanf("%f%f%f", &score1, &score2, &score3);
   
   average = ( score1 + score2 + score3 ) / 3.0;
   
   if ( average < DSCORE )
      printf("Grade is an F");
   else if ( average < CSCORE )
      printf("Grade is a D");
   else if ( average < BSCORE )
      printf("Grade is a C");
   else if ( average < ASCORE )
      printf("Grade is a B");
   else
      printf( "Grade is an A");
return 0;
}

