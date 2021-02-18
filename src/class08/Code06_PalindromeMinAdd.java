package class08;

/**
 * 可以在字符串的任意位置添加字符串，问要使得整个字符串是回文，最少要添加几个字符？
 */
public class Code06_PalindromeMinAdd {

	/**
	 * 还原得到最后的串是什么
	 * @param str
	 * @return
	 */
	public static String getPalindrome1(String str) {
		if (str == null || str.length() < 2) {
			return str;
		}
		char[] chas = str.toCharArray();
		int[][] dp = getDP(chas);
		char[] res = new char[chas.length + dp[0][chas.length - 1]];
		int i = 0;
		int j = chas.length - 1;
		int resl = 0;
		int resr = res.length - 1;
		while (i <= j) {
			if (chas[i] == chas[j]) {
				res[resl++] = chas[i++];
				res[resr--] = chas[j--];
			} else if (dp[i][j - 1] < dp[i + 1][j]) {
				res[resl++] = chas[j];
				res[resr--] = chas[j--];
			} else {
				res[resl++] = chas[i];
				res[resr--] = chas[i++];
			}
		}
		return String.valueOf(res);
	}

	/**
	 * 动态规划找到需要添加串的数量
	 * @param str
	 * @return
	 */
	public static int[][] getDP(char[] str) {
		// dp[i][j] 表示要使得[i,j]是回文，要至少添加几个字符
		int[][] dp = new int[str.length][str.length];
		// 对角线 i==j，字符本身就是回文，需要添加0个字符
		for (int j = 1; j < str.length; j++) {
			// 长度为2的字符，如果两相等就是0，不相等添加一个即可
			dp[j - 1][j] = str[j - 1] == str[j] ? 0 : 1;
			for (int i = j - 2; i > -1; i--) {
				if (str[i] == str[j]) {
					dp[i][j] = dp[i + 1][j - 1];
				} else {
					dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
				}
			}
		}
		return dp;
	}

	public static void main(String[] args) {
		String str = "AB1CD2EFG3H43IJK2L1MN";
		System.out.println(getPalindrome1(str));
	}

}
