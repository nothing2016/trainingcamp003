package class04;

/**
 * 最长递增子序列的解法O(nlogN)
 */
public class Code04_LIS {

	public static int[] lis1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		int[] dp = getdp1(arr);
		return generateLIS(arr, dp);
	}

	public static int[] getdp1(int[] arr) {
		int[] dp = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		return dp;
	}

	public static int[] generateLIS(int[] arr, int[] dp) {
		int len = 0;
		int index = 0;
		for (int i = 0; i < dp.length; i++) {
			if (dp[i] > len) {
				len = dp[i];
				index = i;
			}
		}
		int[] lis = new int[len];
		lis[--len] = arr[index];
		for (int i = index; i >= 0; i--) {
			if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {
				lis[--len] = arr[i];
				index = i;
			}
		}
		return lis;
	}

	public static int[] lis2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		int[] dp = getdp2(arr);
		return generateLIS(arr, dp);
	}

	public static int[] getdp2(int[] arr) {
		int[] dp = new int[arr.length];
		// ends数组就是为了使用二分加速的
		// ends[i] 表示所有长度为i+1长度的递增子序列结尾的值为ends[i]
		// 这里的值一定是 下标递增  并且值也递增的
		int[] ends = new int[arr.length];
		ends[0] = arr[0];
		dp[0] = 1;
		int right = 0; // 0....right 是 ends的有效区内,   right往右无效
		int l = 0;
		int r = 0;
		int m = 0;
		for (int i = 1; i < arr.length; i++) {
			// 有效区内找到大于等于arr[i]的最左位置
			l = 0;
			r = right;
			// 最左位置用mostLeft标记
			int mostLeft = r + 1;
			int target = arr[i];
			while(l <=r){
				int mid = (l + r)/2;
				if(target<=ends[mid]){
					mostLeft = mid;
					r = mid -1;
				}else{
					l = mid + 1;
				}
			}
			// 扩展有效区
			right = Math.max(right, mostLeft);
			ends[mostLeft] = arr[i];
			// 最右位置ans左边包含自己在内有多少个数，就是dp[i]的长度
			dp[i] = mostLeft + 1;
//			while (l <= r) {
//				m = (l + r) / 2;
//				if (arr[i] > ends[m]) {
//					l = m + 1;
//				} else {
//					r = m - 1;
//				}
//			}
//			// l -> right+1
//			right = Math.max(right, l);
//			ends[l] = arr[i];
//			dp[i] = l + 1;
		}
		return dp;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = { 2, 1, 5, 3, 6, 4, 8, 9, 7 };
		printArray(arr);
		printArray(lis1(arr));
		printArray(lis2(arr));

	}
}