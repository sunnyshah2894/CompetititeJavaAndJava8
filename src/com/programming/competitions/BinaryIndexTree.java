package com.programming.competitions;

public class BinaryIndexTree {

	static final int NO = 100005;
	static long tree[] = new long[NO << 1];

	// function to update the given BIT where idx gives the index to be updated
	// val denotes the value by which it should be incremented.
	// n denotes the number of buckets
	public static void update(int idx, int val, int n) {
		while (idx <= n) {
			tree[idx] += val;
			idx += (idx & -idx);
		}
	}

	// function to read the cumulative frequency of the given index.
	// It will return the actual value or actual number of balls bucket before
	// that
	// index and including that index contain.
	public static long read(int idx) {
		long sum = 0;
		while (idx > 0) {
			sum += tree[idx];
			idx -= (idx & -idx);
		}
		return sum;
	}
	
    // function to get the actual freqency at index idx. It will give
    // how much no of balls the current bucket contain.
    public static long readSingle(int idx){
        long sum = tree[idx];          // sum will be decreased
        if (idx > 0){                       // special case
            int z = idx - (idx & -idx);     // make z first
            idx--;                          // idx is no important any more, so instead y, you can use idx
            while (idx != z){               // at some iteration idx (y) will become z
                sum -= tree[idx];           // substruct tree frequency which is between y and "the same path"
                idx -= (idx & -idx);
            }
        }
        return sum;
    }

    public static void updateRange(int u , int v , int val , int n){
        update( u , val , n );
        update( v+1 , -val , n );
    }
    
    public static void main(){
    	int n = 100 ; 	//no of buckets
    	update(1,0,n) ; //put 0 balls into each bucket
    	
    	update( 4 , 10 , n  ); // add 10 balls in 4th bucket
    	
    	updateRange( 1 , 10 , 20 , n); // add 20 balls in buckets numbered from 1 to 10
    	
    }
    
    
}
