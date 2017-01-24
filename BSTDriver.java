/* Jeanne Capaz  
CS3410 Summer 2015 Program-5b
*/

import java.util.*;

public class BSTDriver extends BSTupdate {
   public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
      
      BSTupdate bst = new BSTupdate();
		
        while(true) {
           System.out.println("\nBinary Search Tree Operations\nPlease make Your Choice Below: \n");
           System.out.println("1. Insert an item in the binary search tree");
           System.out.println("2. Delete an item from the binary search tree");
           System.out.println("3. Search for a particular item in the tree.");
           System.out.println("4. Display the minimum item in the tree.");
           System.out.println("5. Display total number of items currently present in the binary search tree.");
           System.out.println("6. Display Items in InOrder Traversal.");
           System.out.println("7. Quit.");
           System.out.print("\nEnter your choice: \n");
           
           int choice = input.nextInt();

           switch (choice) {
           case 1:
               System.out.print("Enter an integer to insert into the tree : ");
               bst.add(input.next());
               break;
           case 2:
               System.out.print("Enter an integer to delete from the tree :");
               bst.delete(input.next());
               break;
           case 3:
               System.out.println("Enter an integer to search for : ");
               System.out.println("Search result : " + bst.search(input.next()));
               break;
           case 4:
               System.out.println("Display the Minimum Item in Tree : " + bst.findMinItem());
               break;
           case 5:
               System.out.println("The Total # of Integers in Tree = " + bst.getSize());
               break;
           case 6:
               System.out.println("Display the Tree by inOrder Traversal : ");
               System.out.println(bst.inOrderTraversal());
               break;
           case 7:
               break;
           default:
               System.out.println("Try again \n ");
               break;
           }
           if (choice == 7)
               break;

       }
       System.out.println("\nGoodbye!");
   }

}

