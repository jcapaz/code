//Jeanne Capaz, CS3410, Summer 2015, Program 1
import java.util.Random;

public class Program1 {

   public static void main(String[] args) {
   
      Random m = new Random();
      int temp = (m.nextInt(999 - 100 + 1) + 100);
      
      for(int i = 10; i <= 100000; i=i*10) {
        
      //GCD Version 1:
         long T1_gcd = System.nanoTime();
         int g1 = gcd(temp, i);
         long T2_gcd = System.nanoTime();
      
         long t3 = (T2_gcd - T1_gcd);
      
         System.out.println("GCD version 1 time: " + t3 + " nanoseconds of (" + temp + ", " + i + ") where the gcd is " + g1);
      
      //Euclid's:
         long T1_euclid = System.nanoTime();
         int g2 = gcdEuclid(temp, i);
         long T2_euclid = System.nanoTime();
         
         long t4 = (T2_euclid - T1_euclid);
      
         System.out.println("Euclid's version time: " + t4 + " nanoseconds of (" + temp + ", " + i + ") where the gcd is1 " + g2);
      }
   }

   //GCD Version 1 Method:
   public static int gcd(int m, int n) {
      int gcd = 1;
      for (int k = 2; k <= m && k <= n; k++) {
         if (m % k == 0 && n % k == 0)
            gcd = k;
      }
      return gcd;
   }

   //GCD Euclid's Method:
   public static int gcdEuclid(int m, int n) {
      if (m % n == 0) {
         return n;
      }
      else {
         return gcdEuclid(n, m % n);
      }
   }
}
