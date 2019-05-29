package com.programming.competitions;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;


class ex2 {
	static long mod = 1000000007;
	static int block = 320;

	static boolean debug = false;
	
	static long segmentTree[] = new long[5000005] ;

	public static void main(String[] args) throws Exception {
		
		InputReader in = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);

		int n , q ;
		n = in.readInt();
		q = in.readInt();
		
		long ar[] = new long[500005];
		
		
		build(ar,0,n-1,1);
		if(debug){
			for(int j=0; j<20;j++){
				System.out.print(segmentTree[j] + " ");
			}
			System.out.println();	
		}
		for(int i = 0; i< q; i++){
			
			int type,x,y ;
			type = in.readInt();
			switch(type){
				case 1 : x = in.readInt();
						update(ar,x-1,0,n-1,1,type);
						break;
				case 2 : x = in.readInt();
						update(ar,x-1,0,n-1,1,type);
						break;
				case 3 : x = in.readInt();
						 y = in.readInt();
						 pw.println(query(0,n-1,x-1,y-1,1));
			}
			if(debug){
				for(int j=0; j<n;j++){
					System.out.print(ar[j] + " ");
				}
				System.out.println();
				for(int j=0; j<20;j++){
					System.out.print(segmentTree[j] + " ");
				}
				System.out.println();
			}
			
		}
		
		
		pw.close();
	}
	static int mini = Integer.MAX_VALUE;
	
	public static void build(long a[], int l , int r , int node){
		
		if( l == r ){
			segmentTree[node] = count1s(a[l]) ;
			return ;
		}
		
		build(a,l, (l+r)/2 , 2*node);
		build(a,(l+r)/2+1,r, 2*node+1);
		
		segmentTree[node] = segmentTree[2*node] + segmentTree[2*node+1] ;
		return;
		
	}
	
	public static long query( int l , int r , int x, int y , int node ){
		
        if( x > r || y < l ) // range x,y is completely outside of l,r
            return 0;
        if( x <= l && r <= y ) // range x,y is completely inside of l,r
            return segmentTree[ node ];
        
        long p1 = query( l , ( l + r ) / 2 , x , y , 2*node) ;
        long p2 = query( ( l + r ) / 2 + 1 , r , x , y , 2*node + 1) ;
        
        return p1+p2;
		
	}
	
	public static void update( long a[], int pos , int l , int r , int node , int type ){
		
		if( pos > r || pos < l ) return; // Since there is no way we will reach the leaf node of pos 
		
		if( l == r ){ //update the leaf node
			if(type==1){
			    a[pos] = 2*a[pos] + 1;
				segmentTree[node] = count1s(a[pos]) ;
			}
			else{
			    a[pos] = (long)a[pos]/2 ;
				segmentTree[node] = count1s(a[pos]) ;
			}
		}
		else{
			update(a,pos,l, (l+r)/2 , 2*node,type);
			update(a,pos, (l+r)/2+1 , r , 2*node+1,type);
			segmentTree[node] = segmentTree[node*2]+segmentTree[node*2+1];
		}
	}
	
	
	public static int count1s(long n)
	{
	    int count = 0;
	    while (n!=0)
	    {
	      count += n & 1;
	      n >>= 1;
	    }
	    return count;
	}
	 

	private static int getMin(int[][] acount,int left ,int right, int n,int l) {
		
		int ar[] = new int[26];
		//System.out.println("inside " + left + " - " + right );
		
		if(right >=l || left > (l-n) )return mini;
		
		else if( mini == n )return mini;
		else{
			
			for(int i = 0 ; i < 26 ; i++){
				
				ar[i] = acount[right+1][i] - acount[left][i]; 
				
			}
			int count = (getPalins(ar)) ;
			//System.out.println("and we found palins as "+ count);
			if(count>=n) mini = Math.min(mini,right-left+1 );
			//System.out.println("mini updated to " + mini);
			if( count >= n && right - left + 1 == n ) return n;
			//else if( mini <= (right+2-left) ) return getMin(acount,left+1, right+1, n, l);
			else if( count<n ) return getMin(acount, left, right+1, n,l);
			else return getMin( acount, left+1, right, n, l);
			
		}
	}

	private static int getPalins(int[] acount) {
		
		int evens = 0;
		int odds = 0 ;
		for( int i = 0 ; i< 26; i++){
			
			if( acount[i] > 0 ){
				evens += acount[i] - acount[i]%2;
				odds += acount[i] % 2;
			}
			
		}
		
		return (evens + ((odds>0)?1:0));
	}

	private static boolean powerOf2(long number){
	     return (number > 0) && ((number & (number - 1)) == 0);
	 }
	static int lower_bound(long ar[],int  l , int h , long n){
		
		int m = (l+h)/2;
		
		if( l ==h || l>h ){
			return l;
		}
		if( ar[m] > n ){
			
			return lower_bound(ar,l,m-1,n );
			
		}
		else return lower_bound( ar, m, h, n );
		
	
		
	}
	
	
	
	static int mul_inv(int a, int b) {

		int b0 = b, t, q;
		int x0 = 0, x1 = 1;
		if (b == 1)
			return 0;
		while (a > 1) {

			q = a / b;
			t = b;
			b = a % b;
			a = t;
			t = x0;
			x0 = x1 - q * x0;
			x1 = t;

		}
		if (x1 < 0)
			x1 += b0;
		return x1;
	}

	public static long gcd(long x, long y) {
		if (x % y == 0)
			return y;
		else
			return gcd(y, x % y);
	}

	public static int gcd(int x, int y) {
		if (x % y == 0)
			return y;
		else
			return gcd(y, x % y);
	}

	public static int abs(int a, int b) {
		return (int) Math.abs(a - b);
	}

	public static long abs(long a, long b) {
		return (long) Math.abs(a - b);
	}

	public static int max(int a, int b) {
		if (a > b)
			return a;
		else
			return b;
	}

	public static int min(int a, int b) {
		if (a > b)
			return b;
		else
			return a;
	}

	public static long max(long a, long b) {
		if (a > b)
			return a;
		else
			return b;
	}

	public static long min(long a, long b) {
		if (a > b)
			return b;
		else
			return a;
	}

	public static long pow(long n, long p, long m) {
		long result = 1;
		if (p == 0)
			return 1;
		if (p == 1)
			return n;
		while (p != 0) {
			if (p % 2 == 1)
				result *= n;
			if (result >= m)
				result %= m;
			p >>= 1;
			n *= n;
			if (n >= m)
				n %= m;
		}
		return result;
	}

	public static long pow(long n, long p) {
		long result = 1;
		if (p == 0)
			return 1;
		if (p == 1)
			return n;
		while (p != 0) {
			if (p % 2 == 1)
				result *= n;
			p >>= 1;
			n *= n;
		}
		return result;

	}

	static class Pair implements Comparable<Pair> {
		int a, b, c;

		Pair(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			if (this.a / block != o.a / block)
				return Integer.compare(this.a / block, o.a / block);
			else if (this.b != o.b)
				return Integer.compare(this.b, o.b);
			else
				return Integer.compare(this.c, o.c);
			// return 0;
		}

		public boolean equals(Object o) {
			if (o instanceof Pair) {
				Pair p = (Pair) o;
				return p.a == a && p.b == b && p.c == c;
			}
			return false;
		}

		public int hashCode() {
			return new Integer(a).hashCode() * 31 + new Integer(b).hashCode() + new Integer(c).hashCode() * 37;
		}

	}

	static long sort(int a[]) {
		int n = a.length;
		int b[] = new int[n];
		return mergeSort(a, b, 0, n - 1);
	}

	static long mergeSort(int a[], int b[], long left, long right) {
		long c = 0;
		if (left < right) {
			long mid = left + (right - left) / 2;
			c = mergeSort(a, b, left, mid);
			c += mergeSort(a, b, mid + 1, right);
			c += merge(a, b, left, mid + 1, right);
		}
		return c;
	}

	static long merge(int a[], int b[], long left, long mid, long right) {
		long c = 0;
		int i = (int) left;
		int j = (int) mid;
		int k = (int) left;
		while (i <= (int) mid - 1 && j <= (int) right) {
			if (a[i] <= a[j]) {
				b[k++] = a[i++];
			} else {
				b[k++] = a[j++];
				c += mid - i;
			}
		}
		while (i <= (int) mid - 1)
			b[k++] = a[i++];
		while (j <= (int) right)
			b[k++] = a[j++];
		for (i = (int) left; i <= (int) right; i++)
			a[i] = b[i];
		return c;
	}

	static class InputReader {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		private SpaceCharFilter filter;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int read() {
			if (numChars == -1)
				throw new InputMismatchException();
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		public int readInt() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public String readString() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		public String readLine() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isEndOfLine(c));
			return res.toString();
		}

		public double readDouble() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			double res = 0;
			while (!isSpaceChar(c) && c != '.') {
				if (c == 'e' || c == 'E')
					return res * Math.pow(10, readInt());
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			}
			if (c == '.') {
				c = read();
				double m = 1;
				while (!isSpaceChar(c)) {
					if (c == 'e' || c == 'E')
						return res * Math.pow(10, readInt());
					if (c < '0' || c > '9')
						throw new InputMismatchException();
					m /= 10;
					res += (c - '0') * m;
					c = read();
				}
			}
			return res * sgn;
		}

		public long readLong() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public boolean isSpaceChar(int c) {
			if (filter != null)
				return filter.isSpaceChar(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public String next() {
			return readString();
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}

		public boolean isEndOfLine(int c) {
			return c == '\n' || c == '\r' || c == -1;
		}
	}

	// BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	// StringBuilder sb=new StringBuilder("");
	// InputReader in = new InputReader(System.in);
	// PrintWriter pw=new PrintWriter(System.out);
	// String line=br.readLine().trim();

	// int t=Integer.parseInt(br.readLine());
	// while(t-->0)
	// {
	// int n=Integer.parseInt(br.readLine());
	// long n=Long.parseLong(br.readLine());
	// String l[]=br.readLine().split(" ");
	// int m=Integer.parseInt(l[0]);
	// int k=Integer.parseInt(l[1]);
	// String l[]=br.readLine().split(" ");
	// l=br.readLine().split(" ");
	/*
	 * int a[]=new int[n]; for(int i=0;i<n;i++) { a[i]=Integer.parseInt(l[i]); }
	 */
	// System.out.println(" ");

	// }

	//// ---------------------------------------------------------------------

	
	
	
	
	
}
