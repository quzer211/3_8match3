package match3;

import java.util.Random;

public class Grid {
private int[][] grid;
private int x;
public Grid(int x){
	this.x=x;
	grid=new int[2*x][x];//���񲼾������2X����X�ĳ����壬����ֻ���°벿���ǿɼ��ģ�������ɱ�ʯ�����Ч��
}
public void start(){
	Random r=new Random();;
	for (int i = 0; i < grid.length; i++) {
		for (int j = 0; j < grid[i].length; j++) {
			int a=r.nextInt(7)+1;
			grid[i][j]=a;
		}
	}
}//������ɱ�ʯ
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
