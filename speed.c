#include<stdio.h>

 #define LIMIT 70
 #define RECKLESS 15


int main()
{

  
  int speed;
  printf("enter the speed \n");
  scanf("%d", &speed);
  
  
    if (speed > LIMIT)
      
        if(speed - LIMIT >= RECKLESS)
          printf("pay $150\n");
        else
          printf("pay $100\n");
      
      
    else if (speed == LIMIT)
        printf( "No fine \n");
    
    else
        printf("You are okay\n");
return 0;

}

