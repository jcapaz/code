/* Jeanne Capaz  
CS3410 Summer 2015 Program-2
*/

import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;


class Evaluator {
    private static final int EOL     = 0;
    private static final int VALUE   = 1;
    private static final int OPAREN  = 2;
    private static final int CPAREN  = 3;
    private static final int EXP     = 4;
    private static final int MULT    = 5;
    private static final int DIV     = 6;
    private static final int PLUS    = 7;
    private static final int MINUS   = 8;

    private static class Precedence {
        public int inputSymbol;
        public int topOfStack;

        public Precedence( int inSymbol, int topSymbol ) {
            inputSymbol = inSymbol;
            topOfStack  = topSymbol;
        }
    }

    private static Precedence[ ] precTable = new Precedence[ ] {
        new Precedence(   0, -1 ),  // EOL
        new Precedence(   0,  0 ),  // VALUE
        new Precedence( 100,  0 ),  // OPAREN
        new Precedence(   0, 99 ),  // CPAREN
        new Precedence(   6,  5 ),  // EXP
        new Precedence(   3,  4 ),  // MULT
        new Precedence(   3,  4 ),  // DIV
        new Precedence(   1,  2 ),  // PLUS
        new Precedence(   1,  2 )  // MINUS
    };

    private static class Token {
        public Token( ) {
            this( EOL );
        }
        
        public Token( int t ) {
            this( t, 0 );
        }
        
        public Token( int t, long v ) {
            type = t;
            value = v;
        }
        
        public int getType( ) {
            return type;
        }
        
        public long getValue( ){
            return value;
        }
        
        private int type = EOL;
        private long value = 0;
    }    

    private static class EvalTokenizer {
        public EvalTokenizer( StringTokenizer is ){
            str = is;
        }
        
        public Token getToken( ) {
            long theValue;           

            if( !str.hasMoreTokens( ) )
                return new Token( );

            String s = str.nextToken( );
            if( s.equals( " " ) ) return getToken( );
            if( s.equals( "^" ) ) return new Token( EXP );
            if( s.equals( "/" ) ) return new Token( DIV );
            if( s.equals( "*" ) ) return new Token( MULT );
            if( s.equals( "(" ) ) return new Token( OPAREN );
            if( s.equals( ")" ) ) return new Token( CPAREN );
            if( s.equals( "+" ) ) return new Token( PLUS );
            if( s.equals( "-" ) ) return new Token( MINUS );

            try
              { theValue = Long.parseLong( s ); }
            catch( NumberFormatException e ){
                System.err.println( "Parse error" );
                return new Token( );
            }
            
            return new Token( VALUE, theValue );
        }
        
        private StringTokenizer str;
        
    }
    

    public Evaluator( String s ) {
        opStack = new Stack( );
        postfixStack = new Stack( );

        str = new StringTokenizer( s, "+*-/^() ", true );

        opStack.push( new Integer( EOL ) );
    }


    public long getValue( ) {
        EvalTokenizer tok = new EvalTokenizer( str );
        Token lastToken;
        
        do {
            lastToken = tok.getToken( );
            processToken( lastToken );
        } while( lastToken.getType( ) != EOL );

        if( postfixStack.isEmpty( ) ) {
            System.err.println( "Missing operand!" );
            return 0;
        }

        long theResult = postFixTopAndPop( );
        if( !postfixStack.isEmpty( ) )
            System.err.println( "Warning: missing operators!" );

        return theResult;
    }


    private Stack opStack;       // Operator stack for conversion
    private Stack postfixStack;  // Stack for postfix machine
    private StringTokenizer str; // StringTokenizer stream


    private long postFixTopAndPop( ) {
        return ( (Long) ( postfixStack.pop( ) ) ).longValue( );
    }

    private int opStackTop( )    {
        return ( (Integer) ( opStack.peek( ) ) ).intValue( );
    }

