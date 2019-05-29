package com.programming.competitions;

public class SegmentTree {

	static final int NO = 100005;
	static long segment[] = new long[NO << 2];
	static long lazy[] = new long[NO << 2];

	public static void build(int node, int start, int end, long arrToCreateTree[]) {

		if (start == end) { // reached the leaf node
			segment[node] = arrToCreateTree[start];
			return;
		}

		build(2 * node, start, (start + end) / 2, arrToCreateTree);
		build(2 * node + 1, (start + end) / 2 + 1, end, arrToCreateTree);

		segment[node] = segment[2 * node] + segment[2 * node + 1];

	}

	public static long queryRange(int node, int start, int end, int l, int r, long arrToCreateTree[]) {
		if (start > end || start > r || end < l) // Out of range
			return 0;

		if (lazy[node] != 0) // This node needs to be updated
		{
			segment[node] += (end - start + 1) * lazy[node]; // Update it
			if (start != end) {
				lazy[node * 2] += lazy[node]; // Mark child as lazy
				lazy[node * 2 + 1] += lazy[node]; // Mark child as lazy
			}
			lazy[node] = 0; // Reset it
		}
		if (start >= l && end <= r) // Current segment is totally within range
									// [l, r]
			return segment[node];

		int mid = (start + end) / 2;
		long p1 = queryRange(node * 2, start, mid, l, r, arrToCreateTree); // Query
																			// left
																			// child
		long p2 = queryRange(node * 2 + 1, mid + 1, end, l, r, arrToCreateTree); // Query
																					// right
																					// child
		return (p1 + p2);

	}

	public static void updateRange(int node, int start, int end, int l, int r, int val, long arrToCreateTree[]) {
		if (lazy[node] != 0) // This node needs to be updated
		{
			segment[node] += (end - start + 1) * lazy[node]; // Update it
			if (start != end) {
				lazy[node * 2] += lazy[node]; // Mark child as lazy
				lazy[node * 2 + 1] += lazy[node]; // Mark child as lazy
			}
			lazy[node] = 0; // Reset it
		}
		if (start > end || start > r || end < l) // Current segment is not
													// within range [l, r]
			return;

		if (start >= l && end <= r) // Segment is fully within range, so we can
									// stop and lazy save for its childs
		{
			segment[node] += (end - start + 1) * val;
			if (start != end) // Not leaf node, if leaf we will propogate
			{
				lazy[node * 2] += val;
				lazy[node * 2 + 1] += val;
			}
			return;
		}
		int mid = (start + end) / 2;
		updateRange(node * 2, start, mid, l, r, val, arrToCreateTree); // Updating
																		// left
																		// child
		updateRange(node * 2 + 1, mid + 1, end, l, r, val, arrToCreateTree); // Updating
																				// right
																				// child
		segment[node] = segment[node * 2] + segment[node * 2 + 1]; // Updating
																	// root with
																	// max value

	}

	public static void updateSingle(int node, int start, int end, int idx, int val, long arrToCreateTree[]) {
		if (idx > end || idx < start)
			return;

		if (start == end) // Leaf node
		{
			arrToCreateTree[idx] += val;
			segment[node] += val;
		} else {
			int mid = (start + end) / 2;

			updateSingle(2 * node, start, mid, idx, val, arrToCreateTree);
			updateSingle(2 * node + 1, mid + 1, end, idx, val, arrToCreateTree);

			segment[node] = segment[2 * node] + segment[2 * node + 1];
		}
	}

	public static void main() {
		int n = 100; // no of buckets
		long initialNoOfBalls[] = new long[100];

		build(1, 0, n - 1, initialNoOfBalls); // put 0 balls(initialNoOfBalls)
												// into each bucket

		updateSingle(1, 0, n - 1, 3, 10, initialNoOfBalls); // add 10 balls in
															// 4th bucket

		updateRange(1, 0, n - 1, 0, 9, 20, initialNoOfBalls); // add 20 balls in
																// buckets
																// numbered from
																// 1 to 10

	}

}
