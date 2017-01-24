/* Jeanne Capaz  
CS3410 Summer 2015 Program-2
*/
import java.io.*;

class Program2update2 {
   char stack1[];
   int top;
   
   Program2update2() {
       stack1 = null;
       top=0;
   }
  
   Program2update2(int size) {
       stack1=new char[size]; //allocate new char array for stack
       top=0;
   }
   
   void push(char ch) {  
       //increment of top
       stack1[top++]=ch;
   }
   char pop() {
       char ch;
       ch=stack1[--top]; 
       return ch; 
   }
   
   int pre(char ch) { //getting priority no. -,+ are 1, * and / are 2
       switch(ch)
       {
           case '-':return 1;
           case '+':return 1;
           case '*':return 2;
           case '/':return 2;
       }
       return 0;
   }
   
   boolean operator(char ch) {
       if(ch=='/'||ch=='*'||ch=='+'||ch=='-')
           return true;
       else
           return false;
   }
   
   boolean isAlpha(char ch){
       if(ch>='a'&&ch<='z'||ch>='0'&&ch<='9')
           return true;
       else
           return false;
   }
   
   void postfix(String str) {
       char output[]=new char[str.length()];
       char ch;
       int p=0,i;
       for(i=0;i<str.length();i++) {
           ch = str.charAt(i);  
           if(ch == '(')
               push(ch);
           else if(isAlpha(ch)) //if operand is found
               output[p++]=ch;
           else if(operator(ch)) { //if operator is encountered
              if(stack1[top-1 ]== 0 || (pre(ch) > pre(stack1[top-1])) || stack1[top-1] == '(') //checking priority of operators with existing
                   push(ch); //push ch to stack
           }
           else if(pre(ch) <= pre(stack1[top-1])) { //if new operator has lowest priority
               output[p++] = pop(); //pop old from stack
               push(ch); //push new one to stack
               }
           else if(ch == ')') { //if ( is found then pop all until ( is found
               while((ch = pop()) != '(') {
                   output[p++] = ch; //add to output
               }
           }
       }
       while(top != 0) { //repeat until top !=0
           output[p++] = pop(); //pop from stack
       }
       for(int j=0;j < str.length();j++) { //printing of final postfix notation
           System.out.print(output[j]);  
       }
   }
}
class InfixToPostfix {
   public static void main(String[] args)throws Exception {
       String s;
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
       System.out.println("Enter input string");
       s = br.readLine(); //read line of string
       Program2update2 b = new Program2update2(s.length()); //stack object
       
       System.out.println("Input String:"+ s); //print input string
       System.out.println("Output String:"); //print output string
       b.postfix(s);
   }
}