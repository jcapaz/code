#include<stdio.h>

#define DISCOUNT 0.15
#define LOW 25.0
#define HI 50.0


int main()
{
  float price, saleprice;
  
 printf("enter the original price : \n");
 scanf("%f", &price);

 if(price > LOW && price <= HI)
   saleprice = price - (price * DISCOUNT);
 else
    saleprice = price;

printf("the saleprice is %.2f \n", saleprice);

return 0;
} 