    private void processToken( Token lastToken ) {
        int topOp;
        int lastType = lastToken.getType( );
      
   
        switch( lastType )
        {
          case VALUE:
            postfixStack.push( new Long( lastToken.getValue( ) ) );
            return;

          case CPAREN:
            while( ( topOp = opStackTop( ) ) != OPAREN && topOp != EOL )
                binaryOp( topOp );
            if( topOp == OPAREN )
                opStack.pop( );  // Get rid of opening parentheseis
            else
                System.err.println( "Missing open parenthesis" );
            break;

          default:    // General operator case
            while( precTable[ lastType ].inputSymbol <=
                   precTable[ topOp = opStackTop( ) ].topOfStack )
                binaryOp( topOp );
            if( lastType != EOL )
                opStack.push( new Integer( lastType ) );
            break;
        }
    }


    private long getTop( ) {
        if ( postfixStack.isEmpty( ) ) {
            System.err.println( "Missing operand" );
            return 0;
        }
        return postFixTopAndPop( );
    }


    private static long pow( long x, long n ) {
        if( x == 0 ){
            if( n == 0 )
                System.err.println( "0^0 is undefined" );
            return 0;
        }
        if( n < 0 ) {
            System.err.println( "Negative exponent" );
            return 0;
        }
        if( n == 0 )
            return 1;
        if( n % 2 == 0 )
            return pow( x * x, n / 2  );
        else
            return x * pow( x, n - 1 );
    }

 
    private void binaryOp( int topOp ){
        if( topOp == OPAREN )
        {
            System.err.println( "Unbalanced parentheses" );
            opStack.pop( );
            return;
        }
        long rhs = getTop( );
        long lhs = getTop( );

        if( topOp == EXP )
            postfixStack.push( new Long( pow( lhs, rhs ) ) );
        else if( topOp == PLUS )
            postfixStack.push( new Long( lhs + rhs ) );
        else if( topOp == MINUS )
            postfixStack.push( new Long( lhs - rhs ) );
        else if( topOp == MULT )
            postfixStack.push( new Long( lhs * rhs ) );
        else if( topOp == DIV )
            if( rhs != 0 )
                postfixStack.push( new Long( lhs / rhs ) );
            else {
                System.err.println( "Division by zero" );
                postfixStack.push( new Long( lhs ) );
            }
        opStack.pop( );
    }

class Postfix {

      public String operator = "+-*/";
      public String numbers = "1234567890";
   
      public int eval(String str) {

         Stack<Integer> postfix = new Stack<Integer>();
         for(int i = 0; i < str.length(); i++){
            char s = str.charAt(i);
            if(isOperator(s)) {
               int x = postfix.pop();
               int y = postfix.pop();
               int z = evalOper(y, x, s);
               postfix.push(z);
            }

            else if(!isOperator(s)) {
               String temp = "" + s;
               postfix.push(Integer.parseInt(temp));
            }
         }
         return postfix.pop();
      }
       
       public int evalOper(int a, int b, char ch) {
         if(ch == '+')
            return a + b;
         else if(ch == '-')
            return a - b;
         else if(ch == '*')
            return a * b;
         else if(ch == '/')
            return a/b;
         return -1;
      }
       public boolean isOperator(char ch) {
         String oper = "" + ch;
         return operator.contains(oper);
      }
      
      public String convert(String str) {
      String temp = "";
      
      Stack<Character> myStack = new Stack<Character>();
      for(int i = 0;i < str.length(); i++) {
         char c = str.charAt(i);
         if(c==('+')||c==('*')||c==('-')||c==('/')) {

            while(!myStack.empty() && isLower(myStack.peek(), c))
               temp += myStack.pop();
               myStack.push(c);
         }
         else if(c == '(') {
            myStack.push(c);
         }
         else if(c == ')') {
            while(!myStack.peek().equals('('))
               temp += myStack.pop();
            myStack.pop();
         }
         else
            temp += c;
      }
      while(!myStack.empty())
         temp += myStack.pop();
      return temp;
   }
   public boolean isLower(char c1, char c2) {
      if((c1 == '+' || c1 == '-') && (c2 == '*' || c2 == '/'))
         return true;
      return false;
   }
}

    public static void main(String[] args) {
         
      System.out.println("Input Your Infix Expression: ");
      Scanner input = new Scanner(System.in);
      String s = input.nextLine();
      
      Evaluator ev = new Evaluator(s);    
            
      System.out.println("Result: " + ev.getValue());
      
      }
        
        
 }
