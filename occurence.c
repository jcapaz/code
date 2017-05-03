#include<stdio.h>



int occurences(const char text[], int len, char target)
{

int count = 0;
int i;
for(i = 0; i < len; i++)
if (text[i] == target)
count++;
return count;
}




int main()
{
int num_grades;
char grades[50];
printf("enter number of grades");
scanf("%d", &num_grades);

int i;
for(i = 0; i < num_grades; i++)
scanf(" %c", &grades[i]);

int num_a = occurences(grades, num_grades, 'A');

printf("%d", num_a);


}







