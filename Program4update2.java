/* Jeanne Capaz  
CS3410 Summer 2015 Program-4
*/

import java.io.*;
import java.util.*;

class Program4update2 {
      private int[] array1;
      private int[] array2;
      private int length;

//Selection Sort
   public static int[] SelectionSort(int[] OrigArr) {
      for(int i = 0; i < OrigArr.length - 1; i++) {
         int temp = i;
         for(int j = i + 1; j < OrigArr.length; j++)
            if(OrigArr[j] < OrigArr[temp])
               temp = j;
  
            int small = OrigArr[temp];
            OrigArr[temp] = OrigArr[i];
            OrigArr[i] = small;
      }
      return OrigArr;
   }
  
//Insertion Sort
   public static int[] InsertionSort(int[] OrigArray) {
       int temp;
       for(int i = 1; i < OrigArray.length; i++)
       {
           for(int j = i ; j > 0 ; j--)
           {
               if(OrigArray[j] < OrigArray[j-1])
               {
                   temp = OrigArray[j];
                   OrigArray[j] = OrigArray[j-1];
                   OrigArray[j-1] = temp;
               }
           }
       }
       return OrigArray;
   }
  
//Merge Sort   
   
   public void MergeSort(int sortArray[]) {
      this.array1 = sortArray;
      this.length = sortArray.length;
      this.array2 = new int[length];
      MergeSorting(0, length - 1);
   }

   private void MergeSorting(int low, int high) {

      if(low < high) {
         int mid = low + (high - low) / 2;

         MergeSorting(low, mid);
         MergeSorting(mid + 1, high);
         MergingParts(low, mid, high);
      }
   }

   private void MergingParts(int low, int mid, int high) {

      for(int i = low; i <= high; i++) {
         array2[i] = array1[i];
      }
      
      int i = low;
      int j = mid + 1;
      int k = low;
      
      while(i <= mid && j <= high) {
         if(array2[i] <= array2[j]) {
            array1[k] = array2[i];
            i++;
         }
         else {
            array1[k] = array2[j];
            j++;
         }
         k++;
      }
      while (i <= mid) {
         array1[k] = array2[i];
         k++;
         i++;
      }

   }

//Quick Sort

   public void quick(int[] inputArray) {
      if(inputArray == null || inputArray.length == 0) {
         return;
      }
      this.array1 = inputArray;
      length = inputArray.length;
      quickSort(0, length - 1);
   }

   private void quickSort(int low, int high) {
      int i = low;
      int j = high;

      int pivot = array1[low + (high - low) / 2];

      while(i <= j) {
         while(array1[i] < pivot) {
            i++;
         }
         while(array1[j] > pivot) {
            j--;
         }
         if(i <= j) {
            exchange(i, j);
            i++;
            j--;
         }
      }

      if(low < j)
         quickSort(low, j);
         if(i < high)
            quickSort(i, high);
   }

   private void exchange(int i, int j) {
      int temp = array1[i];
      array1[i] = array1[j];
      array1[j] = temp;
   }

/*****************************************************************************/

