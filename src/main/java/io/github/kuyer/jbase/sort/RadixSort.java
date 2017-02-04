package io.github.kuyer.jbase.sort;

/**
 * 基数排序
 * @author rory.zhang
 */
public class RadixSort {

	public static void main(String[] args) {
		int[] arr = {45, 8, 147, 3, 74, 21, 233, 71};
		System.out.println("排序前：");
		for(int i=0; i<arr.length; i++) {
			System.out.printf("%d ", arr[i]);
		}
		System.out.println();
		radixSort(arr);
		System.out.println("排序后：");
		for(int i=0; i<arr.length; i++) {
			System.out.printf("%d ", arr[i]);
		}
	}
	
	private static int getMax(int[] arr) {
		int max = 0;
		for(int i : arr) {
			if(i>max) {
				max = i;
			}
		}
		return max;
	}

	private static void radixSort(int[] arr) {
		int max = getMax(arr);
		for(int exp = 1; max/exp > 0; exp *= 10) {
			countSort(arr, exp);
		}
	}

	private static void countSort(int[] arr, int exp) {
		int[] output = new int[arr.length];
		int[] buckets = new int[10];
		
		for(int i=0; i<arr.length; i++) {
			buckets[(arr[i]/exp)%10] ++;
		}
		
		for(int i=1; i<10; i++) {
			buckets[i] += buckets[i-1];
		}
		
		for(int i=arr.length-1; i>=0; i--) {
			output[buckets[(arr[i]/exp)%10]-1] = arr[i];
			buckets[(arr[i]/exp)%10] --;
		}
		
		for(int i=0; i<arr.length; i++) {
			arr[i] = output[i];
		}
	}

}
