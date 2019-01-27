package pacman;

import java.util.ArrayList;

public class Maze {

	private int[][] coordinateBrick = new int[106][2];
	private int[][] coordinateStar1 = new int[62][2];
	private ArrayList<int[]> coordinateStar = new ArrayList<>();

	private int x = 0, y = 0, k = 0, m = 0, xS = 0, yS = 0, kS = 0, mS = 0;

	private int[][] mazeArray = { // numbers: 1 = bricks, 2 = stars 
			{ 0, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2 }, 
			{ 2, 1, 2, 0, 2, 0, 1, 0, 1, 0, 2, 0, 2, 0, 1, 0 },
			{ 0, 1, 0, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 2 }, 
			{ 2, 1, 2, 0, 2, 0, 2, 0, 1, 0, 1, 0, 2, 0, 1, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2 }, 
			{ 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0 },
			{ 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1 }, 
			{ 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0 },
			{ 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1 }, 
			{ 2, 0, 2, 1, 2, 1, 0, 1, 2, 1, 2, 1, 2, 1, 0, 1 },
			{ 0, 0, 2, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1 }, 
			{ 2, 1, 0, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1 },
			{ 0, 1, 2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 }, 
			{ 2, 1, 1, 1, 2, 1, 2, 0, 2, 1, 1, 1, 2, 0, 2, 1 } };

	public int getX() {
		for (; k < mazeArray.length;) {
			if (x == 800 && k < mazeArray.length - 1) {
				++k;
				x = 0;
			}
			for (; x < mazeArray[k].length * 50; x += 50) {
				if (mazeArray[k][x / 50] == 1) {
					x += 50;
					break;
				}
			}
			if (mazeArray[k][x / 50 - 1] == 1) {
				break;
			}
			x = 0;
			++k;
		}
		return ((x / 50 - 1) == 15) ? 750 : (x - 50);
	}

	public int getY() {
		for (; y < mazeArray.length * 50;) {
			if (m == 16 && y / 50 < mazeArray.length - 1) {
				y += 50;
				m = 0;
			}
			for (; m < mazeArray[y / 50].length; m++) {
				if (mazeArray[y / 50][m] == 1) {
					m++;
					break;
				}
			}
			if (mazeArray[y / 50][m - 1] == 1) {
				break;
			}
			m = 0;
			y += 50;
		}
		return y;
	}

	public int getX_Star() {
		for (; kS < mazeArray.length;) {
			if (xS == 800 && kS < mazeArray.length - 1) {
				++kS;
				xS = 0;
			}
			for (; xS < mazeArray[kS].length * 50; xS += 50) {
				if (mazeArray[kS][xS / 50] == 2) {
					xS += 50;
					break;
				}
			}
			if (mazeArray[kS][xS / 50 - 1] == 2) {
				break;
			}
			xS = 0;
			++kS;
		}
		return ((xS / 50 - 1) == 15) ? 750 : (xS - 50);
	}

	public int getY_Star() {
		for (; yS < mazeArray.length * 50;) {
			if (mS == 16 && yS / 50 < mazeArray.length - 1) {
				yS += 50;
				mS = 0;
			}
			for (; mS < mazeArray[yS / 50].length; mS++) {
				if (mazeArray[yS / 50][mS] == 2) {
					mS++;
					break;
				}
			}
			if (mazeArray[yS / 50][mS - 1] == 2) {
				break;
			}
			mS = 0;
			yS += 50;
		}
		return yS;
	}

	public int[][] getCoordinateBrick() {
		for (int i = 0; i < coordinateBrick.length; i++) {
			coordinateBrick[i][0] = getX();
			coordinateBrick[i][1] = getY();
		}
		return coordinateBrick;
	}

	public ArrayList<int[]> getCoordinateStar() {
		for (int i = 0; i < coordinateStar1.length; i++) {
			coordinateStar1[i][0] = getX_Star();
			coordinateStar1[i][1] = getY_Star();
			coordinateStar.add(coordinateStar1[i]);
		}
		return coordinateStar;
	}
}
