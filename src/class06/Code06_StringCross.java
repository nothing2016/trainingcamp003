package class06;

/**
 * 求ai是否是s1和s2的交错值
 * 如 ai = "1ab2c3"    s1 = "123"  s2 = "abc"
 * 那么ai是s1和s2的交错值，因为s1的每一个字符串都在s2的前面
 * 如ab2c31就不是，因为3到1前面去了
 */
public class Code06_StringCross {

	public static boolean isCross1(String s1, String s2, String ai) {
		if (s1 == null || s2 == null || ai == null) {
			return false;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		char[] aim = ai.toCharArray();
		if (aim.length != str1.length + str2.length) {
			return false;
		}
		// dp[i][j] 表示 str1的长度为i的前缀串 和 str2的长度为j的前缀串 能否构成ai的长度为i+j的前缀串
		boolean[][] dp = new boolean[str1.length + 1][str2.length + 1];
		dp[0][0] = true;
		for (int i = 1; i <= str1.length; i++) {
			if (str1[i - 1] != aim[i - 1]) {
				break;
			}
			dp[i][0] = true;
		}
		for (int j = 1; j <= str2.length; j++) {
			if (str2[j - 1] != aim[j - 1]) {
				break;
			}
			dp[0][j] = true;
		}
		for (int i = 1; i <= str1.length; i++) {
			for (int j = 1; j <= str2.length; j++) {
				
				if (
						// 如果str1的第i个 和 aim的第i+j个是相等的，
					    // 并且str1长度为i-1的前缀串 和 str2长度为i的前缀串 能够组成 aim的长度为 i+j-2的交错值
						(str1[i - 1] == aim[i + j - 1] && dp[i - 1][j])
						
						|| 
						
						(str2[j - 1] == aim[i + j - 1] && dp[i][j - 1])
						
						
				) {
					
					
					dp[i][j] = true;
					
					
				}
				
				
				
			}
		}
		return dp[str1.length][str2.length];
	}

	public static boolean isCross2(String str1, String str2, String aim) {
		if (str1 == null || str2 == null || aim == null) {
			return false;
		}
		char[] ch1 = str1.toCharArray();
		char[] ch2 = str2.toCharArray();
		char[] chaim = aim.toCharArray();
		if (chaim.length != ch1.length + ch2.length) {
			return false;
		}
		char[] longs = ch1.length >= ch2.length ? ch1 : ch2;
		char[] shorts = ch1.length < ch2.length ? ch1 : ch2;
		boolean[] dp = new boolean[shorts.length + 1];
		dp[0] = true;
		for (int i = 1; i <= shorts.length; i++) {
			if (shorts[i - 1] != chaim[i - 1]) {
				break;
			}
			dp[i] = true;
		}
		for (int i = 1; i <= longs.length; i++) {
			dp[0] = dp[0] && longs[i - 1] == chaim[i - 1];
			for (int j = 1; j <= shorts.length; j++) {
				if ((longs[i - 1] == chaim[i + j - 1] && dp[j]) || (shorts[j - 1] == chaim[i + j - 1] && dp[j - 1])) {
					dp[j] = true;
				} else {
					dp[j] = false;
				}
			}
		}
		return dp[shorts.length];
	}

	public static void main(String[] args) {
		String str1 = "1234";
		String str2 = "abcd";
		String aim = "1a23bcd4";
		System.out.println(isCross1(str1, str2, aim));
		System.out.println(isCross2(str1, str2, aim));

	}

}
