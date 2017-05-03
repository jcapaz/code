#include<stdio.h>
#define FREEZE 32.0f
#define SCALE (5.0f / 9)

int main()
{
  float f, c;
  printf("enter temp in fahrenheit ");
  scanf("%f", &f);
  
  c = SCALE * (f- FREEZE);
  
 printf("the temp in celcius is %.1f\n", c);

return 0;
}
