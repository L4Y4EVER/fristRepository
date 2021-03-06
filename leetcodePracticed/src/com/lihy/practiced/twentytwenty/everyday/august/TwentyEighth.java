package com.lihy.practiced.twentytwenty.everyday.august;

/**
 * @author lihongyan
 * @date 2020/8/28
 */
public class TwentyEighth {
	public boolean judgeCircle(String moves) {
		int x = 0, y = 0;
		int length = moves.length();
		for (int i = 0; i < length; i++) {
			char move = moves.charAt(i);
			if (move == 'U') {
				y--;
			} else if (move == 'D') {
				y++;
			} else if (move == 'L') {
				x--;
			} else if (move == 'R') {
				x++;
			}
		}
		return x == 0 && y == 0;
	}
}
