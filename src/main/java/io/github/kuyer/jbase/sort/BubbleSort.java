package io.github.kuyer.jbase.sort;

/**
 * 冒泡排序 它是一种较简单的排序算法。
 * 它会遍历若干次要排序的数列，每次遍历时，它都会从前往后依次的比较相邻两个数的大小；
 * 如果前者比后者大，则交换它们的位置。这样，一次遍历之后，最大的元素就在数列的末尾。
 * 采用相同的方法再次遍历时，第二大的元素就被排列在最大元素之前。
 * 重复此操作，直到整个数列都有序为止。
 * 冒泡排序的时间复杂度是O(N2)。
 * 假设被排序的数列中有N个数。遍历一趟的时间复杂度是O(N)，需要遍历多少次呢？N-1次！因此，冒泡排序的时间复杂度是O(N2)。
 * @author rory.zhang
 */
public class BubbleSort {
	
	/**
	 * 冒泡排序
	 * @param arr 待排序数组
	 */
	public static void bubbleSort(int[] arr) {
		int len = arr.length;
		int i, j;
		for(i=len-1; i>0; i--) {
			for(j=0; j<i; j++) {
				if(arr[j] > arr[j+1]) {
					int tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
				}
			}
		}
	}
	
	/**
	 * 冒泡排序，优化版
	 * @param arr 待排序数组
	 */
	public static void bubbleSortOptimize(int[] arr) {
		int len = arr.length;
		int i, j;
		for(i=len-1; i>0; i--) {
			int flag = 0;//标记
			for(j=0; j<i; j++) {
				if(arr[j] > arr[j+1]) {
					int tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
					flag = 1;//数组发生了交换， 则标记为1
				}
			}
			if(flag == 0) {
				break;//若数组没有发生交换，则说明数组已有序
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = {32, 12, 43, 62, 21, 9, 28};
		System.out.print("排序前：");
		for(int i : arr) {
			System.out.print(i+" ");
		}
		System.out.println();
		//bubbleSort(arr);
		bubbleSortOptimize(arr);
		System.out.print("排序后：");
		for(int i : arr) {
			System.out.print(i+" ");
		}
	}

}
