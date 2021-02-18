package class07;

/**
 * 求最长的没有重复字符的子串长度
 * 如 abcbae, 那么cbae就是没有重复的最长子串
 */
public class Code06_LongestNoRepeatSubstring {

	public static int maxUnique(String str) {
		if (str == null || str.equals("")) {
			return 0;
		}
		char[] chas = str.toCharArray();
		// map 替代了哈希表   假设字符的码是0~255
		// map[i]表示ASII为i的字符最后一次出现的位置
		int[] map = new int[256];
		for (int i = 0; i < 256; i++) {
			map[i] = -1;
		}
		int len = 0;
		// pre表示上一个字符最右的重复位置下标
		int pre = -1;
		for (int i = 0; i != chas.length; i++) {
			// pre 和 当前字符的重复位置，谁在右边取谁
			pre = Math.max(pre, map[chas[i]]);
			len = Math.max(len, i - pre);
			map[chas[i]] = i;
		}
		return len;
	}

	// for test
	public static String getRandomString(int len) {
		char[] str = new char[len];
		int base = 'a';
		int range = 'z' - 'a' + 1;
		for (int i = 0; i != len; i++) {
			str[i] = (char) ((int) (Math.random() * range) + base);
		}
		return String.valueOf(str);
	}

	// for test
	public static String maxUniqueString(String str) {
		if (str == null || str.equals("")) {
			return str;
		}
		char[] chas = str.toCharArray();
		int[] map = new int[256];
		for (int i = 0; i < 256; i++) {
			map[i] = -1;
		}
		int len = -1;
		int pre = -1;
		int cur = 0;
		int end = -1;
		for (int i = 0; i != chas.length; i++) {
			pre = Math.max(pre, map[chas[i]]);
			cur = i - pre;
			if (cur > len) {
				len = cur;
				end = i;
			}
			map[chas[i]] = i;
		}
		return str.substring(end - len + 1, end + 1);
	}

	public static void main(String[] args) {
		String str = getRandomString(20);
		System.out.println(str);
		System.out.println(maxUnique(str));
		System.out.println(maxUniqueString(str));
	}
}
