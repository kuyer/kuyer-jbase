package io.github.kuyer.jbase.sort;

/**
 * 快速排序，使用分治法策略。
 * 它的基本思想是：选择一个基准数，通过一趟排序将要排序的数据分割成独立的两部分；其中一部分的所有数据都比另外一部分的所有数据都要小。
 * 然后，再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 * 排序流程：
 * (1) 从数列中挑出一个基准值。
 * (2) 将所有比基准值小的摆放在基准前面，所有比基准值大的摆在基准的后面(相同的数可以到任一边)；在这个分区退出之后，该基准就处于数列的中间位置。
 * (3) 递归地把"基准值前面的子数列"和"基准值后面的子数列"进行排序。
 * 时间复杂度在最坏情况下是O(N2)，平均的时间复杂度是O(N*lgN)。
 * @author rory.zhang
 */
public class QuickSort {
	
	public static void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length-1);
	}
	
	public static void quickSort(int[] arr, int leftIndex, int rightIndex) {
		if(leftIndex < rightIndex) {
			int i = leftIndex;
			int j = rightIndex;
			int x = arr[i];
			while(i<j) {
				while(i<j && arr[j]>x) {
					j--;
				}
				if(i<j) {
					arr[i++] = arr[j];
				}
				while(i<j && arr[i]<x) {
					i++;
				}
				if(i<j) {
					arr[j--] = arr[i];
				}
			}
			arr[i] = x;
			quickSort(arr, leftIndex, i-1);
			quickSort(arr, i+1, rightIndex);
		}
	}

	public static void main(String[] args) {
		int[] arr = {32, 12, 43, 62, 21, 9, 28};
		System.out.print("排序前：");
		for(int i : arr) {
			System.out.print(i+" ");
		}
		System.out.println();
		quickSort(arr);
		System.out.print("排序后：");
		for(int i : arr) {
			System.out.print(i+" ");
		}
	}

}
