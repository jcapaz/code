/*
Program Name: CrystalLake.c
Programmer: Jeanne Capaz
Class: CS 3335A
HW:02
Problem: Crystal Lake
Date: 9/23/15
*/

/*
Using Manning's equation to solve this problem:
Flow = 1.486/roughness * (depth * width) * R (hydraulic radius)^(2/3) * slope^(1/2)
r = hydraulic radius = depth * width / 2.0 * depth + width
*/

#include <stdio.h>
#include <math.h>
int main()
{

  //constant variables
  int width = 15;
  int depth = 10;
  float slope = 0.0015;
  float roughness = 0.014;
  float flow = 1000;
  float r, area;
  float answer;

  area = depth * width;
  r = area / (2.0 * depth + width);

  //Main calculation
  answer = (1.486 / roughness) * area * pow(r, 0.6667) * pow(slope, 0.5);  
  printf("The correct answer is %f\n", answer);
    
  //calculating variables
  float error, difference, percent;
  float target = flow;
  float guessDepth, guessFlow; 

  //half channel depth calculation
	int halfDepth = 5;
	float halfAnswer;
	area = halfDepth * width;
  	r = area / (2.0 * halfDepth + width);

  	halfAnswer = (1.486 / roughness) * area * pow(r, 0.6667) * pow(slope, 0.5);  

	printf("At a depth of %d feet, the flow is %f cubic feet per second.\n", halfDepth, halfAnswer);

  //user input
	printf("\nEnter your initial guess for the channel depth when the flow is %.2f cubic feet per second.", flow);
	printf("\nEnter Depth guess: ");
	scanf("%f", &guessDepth);

	error = answer * 0.001;
	while(guessDepth != error) 
	{
		//Output calculation
		printf("Here are the calculations:\n");
       
       		float guessAnswer;
        	area = guessDepth * width;
        	r = area / (2.0 * guessDepth + width);

        	guessAnswer = (1.486 / roughness) * area * pow(r, 0.6667) * pow(slope, 0.5);
		difference = answer - guessAnswer;
		percent = (answer / guessAnswer) * 100 ; 
		printf("\nDepth: %f Flow: %f Target: %f cfs Difference: %f Error: %f percent\n", guessDepth, guessAnswer, target, difference, percent);
		printf("Guess again please!\n");
break;
	}
return 0;

}



