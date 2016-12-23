package io.github.kuyer.jbase.sort.tree;

/**
 * 判断序列是否二叉树的后续遍历结果
 * @author rory.zhang
 */
public class PostTraverseBinaryTree {
	
	private static boolean isPostTraverseBinaryTree(int[] arr) {
		if(null == arr) {
			return false;
		}
		if(arr.length<=2) {
			return true;
		}
		return isPostTraverseBinaryTree(arr, 0, arr.length-1);
	}
	
	private static boolean isPostTraverseBinaryTree(int[] arr, int start, int end) {
		int len = arr.length;
		if(!(start>=0 && start<len && end>=0 && end<len)) {
			return false;
		}
		if(start==end || start==end-1) {
			return true;
		}
		int i = end-1;
		while(arr[i]>arr[end]) {
			i--;
		}
		if(start<=i && i<end) {
			boolean isFirstPart = isPostTraverseBinaryTree(arr, start, i);
			boolean secondPart = isPostTraverseBinaryTree(arr, i+1, end-1);
			return isFirstPart && secondPart;
		}
		return false;
	}

	public static void main(String[] args) {
		int[][] arrs = {
				{5,7,6,9,11,10,8},
				{5,6,7},
				{5,7,6},
				{1,3,2,5,7,6,4,9,11,10,13,15,14,12,8}
		};
		for(int[] arr : arrs) {
			System.out.println(isPostTraverseBinaryTree(arr));
		}
	}

}
