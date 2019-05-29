package com.programming.competitions;

import java.util.Arrays;

//For solving the matrix exponentiation we are assuming a
//linear recurrence equation like below:
//
//F(n) = a*F(n-1) + b*F(n-2) + c*F(n-3)   for n >= 3 
//                                 . . . . . Equation (1)
//where a, b and c are constants. 
//
//For this recurrence relation it depends on three previous values. 
//Now we will try to represent Equation (1) in terms of matrix. 
//
//[First Matrix] = [Second matrix] * [Third Matrix]
//| F(n)   |     =   Matrix 'C'    *  | F(n-1) |
//| F(n-1) |                          | F(n-2) |
//| F(n-2) |                          | F(n-3) |
// 
//Dimension of the first matrix is 3 x 1 . 
//Dimension of third matrix is also 3 x 1. 
//
//So the dimension of the second matrix must be 3 x 3 
//[For multiplication rule to be satisfied.]
//
//Now we need to fill the Matrix 'C'. 
//
//So according to our equation. 
//F(n) = a*F(n-1) + b*F(n-2) + c*F(n-3)
//F(n-1) = F(n-1)
//F(n-2) = F(n-2)
//
//C = [a b c
//     1 0 0
//     0 1 0]
//
//Now the relation between matrix becomes : 
//[First Matrix]  [Second matrix]       [Third Matrix]
//| F(n)   |  =  | a b c |  *           | F(n-1) |
//| F(n-1) |     | 1 0 0 |              | F(n-2) |
//| F(n-2) |     | 0 1 0 |              | F(n-3) |
//
//Lets assume the initial values for this case :- 
//F(0) = 0
//F(1) = 1
//F(2) = 1
//
//So, we need to get F(n) in terms of these values.
//
//So, for n = 3 Equation (1) changes to 
//| F(3) |  =  | a b c |  *           | F(2) |
//| F(2) |     | 1 0 0 |              | F(1) |
//| F(1) |     | 0 1 0 |              | F(0) |
//
//Now similarly for n = 4 
//| F(4) |  =  | a b c |  *           | F(3) |
//| F(3) |     | 1 0 0 |              | F(2) |
//| F(2) |     | 0 1 0 |              | F(1) |
//
//             - - - -  2 times - - -
//| F(4) |  =  | a b c |  * | a b c | *       | F(2) |
//| F(3) |     | 1 0 0 |    | 1 0 0 |         | F(1) |
//| F(2) |     | 0 1 0 |    | 0 1 0 |         | F(0) |
//
//
//So for n, the Equation (1) changes to 
//
//                - - - - - - - - n -2 times - - - -  -       
//| F(n)   |  =  | a b c | * | a b c | * ... * | a b c | * | F(2) |
//| F(n-1) |     | 1 0 0 |   | 1 0 0 |         | 1 0 0 |   | F(1) |
//| F(n-2) |     | 0 1 0 |   | 0 1 0 |         | 0 1 0 |   | F(0) |
//
//
//| F(n)   |  =  [ | a b c | ] ^ (n-2)   *  | F(2) |
//| F(n-1) |     [ | 1 0 0 | ]              | F(1) |
//| F(n-2) |     [ | 0 1 0 | ]              | F(0) |
//
//public class FibonacciSequence {
//	static int[][] fib(int n, int m) {
//		int F[][] = new int[][] { { 1, 1 }, { 1, 0 } };
//
//		int x[][] = new int[][] { { 0, 0 }, { 0, 0 } };
//		if (n == 0)
//			return x;
//		F = power(F, n, m);
//		return F;
//	}
//
//	static int[][] multiply(int F[][], int M[][], int m) {
//		long a = F[0][0], b = F[0][1], c = F[1][0], d = F[1][1];
//		long e = M[0][0], f = M[0][1], g = M[1][0], h = M[1][1];
//		int[][] ans = new int[2][2];
//		ans[0][0] = (int) (((a * e) % m + (b * g) % m) % m);
//		ans[0][1] = (int) (((a * f) % m + (b * h) % m) % m);
//		ans[1][0] = (int) (((c * e) % m + (d * g) % m) % m);
//		ans[1][1] = (int) (((c * f) % m + (d * h) % m) % m);
//		return ans;
//	}
//
//	static int[][] power(int F[][], int n, int m) {
//		if (n == 0 || n == 1)
//			return F;
//		int M[][] = new int[][] { { 1, 1 }, { 1, 0 } };
//
//		F = power(F, n / 2, m);
//		F = multiply(F, F, m);
//
//		if (n % 2 != 0)
//			F = multiply(F, M, m);
//		return F;
//	}
//
//	// Driver code
//	public static void main(String[] args) {
//
//		int n = 5;
//
//		for (int i = 0; i < 100; i++) {
//			
//			/* the fib function returns the following results:
//			 *   | f(n) 	f(n-1) |
//			 *   | f(n-1)	f(n-2) |
//			 * 
//			 */
//			System.out.println(fib(i, 1000000007)[0][0]);    
//
//		}
//	}
//
//}

public class FibonacciSequence {
	
    static void multiply(int a[][], int b[][], int m) 
    { 
        int mul[][] = new int[3][3]; 
        for (int i = 0; i < 3; i++) 
        { 
            for (int j = 0; j < 3; j++) 
            { 
                mul[i][j] = 0; 
                for (int k = 0; k < 3; k++){ 
                    mul[i][j] += (a[i][k] * b[k][j])%m;
                    mul[i][j] = (mul[i][j])%m;   
                }
                mul[i][j] = (mul[i][j])%m;
            } 
            
        } 
     
        for (int i=0; i<3; i++) 
            for (int j=0; j<3; j++) 
                a[i][j] = mul[i][j];  
    } 
      
    // Function to compute F raise to 
    // power n-2. 
    static int power(int F[][], int n,int m) 
    { 
        int M[][] = {{1, 0, 1}, {1, 0, 0}, 
                               {0, 1, 0}}; 
      
        if (n == 1 || n==0) 
            return (F[0][0] + F[0][1])%m; 
        power(F, n / 2,m); 
        multiply(F, F,m); 
        
        if (n%2 != 0) 
            multiply(F, M,m);
        
        return (F[0][0] + F[0][1])%m ; 
    } 
    
    static int solve(int n,int mod) 
    { 
    	int F[][] = {{1, 0, 1}, {1, 0, 0}, 
                {0, 1, 0}} ; 
        return power(F, n-2,mod); 
    } 
      
    // Driver code 
    public static void main (String[] args) { 
          
        int n = 5; 
        for( int i=0  ;i <10 ; i++){
        	if( i==0 )
        		System.out.println(0);
        	if( i==1 )
        		System.out.println(1);
        	if( i==2 )
        		System.out.println(1);
        	if( i==3 )
        		System.out.println(2);
        	if( i>4 )
        		System.out.println(solve(i,1000000007));
        	
        }
    } 

}