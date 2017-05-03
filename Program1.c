/*Program Name: Program1.c
Programmer: Jeanne Capaz
Class: CS 3335A
HW:01
Problem: Earthquake
Date: 9/10/15
*/

#include <stdio.h>

  int main()
  {
    float earthquake;
    float richterScale = 5.0;

    printf("enter the size of your earthquake: \n");
    scanf("%f", &earthquake);

    if(earthquake < richterScale)
		printf("the earthquake of size %.2f will cause little or no damage\n", earthquake);
    else if(earthquake >= richterScale && earthquake < 5.5) 
		printf("the earthquake of size %.2f will cause some damage\n", earthquake);
    else if(earthquake >= 5.5 && earthquake < 6.5)
		printf("the earthquake of size %.2f will cause serious damage: walls may crack or fall\n", earthquake);
    else if(earthquake >= 6.5 && earthquake < 7.5)
		printf("the earthquake of size %.2f will cause a diaster: houses and buildings may collapse\n", earthquake);
    else
		printf("the earthquake of size %.2f is a CATASTROPHE: most buildings are destroyed\n", earthquake);
    return 0;
  }

