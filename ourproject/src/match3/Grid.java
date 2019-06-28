package match3;

import java.util.Random;

public class Grid {
private int[][] grid;
private int x;
public Grid(int x){
	this.x=x;
	grid=new int[2*x][x];//方格布局整体高2X，宽X的长方体，但是只有下半部分是可见的，可以造成宝石下落的效果
}
public void start(){
	Random r=new Random();;
	for (int i = 0; i < grid.length; i++) {
		for (int j = 0; j < grid[i].length; j++) {
			int a=r.nextInt(7)+1;
			grid[i][j]=a;
		}
	}
}//随机生成宝石
public int[][] getGrid() {
	return grid;
}
public void setGrid(int[][] grid) {
	this.grid = grid;
}
public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}

}
