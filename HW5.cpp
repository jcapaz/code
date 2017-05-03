 /* 
Program Name: Homework5.cpp 
Programmer: Jeanne Capaz
Class: CS 3335 A 
HW: 05   
Problem: 01 
Date: 12/1/15
*/ 

#include <iostream>
#include <cstring>

using namespace std;

const int MAX_NAME = 20;
const int MAX_SALESMEN = 5;
const int NOT_FOUND = -1;

struct Seller
{
   float sales;               // running total of money he collected
   float time;                // running total of time, in hours
   char name[MAX_NAME + 1];   // name of the salesman
};

struct ListOfSalesmen
{
   int num;                  // number of salesmen in the list
   Seller salesman[MAX_SALESMEN];
};

void InitSeller(Seller & s, const char name[]);
void UpdateSales(Seller & s, float sales, float time);
bool SellerHasName(const Seller & s, const char name[]);
float SalesValue(Seller & s);

int Find(ListOfSalesmen &s, const char name[]);
void Add(ListOfSalesmen &s);
void Output(ListOfSalesmen &s);
void Update(ListOfSalesmen &s);



int main()
{
   ListOfSalesmen & s;
   ListOfSalesmen.num = 0;
   char command;
   
   while(command != 'Q')
   {
      switch(command)
      {
         case 'A':
            Add(s);
            break;
         case 'O':
            Output(s);
            break;
         case 'U':
            Update(s);
            break;  
         default:
            cout << "Pick the correct letter please" << endl;
      }
   }
}

void InitSeller(Seller & s, const char name[])
{
// Initializes the Salesman's name to name.  
// Initializes the Salesman's sales and time to 0.0.

   strcpy(s.name, name);
   s.time = 0.0;
   s.sales = 0.0;

}

void UpdateSales(Seller & s, float sales, float time) // Adds the money and time to the salesman's accumulated sales and time.
{
   s.time = s.time + time;
   s.sales = s.sales + sales;

}

bool SellerHasName(const Seller & s, const char name[]) // Returns true if the salesman's name is the same as name; false otherwise.
{
   return strcmp(s.name, name);
}

float SalesValue (Seller & s)
{
// Returns sales per hour for the salesman.
// Returns 0.0 if the salesman's time is 0.0.
// It also zeroes the salesman's sales & time when done calculating.
   if(s.time == 0.0)
   {
      return 0.0;
   }
   else
   {
      return s.sales/s.time;
   }
   s.time = 0.0;
   s.sales = 0.0;
}

int Find(ListOfSalesmen & s, const char name[]) //determines whether or not a salesman with the specified name is in the ListOfSalesmen.
{
   int i;
   for(i = 0; i < s.num; i++)
   {
      if(SellerHasName(s.salesman[i], name))
      {
         return i;
      }
      else
      {
         return NOT_FOUND;
      }
   }
}

void Add(ListOfSalesmen & s) //adds a salesman with the given name to the end of the list if a salesman with that name doesn't already exist and the list isn't full.
{
   char name[MAX_NAME + 1];
   
   cin >> name;
   int index = Find(s, name);
   
   if(s.num > MAX_SALESMEN)
      cout << "Cannot add" << name << "List is Full!" << endl;
   else if(index != NOT_FOUND)
      cout << "Cannot add" << name << "Name is already in the list" << endl;
   else
      InitSeller(name, index); 
      cout << name << "added to the list" << endl;
      cout << name << " is salesman " << s.num << endl;
}

void Output(ListOfSalesmen &s)//outputs the sales per hour for that salesman; if the salesman doesn't exist, print do not find
{
   char name[MAX_NAME + 1];
   
   cin >> name;
   int index = Find(s.salesman, name);
   
   if(index != NOT_FOUND)
   { 
      cout << name << ":" << SalesValue(name) << "per hour" << endl;
   }
   else
   {
      cout << "Cannot out." << name << "is not in the list." << endl;
   }
}

void Update(ListOfSalesmen & s) //updates the salesman with the given sales and time; if the salesman doesn't exist, print do not find
{
   char name[MAX_NAME + 1];
   float sales;
   float time;
   
   cin >> name;
   cin >> sales;
   cin >> time;
   
   int index = Find(s, name);
   
   if(index != NOT_FOUND)
   {
      cout << name << " sold " << sales << " of toys in" << time << " hours.";
   }
   else
   {
      cout << "Salesman not found";
   }
   
}