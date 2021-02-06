package class07;

/**
 * 给定一个无序数组，问要对最少多长的子数组进行排序，使得整体有序
 *
 * 如：[1,2,3,4, 7,6,8,9,10]  那么 [7,6]就是需要排序的最短子数组
 */
public class Code01_MinLengthForSort {

	public static int getMinLength(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int min = arr[arr.length - 1];
		int noMinIndex = -1;
		for (int i = arr.length - 2; i != -1; i--) {
			if (arr[i] > min) {
				noMinIndex = i;
			} else {
				min = Math.min(min, arr[i]);
			}
		}
		if (noMinIndex == -1) {
			return 0;
		}
		int max = arr[0];
		int noMaxIndex = -1;
		for (int i = 1; i != arr.length; i++) {
			if (arr[i] < max) {
				noMaxIndex = i;
			} else {
				max = Math.max(max, arr[i]);
			}
		}
		return noMaxIndex - noMinIndex + 1;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19 };
		System.out.println(getMinLength(arr));

	}

}
