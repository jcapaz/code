#include<stdio.h>

int main()
{
  int array[] = {45, 67, 89};
  int *array_ptr;
  array_ptr = array;
  printf("first element: %i (%p)\n", *array_ptr, array_ptr);
  array_ptr++;
   printf("second element: %i (%p)\n", *array_ptr, array_ptr);
 array_ptr++;
   printf("third element: %i (%p)\n", *array_ptr, array_ptr);
return 0;
}
