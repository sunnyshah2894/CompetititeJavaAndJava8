package com.programming.competitions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;


class Main {
	static long mod = 1000000007;
	static int block = 320;

	static long fibMods[] = new long[100006];
	static Map<Long,Integer> mp = new HashMap<>();

	public static void calculateFibs() {

		fibMods[1] = 1;
		fibMods[2] = 1;
		for (int i = 3; i < 100005; i++) {
			fibMods[i] = (fibMods[i - 1] + fibMods[i - 2]) % mod;
		}
	}
	
	static final int NO = 100005;
	static long segment[] = new long[NO << 2];
	static long lazy[] = new long[NO << 2];
	
	public static void main(String[] args) throws Exception {

//		InputReader in = new InputReader( new FileInputStream("/Users/sunnyshah/Documents/workspace/DemoJava8/src/com/programming/competitions/input.txt") );
//		PrintWriter pw = new PrintWriter( new FileOutputStream("/Users/sunnyshah/Documents/workspace/DemoJava8/src/com/programming/competitions/output_shiny_selfies.txt"));
	
		InputReader in = new InputReader( System.in );
		PrintWriter pw = new PrintWriter( System.out );
		
		int tc = 1;
		tc = in.readInt();
		HashSet<Integer> hs[] = new HashSet[100006];
		HashMap<String, Integer> hp = new HashMap<>();
		
		for( int i=0 ; i<100006 ; i++ ){
			hs[i] = new HashSet<>();
		}
		
		List<Integer> hor = new ArrayList<>();
		List<Integer> ver = new ArrayList<>();
		
		int index = 0;
		int tagIndex = 0;
		
		while( tc-->0 ){
			
			String ch = in.readString();
			if( ch.charAt(0) == 'H' ){
				hor.add(index);
			}
			else{
				ver.add(index);
			}
			int numOfTags = in.readInt();
			
			for( int i=0 ; i<numOfTags ; i++ ){
				
				String str = in.readString();
				int id = 0 ;
				if( hp.containsKey(str) ){
					id = hp.get(str);
				}
				else{
					hp.put(str,tagIndex);
					id = tagIndex;
					tagIndex++;
				}
				hs[index].add(id);
				
			}
			index++;
			
		}
		pw.println( "" + ( hor.size() + ver.size()/2 ) );
		for( int i=0 ; i<hor.size() ; i++ ){
			pw.println(hor.get(i));
		}
		for( int i=0 ; i<ver.size() ; i+=2 ){
			pw.println(ver.get(i) +" "+ ver.get(i+1));
		}

		pw.close();

	}

	private static boolean isPowerOf2(long number) {
		return (number > 0) && ((number & (number - 1)) == 0);
	}

	public static long fib(long n, long a, long b, long c, long d) {
		long f[][] = { { 1, 1 }, { 1, 0 } };
		long g[][] = { { a, b }, { c, d } };

		if (n == 0)
			return 0;
		if (n == 1)
			return b;
		if (n == 2)
			return a;
		power(f, n - 2, a, b, c, d);
		multiply(f, g);

		return f[0][0] % mod;

	}

	public static void multiply(long f[][], long g[][]) {
		long a = ((f[0][0] * g[0][0]) % mod + (f[0][1] * g[1][0]) % mod) % mod;
		long b = ((f[0][0] * g[0][1]) % mod + (f[0][1] * g[1][1]) % mod) % mod;
		long c = ((f[1][0] * g[0][0]) % mod + (f[1][1] * g[1][0]) % mod) % mod;
		long d = ((f[1][0] * g[0][1]) % mod + (f[1][1] * g[1][1]) % mod) % mod;

		f[0][0] = a;
		f[0][1] = b;
		f[1][0] = c;
		f[1][1] = d;

	}

	public static void power(long f[][], long n, long a, long b, long c, long d) {

		long g[][] = new long[2][2];
		g[0][0] = f[0][0];
		g[1][0] = f[1][0];
		g[0][1] = f[0][1];
		g[1][1] = f[1][1];

		if (n == 0 || n == 1)
			return;

		power(f, n / 2, a, b, c, d);
		multiply(f, f);

		if (n % 2 == 1)
			multiply(f, g);

	}

	// If m is prime, then directly calculate the modulo inverse using the
	// following formula:
	// power(a, m-2, m)
	//
	// But if m is not guaranteed to be prime, however a , m are coprime use
	// extended Euclid theoram as below
	//
	// Returns modulo inverse of a with
	// respect to m using extended Euclid
	// Algorithm Assumption: a and m are
	// coprimes, i.e., gcd(a, m) = 1
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

	public static long mulmod(long a, long b, long mod) throws Exception {
		if (a == 0 || b == 0)
			return 0;
		if (b == 1)
			return a;
		long ans = mulmod(a, b / 2, mod);
		ans = (ans * 2) % mod;
		if (b % 2 == 1)
			ans = (a + ans) % mod;
		return ans;
	}

	// sieve
	public static int[] primes(int n) throws Exception { // for(int
															// i=1;i<=arr.length-1;i++)out.write(""+arr[i]+"
															// ");
		boolean arr[] = new boolean[n + 1];
		Arrays.fill(arr, true);
		for (int i = 1; i <= Math.sqrt(n); i++) {
			if (!arr[i])
				continue;
			for (int j = 2 * i; j <= n; j += i) {
				arr[i] = false;
			}
		}
		LinkedList<Integer> ll = new LinkedList<Integer>();
		for (int i = 1; i <= n; i++) {
			if (arr[i])
				ll.add(i);
		}
		n = ll.size();

		int primes[] = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			primes[i] = ll.removeFirst();
		}
		return primes;
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

	/*
	 * -If searched element doesn't exist function returns index of first
	 * element which is bigger than searched value.<br> -If searched element is
	 * bigger than any array element function returns first index after last
	 * element.<br> -If searched element is lower than any array element
	 * function returns index of first element.<br> -If there are many values
	 * equals searched value function returns first occurrence.<br>
	 */
	public static int lowerBound(int[] array, int length, int value) {
		int low = 0;
		int high = length;
		while (low < high) {
			final int mid = (low + high) / 2;
			if (value <= array[mid]) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	/*
	 * Returns index of the first element which is grater than searched value.
	 * If searched element is bigger than any array element function returns
	 * first index after last element
	 */
	public static int upperBound(int[] array, int length, int value) {
		int low = 0;
		int high = length;
		while (low < high) {
			final int mid = (low + high) / 2;
			if (value >= array[mid]) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return low;
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

}
