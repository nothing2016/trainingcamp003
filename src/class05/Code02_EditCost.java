package class05;

/**
 * 编辑字符串花最小代价问题
 */
public class Code02_EditCost {

	/**
	 * O(N * M)
	 * @param s1 原字符串
	 * @param s2 新字符串
	 * @param ic 插入代价
	 * @param dc 删除代价
	 * @param rc 替换代价
	 * @return
	 */
	public static int minCost1(String s1, String s2, int ic, int dc, int rc) {
		if (s1 == null || s2 == null) {
			return 0;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int N = str1.length + 1;
		int M = str2.length + 1;
		// dp[i][j] 表示长度为i的str1变成长度为j的str2的代价，所以N 是length + 1
		int[][] dp = new int[N][M];
		// 零行零列表示变成空字符串时的代价
		// dp[0][0]  = 0  空字符串变成空字符串的代价是copy代价，成本定义为0
		for (int i = 1; i < N; i++) {
			// 将长度为i的字符串变成空字符串，肯定是i个删除代价
			dp[i][0] = dc * i;
		}
		for (int j = 1; j < M; j++) {
			// 将空字符串变成长度为j的字符串，肯定是j个添加代价
			dp[0][j] = ic * j;

		}
		// 例如： abc -> acf, 前面填好了第0行和第0列，现在从第一行开始填
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				// 如果 a == a,那么就是copy,没有代价
				if (str1[i - 1] == str2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					// 如果是b -> c， 就是替换的代价
					dp[i][j] = dp[i - 1][j - 1] + rc;
				}
				// 如果i = 2 && j =2 ,那么目的就是将 ab -> ac
				// ab -> a  然后 再在a后添加一个c,就是一个添加的代价
				dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
				// a -> ac   然后那么原来的ab就变成acb,这样需要删除b,就是需要一个删除的代价， min都是求最小代价
				dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
			}
		}
		return dp[N - 1][M - 1];
	}

	public static int minCost2(String str1, String str2, int ic, int dc, int rc) {
		if (str1 == null || str2 == null) {
			return 0;
		}
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		char[] longs = chs1.length >= chs2.length ? chs1 : chs2;
		char[] shorts = chs1.length < chs2.length ? chs1 : chs2;
		if (chs1.length < chs2.length) {
			int tmp = ic;
			ic = dc;
			dc = tmp;
		}
		int[] dp = new int[shorts.length + 1];
		for (int i = 1; i <= shorts.length; i++) {
			dp[i] = ic * i;
		}
		for (int i = 1; i <= longs.length; i++) {
			int pre = dp[0];
			dp[0] = dc * i;
			for (int j = 1; j <= shorts.length; j++) {
				int tmp = dp[j];
				if (longs[i - 1] == shorts[j - 1]) {
					dp[j] = pre;
				} else {
					dp[j] = pre + rc;
				}
				dp[j] = Math.min(dp[j], dp[j - 1] + ic);
				dp[j] = Math.min(dp[j], tmp + dc);
				pre = tmp;
			}
		}
		return dp[shorts.length];
	}

	public static void main(String[] args) {
		String str1 = "ab12cd3";
		String str2 = "abcdf";
		System.out.println(minCost1(str1, str2, 5, 3, 2));
		System.out.println(minCost2(str1, str2, 5, 3, 2));

		str1 = "abcdf";
		str2 = "ab12cd3";
		System.out.println(minCost1(str1, str2, 3, 2, 4));
		System.out.println(minCost2(str1, str2, 3, 2, 4));

		str1 = "";
		str2 = "ab12cd3";
		System.out.println(minCost1(str1, str2, 1, 7, 5));
		System.out.println(minCost2(str1, str2, 1, 7, 5));

		str1 = "abcdf";
		str2 = "";
		System.out.println(minCost1(str1, str2, 2, 9, 8));
		System.out.println(minCost2(str1, str2, 2, 9, 8));

	}

}
