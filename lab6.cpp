
//---------------------------------------------------------------------
// DO_1: Jeanne Capaz
// Name: 
// 
// 
// Purpose: This program reads in a string first, computes and
//          displays the size of the string; it then reads in a 
//          char, finds and displays how many times the char
//          occurs in the string. 
//
// Input:   A string and a character 
//          
// Output:  Input prompts; 
//          The input string with its size;
//          The number of times the character occurs in the input string.
//---------------------------------------------------------------------

#include <iostream>
#include <cstring>

using namespace std;

const int MAX_SIZE = 11;

int TargetCount(const char str[], char target);
int StrSize(const char str[]);

int main()
{
   char word[MAX_SIZE], target;
   int  size, count;

   cout << "Enter a string (at most 10 characters): ";

   // DO_4: Read in a string to word.
   
      cin >> word;
 
      cout << endl << "The string entered: ";

      // DO_5: Display string word.

      cout << word << endl;

      // DO_6: Call function StrSize to find the size of word
      //       and store the result in variable size.
  
      size = StrSize(word);

      cout << endl << "String '" << word << "' has " << size << " characters.";

      cout << "\nEnter a character: ";

      // DO_7: Read in a char to target.

      cin >> target;

      // DO_8: Call function TargetCount to find out 
      //       how many times target occurs in word.

      count = TargetCount(word, target);

      cout << "Character '" << target << "' occurs in "
		   << "'" << word << "' " << count << " times.";

   return 0;
}

// The function has two parameters:
//    str   : array of char
//    target: char
// The function finds and returns how many times target occurs in str.

int TargetCount(const char str[], char target)
{
   int count = 0;
  
   // DO_2: Using a for loop to find out how many times
   //       target occurs in str.
   // DO NOT call function StrSize!

   int i;
   
   for(i = 0; i < strlen(str); i++)
   {
      if(str[i] == target)
      {
         count++;
      }
   }
   return count;
}

// The function has one parameter:
//    str   : array of char
// The function finds and returns the size of str,
//     which is the number of chars, excluding the ending null char.

int StrSize(const char str[])
{
   // DO_3: Using a loop to find out the number of chars of str, 
   //       excluding the ending null char.

   int size = 0;
   
   while(str[size] != '\0')
   {
      size++;
   }

   return size;
}
