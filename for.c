#include<stdio.h>

int main()
{
  int count;
  scanf("%d", &count);
  while(count <= 0)
  {
   scanf("%d", &count);
  }
 int i, j;
 for (i =0; i < count; i++){
   for(j =0; j < count; j++){
      printf("*");
    }
      printf("\n");
}

return 0;
}

