package io.github.kuyer.jbase.sort;

public class BubbleSort1 {

	public static void main(String[] args) {
		int[] arr = {45, 8, 147, 3, 74, 21, 233, 71};
		System.out.println("排序前：");
		for(int i=0; i<arr.length; i++) {
			System.out.printf("%d ", arr[i]);
		}
		System.out.println();
		bubbleSort(arr);
		System.out.println("排序后：");
		for(int i=0; i<arr.length; i++) {
			System.out.printf("%d ", arr[i]);
		}
	}

	private static void bubbleSort(int[] arr) {
		for(int i=arr.length-1; i>=0; i--) {
			for(int j=0; j<i; j++) {
				if(arr[j]>arr[j+1]) {
					int tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
				}
			}
		}
	}

}
