/*Program Name: Program2.c
Programmer: Jeanne Capaz
Class: CS 3335A
HW:01
Problem: Cartesian plane
Date: 9/10/15
*/

#include <stdio.h>

int main()
{
  float x = 0.0;
  float y = 0.0;

  printf("give me a x-value please\n");
  scanf("%f", &x);
  printf("give me a y-value please\n");
  scanf("%f", &y);
  
  if(x == 0.0)
	printf("(%.1f, %.1f) is on the x-axis\n", x, y);

  	else if(y == 0.0)
		printf("(%.1f, %.1f) is on the y-axis\n", x, y);	
	else if(x == 0.0 && y == 0.0)
		printf("(%.1f, %.1f) is on the origin\n", x, y);
  	else if(x > 0.0 && y > 0.0)
		printf("(%.1f, %.1f) is in quadrant I\n", x, y);
  	else if(x < 0.0 && y > 0.0)
		printf("(%.1f, %.1f) is in quadrant II\n", x, y);
  	else if(x < 0.0 && y < 0.0)
		printf("(%.1f, %.1f) is in quadrant III\n", x, y);
  else
		printf("(%.1f, %.1f) is quadrant IV\n", x, y);
 return 0;
} 


