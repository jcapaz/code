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
  
   public void sort(int sortArray[]) {
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

   public static void main(String[] args) {
   
       ArrayList<Integer> randomNumArr = new ArrayList<Integer>();
       ArrayList<Integer> equalNumArr = new ArrayList<Integer>();
       ArrayList<Integer> sortedIncArr = new ArrayList<Integer>();
       ArrayList<Integer> sortedDecArr = new ArrayList<Integer>();
   
       Scanner filename = new Scanner(System.in);
       System.out.println("Please enter a filename with extension: ");
       String file = filename.next();
       Scanner scan = new Scanner(new File(file));
/*1************************************************************/       
       String line1 = scan.nextLine();
       String[] set1 = line1.split(" ");
       
       for(int i = 0; i < set1.length; i++) {
         randomNumArr.add(Integer.parseInt(set1[i]));
       }
       int[] data1 = new int[randomNumArr.size()];
       
       for(int i = 0; i < data1.length; i++) {
         data1[i] = randomNumArr.get(i);
       }
/*2************************************************************/         
       String line2 = scan.nextLine();
       String[] set2 = line2.split(" ");
       
       for(int i = 0; i < set2.length; i++) {
         equalNumArr.add(Integer.parseInt(set2[i]));
       }
       int[] data2 = new int[equalNumArr.size()];
       
       for(int i = 0; i < data2.length; i++) {
         data2[i] = equalNumArr.get(i);
       }
/*3************************************************************/  
       
       String line3 = scan.nextLine();
       String[] set3 = line3.split(" ");
       
       for(int i = 0; i < set3.length; i++) {
         sortedIncArr.add(Integer.parseInt(set3[i]));
       }
       int[] data3 = new int[sortedIncArr.size()];
       
       for(int i = 0; i < data3.length; i++) {
         data3[i] = sortedIncArr.get(i);
       }
/*4************************************************************/ 
      
       String line4 = scan.nextLine();
       String[] set4 = line4.split(" ");
       
       for(int i = 0; i < set4.length; i++) {
         sortedDecArr.add(Integer.parseInt(set4[i]));
       }
       int[] data4 = new int[sortedDecArr.size()];
       
       for(int i = 0; i < data4.length; i++) {
         data4[i] = sortedDecArr.get(i);
       }
/*************************************************************/        
        
       Program4update2 sortArr = new Program4update2();
       long start;
       long end;
       long total;
       ArrayList<Integer> outputArr = new ArrayList<Integer>();

      
       //Running Selection Sort on Random Array
       start=System.nanoTime();
      
       SelectionSort(randomNumArr);
       end=System.nanoTime();
       total=end-start;
       
       System.out.println("Runtime For Selection Sort :\n");
       for(int i:outputArr){
         System.out.print(i);
         System.out.print(" ");
       }
       System.out.println();
       System.out.print("--Random Array : "+total+" nanoseconds.\n");
      
       //Running Selection Sort on Equal Array      
       start=System.nanoTime();
       
       outputArr=SelectionSort(equalNumArr);
       end=System.nanoTime();
       total=end-start;      
       
       for(int i:outputArr){
         System.out.print(i);
         System.out.print(" ");
       }
       System.out.println();
       System.out.print("--Equal Array : "+total+" nanoseconds.\n");
      
       //Running Selection Sort on Sorted Array Increasing order      
       start=System.nanoTime();
       
       outputArr=SelectionSort(sortedIncArr);
       end=System.nanoTime();
       total=end-start;      
       for(int i:outputArr){
         System.out.print(i);
         System.out.print(" ");
       }
       System.out.println();
       System.out.print("--Sorted in Increasing Order Array : "+total+" nanoseconds.\n");
      
       //Running Selection Sort on Sorted Array Decreasing order      
       start=System.nanoTime();
       
       outputArr=SelectionSort(sortedDecArr);
       end=System.nanoTime();
       total=end-start;
      
       for(int i:outputArr){
         System.out.print(i);
         System.out.print(" ");
       }
       System.out.println();
       System.out.print("--Sorted in Decreasing Order Array : "+total+" nanoseconds.\n");
      
       //Running Insertion Sort
       start=System.nanoTime();      
       
       outputArr=InsertionSort(randomNumArr);
       end=System.nanoTime();
       total=end-start;
       System.out.println("\nRuntime For Insertion Sort :\n");
       for(int i:outputArr){
         System.out.print(i);
         System.out.print(" ");
       }
       System.out.println();
       System.out.print("--Random Array : "+total+" nanoseconds.\n");
      
       //Running Selection Sort on Equal Array      
       start=System.nanoTime();
       
       outputArr=InsertionSort(equalNumArr);
       end=System.nanoTime();
       total=end-start;      
       for(int i:outputArr){
         System.out.print(i);
         System.out.print(" ");
       }
       System.out.println();
       System.out.print("--Equal Array : "+total+" nanoseconds.\n");
      
       //Running Selection Sort on Sorted Array Increasing order      
       start=System.nanoTime();
       
       outputArr=InsertionSort(sortedIncArr);
       end=System.nanoTime();
       total=end-start;      
       for(int i:outputArr){
         System.out.print(i);
         System.out.print(" ");
       }
       System.out.println();
       System.out.print("--Sorted in Increasing Order Array : "+total+" nanoseconds.\n");
      
       //Running Selection Sort on Sorted Array Decreasing order      
       start=System.nanoTime();
       
       outputArr=InsertionSort(sortedDecArr);
       end=System.nanoTime();
       total=end-start;
      
       for(int i:outputArr){
         System.out.print(i);
         System.out.print(" ");
       }
       System.out.println();
       System.out.print("--Sorted in Decreasing Order Array : "+total+" nanoseconds.\n");
      
       //Runtime for Merge Sort
       System.out.println("\nRuntime For Merge Sort :\n");
       
       outputArr=randomNumArr;
       
       start=System.nanoTime();
       sortArr.sort(outputArr);
       end=System.nanoTime();
       total=end-start;
      
       for(int i:outputArr){
         System.out.print(i);
         System.out.print(" ");
       }
       System.out.println();
       System.out.print("--Random Array : "+total+" nanoseconds.\n");
      
       outputArr=randomNumArr;
       
       start=System.nanoTime();
       sortArr.sort(outputArr);
       end=System.nanoTime();
       total=end-start;
      
       for(int i:outputArr){
         System.out.print(i);
         System.out.print(" ");
       }
       System.out.println();
       System.out.print("--Equal Array : "+total+" nanoseconds.\n");
      
       outputArr=randomNumArr;
       
       start=System.nanoTime();
       sortArr.sort(outputArr);
       end=System.nanoTime();
       total=end-start;
      
       for(int i:outputArr){
         System.out.print(i);
         System.out.print(" ");
       }
       System.out.println();
       System.out.print("--Sorted in Increasing Order Array : "+total+" nanoseconds.\n");
      
       outputArr=randomNumArr;
       start=System.nanoTime();
       sortArr.sort(outputArr);
       end=System.nanoTime();
       total=end-start;
      
       for(int i:outputArr){
         System.out.print(i);
         System.out.print(" ");
       }
       System.out.println();
       System.out.print("--Sorted in Decreasing Order Array : "+total+" nanoseconds.\n");
              
       //Runtime FOr QuickSort
      
       System.out.println("\nRuntime For Quick Sort :\n");
       
       outputArr=randomNumArr;
       
       start=System.nanoTime();
       sortArr.quick(outputArr);
       end=System.nanoTime();
       total=end-start;
      
       for(int i:outputArr){
         System.out.print(i);
         System.out.print(" ");
       }
       System.out.println();
       System.out.print("--Random Array : "+total+" nanoseconds.\n");
      
       outputArr=randomNumArr;
       
       start=System.nanoTime();
       sortArr.quick(outputArr);
       end=System.nanoTime();
       total=end-start;
      
       for(int i:outputArr){
         System.out.print(i);
         System.out.print(" ");
       }
       System.out.println();
       System.out.print("--Equal Array : "+total+" nanoseconds.\n");
      
       outputArr=randomNumArr;
       
       start=System.nanoTime();
       sortArr.quick(outputArr);
       end=System.nanoTime();
       total=end-start;
      
       for(int i:outputArr){
         System.out.print(i);
         System.out.print(" ");
       }
       System.out.println();
       System.out.print("--Sorted in Increasing Order Array : "+total+" nanoseconds.\n");
      
       outputArr=randomNumArr;
       
       start=System.nanoTime();
       sortArr.quick(outputArr);
       end=System.nanoTime();
       total=end-start;
      
       for(int i:outputArr){
         System.out.print(i);
         System.out.print(" ");
       }
       System.out.println();
       System.out.print("--Sorted in Decreasing Order Array : "+total+" nanoseconds.\n");
              
   }

}