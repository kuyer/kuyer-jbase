package io.github.kuyer.jbase.sort;

public class InsertSort {

	public static void main(String[] args) {
		int[] arr = {45, 8, 147, 3, 74, 21, 233, 71};
		System.out.println("排序前：");
		for(int i=0; i<arr.length; i++) {
			System.out.printf("%d ", arr[i]);
		}
		System.out.println();
		insertSort(arr);
		System.out.println("排序后：");
		for(int i=0; i<arr.length; i++) {
			System.out.printf("%d ", arr[i]);
		}
	}

	private static void insertSort(int[] arr) {
		int len = arr.length;
		for(int i=1; i<len; i++) {
			int index = -1;
			int temp = 0;
			for(int j=0; j<i; j++) {
				if(arr[j] > arr[i]) {
					index = j;
					temp = arr[i];
					break;
				}
			}
			if(index >= 0) {
				for(int k=i; k>index; k--) {
					arr[k] = arr[k-1];
				}
				arr[index] = temp;
			}
		}
	}

}
