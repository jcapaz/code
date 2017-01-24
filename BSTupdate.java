/* Jeanne Capaz  
CS3410 Summer 2015 Program-5a
*/

/** Modified from code supplied by Dr. Jam Jenkins */

import java.util.*;

 class BSTupdate {
	class Node {
		Node parent;
		String data;
		Node left;
		Node right;
	}
	
	private Node root;
	private int size;
	
	public BSTupdate() {
		size=0;
		root=null;
	}
	
	public void add(String text) {
		Node node=new Node();
		node.data=text;
		size++;
		if(root!=null)
			add(node, root);
		else
			root=node;
	}
	
	/**
	 * inserts a node below the parent
	 * @param toAdd the node to be added
	 * @param parent 
	 */
    
	private void add(Node toAdd, Node parent) {
		if(toAdd.data.compareTo(parent.data)<0) {
			//insert on left
			if(parent.left == null) {
				parent.left=toAdd;
				toAdd.parent=parent;
			}
			else {
				add(toAdd, parent.left);
			}
		}
		else {
			//insert on right
			if(parent.right==null) {
				parent.right=toAdd;
				toAdd.parent=parent;
			}
			else {
				add(toAdd, parent.right);
			}			
		}
	}
	
	
   public String toString() {
		return inOrderTraversal(root).toString();
	}
	
 
	public ArrayList<String> inOrderTraversal(Node node)
	{
		if(node == null) 
         return new ArrayList<String>();
         
		ArrayList<String> result = new ArrayList<String>();
		result.addAll(inOrderTraversal(node.left));
		result.add(node.data);
		result.addAll(inOrderTraversal(node.right));
		return result;
	}
   
  public ArrayList inOrderTraversal() {
       return inOrderTraversal(root);
   }
   
	public int getSize(){
		return size;
	}
	
   private Node findLeast(Node node) {
		Node least = node;
		while(least.left != null)
			least = least.left;
		      return least;
	}
	
   
	public String findMinItem(){
		return findLeast(root).data;
	}
		
	private Node findGreatest(Node node) {
		Node great=node;
		while(great.right!=null)
			great=great.right;
		      return great;
	}
	
	public String findMaxItem(){
		return findGreatest(root).data;
	}

/*Three Cases of Deletion
1. Delete a child (leaf)
2. Delete a parent with one child
3. Delete a parent with two children
*/

   public void delete(String data) {
       if (getSize() == 0)
           System.out.println("This tree is empty");
       else if (search(data) == false)
           System.out.println("Sorry " + data + " is not present in this tree");
       else {
           root = delete(root, data);
           System.out.println(data + " deleted from the tree");
           size--;
       }
   }
//Updated
   private Node delete(Node root, String data) {
       Node temp;
       Node p, p2;
       if (root.data.equalsIgnoreCase(data)) {
         Node lt, rt;
            lt = root.left;
            rt = root.right;

           if (lt == null && rt == null) //Case 1: Leaf node
               return null;
           else if (lt == null) { //Case 2: Right child
               p = rt;
               return p;
           } 
           else if (rt == null) { //Case 2: Left child
               p = lt;
               return p;
           } 
           else {
               p2 = rt;
               p = rt;
               
           while (p.left != null)
                   p = p.left;
                   p.left = lt;
           return p2;
           }
       }
       if (data.compareTo(root.data) < 0) {
           temp = delete(root.left, data);
           root.left = temp;
       } 
       else {
           temp = delete(root.right, data);
           root.right = temp;
         }
       return root;
   }

//Finds the node requested:
   private boolean search(Node r, String value) {
       boolean found = false;
       while ((r != null) && !found) {
           String rval = r.data;
           if (value.compareTo(rval) < 0)
               r = r.left;
           else if (value.compareTo(rval) > 0)
               r = r.right;
           else {
               found = true;
               break;
           }
           found = search(r, value);
       }
       return found;
   }

   public boolean search(String value) {
       return search(root, value);
   }
    
}

//end_of_BST_class
