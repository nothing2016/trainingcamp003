package class06;

/**
 * 回文最少的部分数
 * 给定一个str,最少用几刀将字符串全部变成回文，求返回的最少部分数
 * 如abcbakkkabcbaff,这里就只是一刀就切成了两部分了，返回2
 */
public class Code07_PMinParts {

	public static int minParts2(String s){
		char[] str = s.toCharArray();
		return process(str,0);
	}

	/**
	 * 求以i为开头到结尾的字符串中，返回的最少回文部分数
	 * @param str
	 * @param i
	 * @return
	 */
	private static int process(char[] str, int i) {
		if(i == str.length){
			return 0;
		}
		int min = Integer.MAX_VALUE;
		for(int j=i;j<str.length;j++){
			if(isP(str,i,j)){
				int p1 = 1 + process(str,j+1);
				min = Math.min(min,p1);
			}
		}
		return min;
	}

	private static boolean isP(char[] str, int i, int j) {
		if(i == j){
			return true;
		}
		while(i <=j){
			if(str[i] != str[j]){
				return false;
			}
			i++;
			j--;
		}
		return true;
	}

	public static int minParts(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		if (s.length() == 1) {
			return 1;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		// isP[i][j] 字符从i开始到j结束的这段字符串是否是回文
		boolean[][] isP = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			isP[i][i] = true;
		}
		for (int i = 0; i < N - 1; i++) {
			isP[i][i + 1] = str[i] == str[i + 1];
		}
		for (int row = N - 3; row >= 0; row--) {
			for (int col = row + 2; col < N; col++) {
				isP[row][col] = str[row] == str[col] && isP[row + 1][col - 1];
			}
		}
		// dp[i] 表示以当前i为开头到结尾的最少划分部分数
		int[] dp = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		dp[N] = 0;
		for (int i = N - 1; i >= 0; i--) {
			for (int end = i; end < N; end++) {
				// i..end
				if (isP[i][end]) {
					dp[i] = Math.min(dp[i], 1 + dp[end + 1]);
				}
			}
		}
		return dp[0];
	}

	public static void main(String[] args) {
		String test2 = "aba12321412321TabaKFK";
		String test = "abcbakkkabcbaff";
		char[] chars = test.toCharArray();
		System.out.println(minParts(test));
		System.out.println(minParts2(test));
		System.out.println(minParts(test2));
		System.out.println(minParts2(test2));
//		System.out.println(isP(chars,0,4));
//		System.out.println(isP(chars,0,2));
	}

}
