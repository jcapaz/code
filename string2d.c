#include <stdio.h>
 
int main()
{
      int i, j;
      char a[3][4];
      printf("Give three 3-characters strings.\n");
      for(i = 0; i <= 2; i = i + 1)
            // scanf("%s", &a[i]);
            scanf("%s", &a[i]);
      for(i = 0; i <= 2; i = i + 1)
      {
            for(j = 0; j <= 3; j = j + 1)
                  printf("a[%d][%d] = %c\t", i, j, a[i][j]);
            printf("\n");
      }
return 0;
}
