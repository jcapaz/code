#include <stdio.h>

/*Program Name: Battleship.c
Programmer: Jeanne Capaz
Class CS 3335 A
HW: 04
Problem: 01
Date: 11/5/15
*/

//This method sets up the human's board game.
//The user will place the two ships, which must be in consecutive order
//The board only has 10 slots

void setBoard(int[] hBoard)
{
   int shipPosition[];
   int ship1;
   int ship2;
   int i, j;
    
   printf("Enter 1st Position of Your Ship:/n");
   scanf("%d", &ship1);
   
   printf("Enter 2nd Position of Your Ship:/n");
   scanf("%d", &ship2);
   
   for(i = 0; i < 3; i++)
   {
      for(j = 0; j < 3; j++)
      {
         shipPosition[i][j] = shipPosition[ship1][ship2];
      }
   }
}


//This method sets up the computer's board game.
//The ships will be placed in random order but must be in consecutive order
//The board only has 10 slots

void setComputerBoard(int[] cBoard)
{
   int shipPosition[];
   int i, j;

      for(i = 0; i < 3; i++)
      {
         for(j = 0; j < 3; j++)
         {
            shipPosition[i][j] = rand() % 10;
         }
      }
}

//This method will print the board as the game is played
//S = ship's location in the array
//M = miss shot
//H = successful shot
//* = all other slots in the array

void printBoard(int[] mainBoard)
{
   int i;
   char miss = 'M';
   char hit = 'H';
   char ship = 'S';
   char slot = '*';
   
   
   for(i = 0; i < 10; i++)   
   {
      printf("%d", i);
      printf("\n");
      if(mainboard[i] == -1)
      {
         printf("%c", slot)
      }
      else if(mainboard[i] == 0)
      {
         printf("%c", miss);
      }
      else if(mainboard[i] == 1)
      {
         printf("%c", hit);
      }
      else
      {
         printf("%c", ship);
      }
   }
}

/*
1. Computer's Turn: The computer will shoot randomly at the human's board.  If the computer picks on a human's ship
"HIT!" message will be displayed.  If it picks an empty slot, "MISS!" will be displayed.  The boards
will be printed during each player's round.

2. Human's Turn: The human will shoot randomly at the computer's board.  If the human picks on a computer's ship
"HIT!" message will be displayed.  If it picks an empty slot, "MISS!" will be displayed.

3. Repeat until there is a winner: both ships must be hit during the same round by either the computer or the human.
*/
int playGame(int[] hShip, int[] cShip)
{
   int game[];
   int i, j;
   int computerGuess;
   int humanGuess;
   
   //This is the computer's turn
   
   computerGuess = rand() % 10;
   printf("Computer guesses: %d", computerGuess);
   
      for(i = 0; i < 10; i++)
      {
         if(hShip[i] == hShip[computerGuess])
         {
            printf("HIT! You sunk my battleship!");
         }
         else
         {
            printf("MISS! Too bad!");
         }
      printBoard(game[10]); 
      }   
   
   //This is the human's turn
   printf("Enter Guess between 1 - 10:");
   scanf("%d", &humanGuess);
   printf("You guessed: %d\n", humanGuess);   
   
   for(i = 0; i < 10; i++)
      {
         if(cShip[i] == cShip[humanGuess])
         {
            printf("HIT! You sunk my battleship!");
         }
         else
         {
            printf("MISS! Too bad!");
         }
      printBoard(game[10]); 
      } 
   
}

int main()
{

   int *human;
   human = new int[10];
   
   int *computer;
   computer = new int[10];
   
   srand(time(NULL));
   
   setBoard(human);
   setComputerBoard(computer);
   
   int winner = playGame(human, computer);
   
   if(winner == 0)
   {
      cout << "Computer wins!" << endl;
   }
   else
   {
      cout << "Human wins!" << endl;
   }
}
     
   
   
       