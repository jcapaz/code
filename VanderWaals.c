/*
Program Name: VanderWaals.c
Programmer: Jeanne Capaz
Class: CS 3335A
HW:02
Problem: Van der Waals
Date: 9/23/15
*/

#include<stdio.h>

#define A 3.592f
#define B 0.0427f
#define R 0.08206f

/*
This program will use the Van der Waals equation of state for a gas
(P + (n*n*a)/(V*V) (V - bn) = nRT
P = pressure in atmospheres
V = volume in liters
a = 3.592L(squared)atm/mol(squared)
b = 0.0427L/mol
R = 0.08206L atm/mol K

inputs = n, Kelvin temp, initial_vol, final_vol, vol_increment
*/

int main()
{
   
   float volume;
   float pressure;  
   
   //variables needed for input  
   float n;
   float kTemp;
   float initial_vol;
   float final_vol;
   int vol_increment;
   
     
   printf("Please enter at the prompts the number of moles of carbon dioxide, the absolute temperature, the initial volume in millileters, the final volume, and the increment volume between the lines of the table.\n\n\n");
   
   //user prompts      
   printf("Quantity of carbon dioxide (moles): ");
   scanf("%f", &n);
   
   printf("\nTemperature (kelvin): ");
   scanf("%f", &kTemp);
   
   printf("\nInitial volume (milliliters): ");
   scanf("%f", &initial_vol);
   
   printf("\nFinal Volume (milliliters): ");
   scanf("%f", &final_vol);
   
   printf("\nVolume increment (milliliters): ");
   scanf("%d", &vol_increment);

           printf("\nOUTPUT FILE\n\n");
           printf("%f moles of carbon dioxide at %.2f kelvin\n", n, kTemp);
           printf("Volume (ml)\t\t Pressure (atm)\n");

   
   //calculations
  
   float count_vol;

   for(count_vol = initial_vol; count_vol <= final_vol; count_vol+=vol_increment)
   {
	pressure = (n * R * kTemp) / ((count_vol*0.001) - B * n) - (n*n * A) /(count_vol * count_vol * 0.001);
	   //output table

	   printf("\n%.2f\t\t\t%f\n", count_vol, pressure);
   }   
   
 return 0;  
}
