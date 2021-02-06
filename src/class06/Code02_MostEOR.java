package class06;

import java.util.HashMap;

/**
 * 最多异或和为0的分组数
 * 给定一个数组，我们总是能找到这样一个分组，使得里面的异或和为零分组最多（当然有些分组不一定为零），返回最大的都为零的組数
 * 例如  [4,1,2,3,0,5,4,1,9] ,这样的分组是最多的，如[4,1,2,3][0][5,4,1][9],这样答案就是3，因为9是不为零的
 */
public class Code02_MostEOR {

	public static int mostEOR(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		// i 为最后一组的成员时，得到的答案就是dp[i]
		int[] dp = new int[N]; // dp[i] = 0
		// key: 某个前缀异或和
		// value: 前缀异或和最晚出现的位置
		HashMap<Integer, Integer> map = new HashMap<>();
		// 补充一位，前缀和为0的最晚出现就是在-1位置(虚拟的位置)
		map.put(0, -1);
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum ^= arr[i];
			if (map.containsKey(sum)) {
				// 假设范围为[0...j...i]，0...i上的异或和为sum, 0...j上的异或和也为sum,那么j+1...i上的异或和就一定为0
				int pre = map.get(sum);
				dp[i] = pre == -1 ? 1 : (dp[pre] + 1);
			}
			if (i > 0) {
				// 如果arr[i]作为最后一部分，且异或和不为0，那么就去前一部分的值即可
				dp[i] = Math.max(dp[i - 1], dp[i]);
			}
			// 更新最晚出现的位置
			map.put(sum, i);
		}
		return dp[dp.length - 1];
	}

	// for test
	public static int comparator(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int[] eors = new int[arr.length];
		int eor = 0;
		for (int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
			eors[i] = eor;
		}
		int[] mosts = new int[arr.length];
		mosts[0] = arr[0] == 0 ? 1 : 0;
		for (int i = 1; i < arr.length; i++) {
			mosts[i] = eors[i] == 0 ? 1 : 0;
			for (int j = 0; j < i; j++) {
				if ((eors[i] ^ eors[j]) == 0) {
					mosts[i] = Math.max(mosts[i], mosts[j] + 1);
				}
			}
			mosts[i] = Math.max(mosts[i], mosts[i - 1]);
		}
		return mosts[mosts.length - 1];
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random());
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int res = mostEOR(new int[]{4,1,2,3,0,5,4,1,9});
		System.out.println(res);
//		int testTime = 500000;
//		int maxSize = 300;
//		int maxValue = 100;
//		boolean succeed = true;
//		for (int i = 0; i < testTime; i++) {
//			int[] arr = generateRandomArray(maxSize, maxValue);
//			int res = mostEOR(arr);
//			int comp = comparator(arr);
//			if (res != comp) {
//				succeed = false;
//				printArray(arr);
//				System.out.println(res);
//				System.out.println(comp);
//				break;
//			}
//		}
//		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}

}