   public static void main(String[] args) throws FileNotFoundException {
   
       ArrayList<Integer> randomNumArr = new ArrayList<Integer>();
       ArrayList<Integer> equalNumArr = new ArrayList<Integer>();
       ArrayList<Integer> sortedIncArr = new ArrayList<Integer>();
       ArrayList<Integer> sortedDecArr = new ArrayList<Integer>();
         
       Scanner input = new Scanner(System.in);
       System.out.println("Please enter a filename with extension: ");
       String file = input.next();
       Scanner scan = new Scanner(new File(file));//<------------Accessing the file
       while(scan.hasNextLine()) {//<----------------------------Check to see if the next line is blank
       
/*1************************************************************/       
         String line1 = scan.nextLine().trim();//<----------------------Reading the first line
         if((line1 = scan.nextLine()).isEmpty()) {
         continue;//<------------------Skipping the line if it is blank
         }   
         else {
            String[] set1 = line1.split(" ");//<--------------------Spliting the line by a space
               for(int i = 0; i < set1.length; i++) {//<---------------Going through the enitre first line of the String[]
               randomNumArr.add(Integer.parseInt(set1[i]));//<-------Changing the String to Integers and then adding them to the ArrayList
            }
         } 
            int[] data1 = new int[randomNumArr.size()];
            
            for(int j = 0; j < data1.length; j++) {//<--------------Going through the int[] and supplying it to data1
               data1[j] = randomNumArr.get(j);
            }
            
/*2************************************************************Here we go for the Equal numbered array*/        
       String line2 = scan.nextLine().trim();
       if((line2 = scan.nextLine()).isEmpty()){
       continue;
       }               
       else {
          String[] set2 = line2.split(" ");
            for(int i = 0; i < set2.length; i++) {
               equalNumArr.add(Integer.parseInt(set2[i]));
            }
       }
         int[] data2 = new int[equalNumArr.size()];       

         for(int j = 0; j < data2.length; j++) {
            data2[j] = equalNumArr.get(j);
         }

/*3************************************************************/  
       
       String line3 = scan.nextLine().trim();
       if((line3 = scan.nextLine()).isEmpty()) {
       continue;
       }
       else {         
          String[] set3 = line3.split(" ");
               for(int i = 0; i < set3.length; i++) {
                  sortedIncArr.add(Integer.parseInt(set3[i]));
               }
       }
         int[] data3 = new int[sortedIncArr.size()];
       
         for(int i = 0; i < data3.length; i++) {
            data3[i] = sortedIncArr.get(i);
         }
/*4************************************************************/ 
      
       String line4 = scan.nextLine().trim();
       if((line4 = scan.nextLine()).isEmpty()) {
       continue;
       }
       else {
          String[] set4 = line4.split(" ");
               for(int i = 0; i < set4.length; i++) {
                  sortedDecArr.add(Integer.parseInt(set4[i]));
               }
       }
         int[] data4 = new int[sortedDecArr.size()];
       
         for(int i = 0; i < data4.length; i++) {
            data4[i] = sortedDecArr.get(i);
         }
  
     scan.close();
/*************************************************************/        
        
       Program4update2 reference = new Program4update2();
       Program4update2 reference2 = new Program4update2();
       long start;
       long end;
       long total;


      
//Running Selection Sort on Random Array
       start=System.nanoTime();
      
       SelectionSort(data1);
       end=System.nanoTime();
       total=end-start;
       
       System.out.println("Runtime For Selection Sort :\n");

       System.out.print("**Random Array : "+total+" nanoseconds.\n");
      
//Running Selection Sort on Equal Array      
       start=System.nanoTime();
       
       SelectionSort(data2);
       end=System.nanoTime();
       total=end-start;      
       
       System.out.print("**Equal Array : "+total+" nanoseconds.\n");
      
//Running Selection Sort on Sorted Array Increasing order      
       start=System.nanoTime();
       
       SelectionSort(data3);
       end=System.nanoTime();
       total=end-start;      

       System.out.print("**Sorted in Increasing Order Array : "+total+" nanoseconds.\n");
      
//Running Selection Sort on Sorted Array Decreasing order      
       start=System.nanoTime();
       
       SelectionSort(data4);
       end=System.nanoTime();
       total=end-start;
      
       System.out.print("**Sorted in Decreasing Order Array : "+total+" nanoseconds.\n");
      
//Running Insertion Sort on Random Array
       start=System.nanoTime();      
       
       InsertionSort(data1);
       end=System.nanoTime();
       total=end-start;
       System.out.println("\nRuntime For Insertion Sort :\n");

       System.out.print("**Random Array : "+total+" nanoseconds.\n");
      
//Running Insertion Sort on Equal Array      
       start=System.nanoTime();
       
       InsertionSort(data2);
       end=System.nanoTime();
       total=end-start;      

       System.out.print("**Equal Array : "+total+" nanoseconds.\n");
      
//Running Insertion Sort on Sorted Array Increasing order      
       start=System.nanoTime();
       
       InsertionSort(data3);
       end=System.nanoTime();
       total=end-start;      
 
       System.out.print("**Sorted in Increasing Order Array : "+total+" nanoseconds.\n");
      
//Running Insertion Sort on Sorted Array Decreasing order      
       start=System.nanoTime();
       
       InsertionSort(data4);
       end=System.nanoTime();
       total=end-start;
      
       System.out.print("**Sorted in Decreasing Order Array : "+total+" nanoseconds.\n");
      
//Running MergeSort on Random Array

       System.out.println("\nRuntime For Merge Sort :\n");

       start=System.nanoTime();

       reference.MergeSort(data1);
       end=System.nanoTime();
       total=end-start;
      
       System.out.print("**Random Array : "+total+" nanoseconds.\n");
      

//Running MergeSort on Equal Array
      
       start=System.nanoTime();
       reference.MergeSort(data2);
       end=System.nanoTime();
       total=end-start;
      
       System.out.print("**Equal Array : "+total+" nanoseconds.\n");
      
//Running MergeSort on Sorted Array Increasing order 
       
       start=System.nanoTime();
       reference.MergeSort(data3);
       end=System.nanoTime();
       total=end-start;

       System.out.print("**Sorted in Increasing Order Array : "+total+" nanoseconds.\n");
      
//Running MergeSort on Sorted Array Decreasing order
 
       start=System.nanoTime();
       reference.MergeSort(data4);
       end=System.nanoTime();
       total=end-start;

       System.out.print("**Sorted in Decreasing Order Array : "+total+" nanoseconds.\n");
              
//Running For QuickSort on Random Array
      
       System.out.println("\nRuntime For Quick Sort :\n");
          
       start=System.nanoTime();
       reference2.quick(data1);
       end=System.nanoTime();
       total=end-start;

       System.out.print("**Random Array : "+total+" nanoseconds.\n");
      
//Running QuickSort on Equal Array 
       
       start=System.nanoTime();
       reference2.quick(data2);
       end=System.nanoTime();
       total=end-start;
      
       System.out.print("**Equal Array : "+total+" nanoseconds.\n");
      
//Running QuickSort on Sorted Array Increasing order
      
       start=System.nanoTime();
       reference2.quick(data3);
       end=System.nanoTime();
       total=end-start;
      
       System.out.print("**Sorted in Increasing Order Array : "+total+" nanoseconds.\n");
      
//Running QuickSort on Sorted Array Decreasing order
       
       start=System.nanoTime();
       reference2.quick(data4);
       end=System.nanoTime();
       total=end-start;
      
       System.out.print("**Sorted in Decreasing Order Array : "+total+" nanoseconds.\n");
              
   }
 }
}