// package week4;

// public class interview {

// }

// // the idea is to have two arrays that will give me the two mid points
// // the first array will have the largest value in it (this is the mid point)
// // the second array the min one is the mid point
// class constMedian {
// BinaryHeap<Integer> right;
// BinaryHeap<Integer> left;
// int r;
// int l;

// public constMedian(int capacity) {
// right = new BinaryHeap<Integer>(capacity);
// left = new BinaryHeap<Integer>(capacity);
// }

// public double findMedian() {
// int L = left.size();
// int R = right.size();
// if (L == R)
// return (double) left.max() + (double) right.min() / 2;
// else if (L > R)
// return left.max();
// else
// return right.min();
// }

// public void insert(int key) {
// if (key > findMedian()) {
// // insert right
// right.insert(key);
// if (r - l > 1) {
// left.insert(right.removeMin());
// }

// } else {
// // insert left
// left.insert(key);
// if (l - r > 1) {
// right.insert(left.removeMax());
// }

// }
// }
// }