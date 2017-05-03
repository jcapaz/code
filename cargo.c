/*Program Name: Cargo
Programmer: Jeanne Capaz
Class: CS 3335A
HW: 03
Problem: 01
Date: 10/8/15
*/

#include<stdio.h>

#include<math.h>

#define GRAVITY 442.2f
#define SPEED 16
#define NUMBER_OF_TRIES 5
#define MIN_SPEED 14
#define MAX_SPEED 15 

//functions that are required:

float GetCargo()
//this function will return a positive number of ounces of cargo
{
	float weight;

        printf("Enter the weight of cargo in ounces, please: ");
        scanf("%f", &weight);
 
	if(weight <= 0.0)
	{
		printf("Enter a positive number for the weight of cargo in ounces, please: ");
		scanf("%f", &weight);
	}
	return weight;
}

float GetChuteSize(int count)
//this function will return a positive number for the chute size (square feet)
//the parameter passed to the function is the "try number". It will be between 1-5
{
	float size; 
	count = 1;

        printf("\nEnter the parachute size in square feet, please: ");
        scanf("%f", &size);

	if(size <= 0.0)
	{
		printf("\nEnter a positive number for the parachute size in square feet, please: ");
		scanf("%f", &size);
	}
   
	return size;
}

float ComputeFPS(float weight, float size)
//this function will compute and display the rate of descent. The speed is returned.
//the formula is fps = (442.2 * sqrt(X/16)) / Y
//X = the weight of the cargo in ounces
//Y = the size of the chute in square feet
{
	float fps;
   
	fps = (GRAVITY * sqrt(weight / SPEED)) / size;
   
	return fps;
}

int main()
{
	float weight, size, fps; 
  	int count = 1;

	printf("You get 5 chances to land your cargo safely. Good Luck!\n");
	
	weight = GetCargo();     

        while(count <= NUMBER_OF_TRIES)
    	{       
	        
		printf("Try: %d", count); 
	
		size = GetChuteSize(count);
   
		fps = ComputeFPS(weight, size);   
		printf("\nFalling at %.3f\n", fps);

		if(fps <= MIN_SPEED)
		{
			printf("\nDRIFTING AWAY! The parachute is too big. Please try again\n");
		}
		else if(fps > MAX_SPEED)
		{
			printf("\nSPLAT! We're going to need a bigger chute. Please try again.\n");
		}
		else
		{
			printf("\nNICE LANDING!!! You won the game!\n");
			break;
		}
   	count++;
	}
	
	return 0;
}
