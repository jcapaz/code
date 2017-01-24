public class Node<T> {
2	  public int value;
3	  public Node left;
4	  public Node right;
5	 
6	  public Node(int value) {
7	    this.value = value;
8	  }
9	 
10	}

public class BinarySearchTree {
2	  public Node root;
3	 
4	  public void insert(int value){
5	    Node node = new Node<>(value);
6	 
7	    if ( root == null ) {
8	      root = node;
9	      return;
10	    }
11	 
12	    insertRec(root, node);
13	 
14	  }
15	 
16	  private void insertRec(Node latestRoot, Node node){
17	 
18	    if ( latestRoot.value > node.value){
19	 
20	      if ( latestRoot.left == null ){
21	        latestRoot.left = node;
22	        return;
23	      }
24	      else{
25	        insertRec(latestRoot.left, node);
26	      }
27	    }
28	    else{
29	      if (latestRoot.right == null){
30	        latestRoot.right = node;
31	        return;
32	      }
33	      else{
34	        insertRec(latestRoot.right, node);
35	      }
36	    }
37	  }
38	}

2	 * Returns the minimum value in the Binary Search Tree.
3	 */
4	public int findMinimum(){
5	  if ( root == null ){
6	    return 0;
7	  }
8	  Node currNode = root;
9	  while(currNode.left != null){
10	    currNode = currNode.left;
11	  }
12	  return currNode.value;
13	}
14	 
15	/**
16	 * Returns the maximum value in the Binary Search Tree
17	 */
18	public int findMaximum(){
19	  if ( root == null){
20	    return 0;
21	  }
22	 
23	  Node currNode = root;
24	  while(currNode.right != null){
25	    currNode = currNode.right;
26	  }
27	  return currNode.value;
28	}

1	/**
2	 * Printing the contents of the tree in an inorder way.
3	 */
4	public void printInorder(){
5	  printInOrderRec(root);
6	  System.out.println("");
7	}
8	 
9	/**
10	 * Helper method to recursively print the contents in an inorder way
11	 */
12	private void printInOrderRec(Node currRoot){
13	  if ( currRoot == null ){
14	    return;
15	  }
16	  printInOrderRec(currRoot.left);
17	  System.out.print(currRoot.value+", ");
18	  printInOrderRec(currRoot.right);
19	}

/**
2	 * Printing the contents of the tree in a Preorder way.
3	 */
4	public void printPreorder() {
5	  printPreOrderRec(root);
6	  System.out.println("");
7	}
8	 
9	/**
10	 * Helper method to recursively print the contents in a Preorder way
11	 */
12	private void printPreOrderRec(Node currRoot) {
13	  if (currRoot == null) {
14	    return;
15	  }
16	  System.out.print(currRoot.value + ", ");
17	  printPreOrderRec(currRoot.left);
18	  printPreOrderRec(currRoot.right);
19	}

/**
2	 * Printing the contents of the tree in a Postorder way.
3	 */
4	public void printPostorder() {
5	  printPostOrderRec(root);
6	  System.out.println("");
7	}
8	 
9	/**
10	 * Helper method to recursively print the contents in a Postorder way
11	 */
12	private void printPostOrderRec(Node currRoot) {
13	  if (currRoot == null) {
14	    return;
15	  }
16	  printPostOrderRec(currRoot.left);
17	  printPostOrderRec(currRoot.right);
18	  System.out.print(currRoot.value + ", ");
19	 
20	}

public class BinarySearchTreeDemo {
152	 
153	  public static void main(String[] args) {
154	    BinarySearchTree bst = new BinarySearchTree();
155	    bst .insert(40)
156	        .insert(25)
157	        .insert(78)
158	        .insert(10)
159	        .insert(3)
160	        .insert(17)
161	        .insert(32)
162	        .insert(30)
163	        .insert(38)
164	        .insert(78)
165	        .insert(50)
166	        .insert(93);
167	    System.out.println("Inorder traversal");
168	    bst.printInorder();
169	 
170	    System.out.println("Preorder Traversal");
171	    bst.printPreorder();
172	 
173	    System.out.println("Postorder Traversal");
174	    bst.printPostorder();
175	 
176	    System.out.println("The minimum value in the BST: " + bst.findMinimum());
177	    System.out.println("The maximum value in the BST: " + bst.findMaximum());
178	 
179	  }
180	}
- See more at: http://www.javabeat.net/binary-search-tree-traversal-java/#sthash.9rHRxDSk.dpuf