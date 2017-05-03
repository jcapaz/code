#include<stdio.h>
#define KMS_PER_MILE 1.609

int main()
{
  double miles,kms;
  printf("enter distance in miles> ");
  scanf("%lf", &miles);
  
  kms = KMS_PER_MILE *miles;
  
  printf("that equals %f kms.\n", kms);
  
  return 0;
  
  
}
  