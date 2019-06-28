package match3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {
private Win win;
private Grid grid;
private int x;
private int score;
private int level;
private TollThread tollthread;
private boolean clicked;
private List<Integer> list;
boolean bb=true;
private Winwin winwin;
public Controller(){
	x=10;
	win=new Win(this);
	grid=new Grid(x);
	list=new ArrayList<Integer>();
	tollthread=new TollThread(this,win.getRoll());
	winwin=new Winwin();
}
public void beforeStart(){
	if(!bb){
		return;
	}
	grid.start();
	showGame();
	try {
		Thread.sleep(100);
	} catch (InterruptedException e1) {
		
		e1.printStackTrace();
	}
	new Thread(){
		public void run(){
			int j=0;
			while(true){
				bb=false;
				if(j>=220){
					Controller.this.start();
					bb=true;
					break;
				}
				try {
					sleep(10);
					j+=10;
					win.getP().setBounds(350,j, 400, 400);
				} catch (InterruptedException e) {
				}
			}
		}
	}.start();
}
public void start(){
	win.getFinity().setVisible(false);
	TollThread.go=false;
	win.getScore().setEnabled(true);
	win.getAction().setVisible(true);
	//grid.start();
	//showGame();
	startbomp();
	score=0;
	level=1;
	clicked=false;
	win.getLevel().setText("level "+Integer.toString(level));
	win.getScore().setText(Integer.toString(score));
	tollthread=new TollThread(this,win.getRoll());
	new Thread(){
		public void run(){
			try {
				Thread.sleep(1500);
				win.getAction().setVisible(false);
				TollThread.go=true;
				TollThread.time=70;
				tollthread.start();
				
			} catch (InterruptedException e) {
			}
		}
	}.start();
}
public void levelUp(){//升级
	if(score>=100*level){//可调节
		level++;
		if(level>8){
			win.setVisible(false);//原游戏界面消失
			winwin.setVisible(true);//出现新窗口
			return;
		}
		win.getLevel().setText("level "+Integer.toString(level));
		win.getAction().setVisible(true);
		grid.start();
		showGame();
		startbomp();
		TollThread.time-=3;//升级后时间流逝变快，可调节
		new Thread(){
			public void run(){
				try {
					Thread.sleep(2000);
					win.getAction().setVisible(false);
					int l=tollthread.getLength()+100*(8-level);
					l=l>760?760:l;
					tollthread.setLength(l);
				} catch (InterruptedException e) {
				}
			}
		}.start();
	}
}
public  void lost(){//时间结束

	win.getScore().setEnabled(false);
	score=0;
	level=1;
	win.getFinity().setVisible(true);

}
public void startbomp(){
	new Thread(){
		public void run(){
			try {
				sleep(300);
				for(int i=19;i>=10;i--){
					for(int j=0;j<10;j++){
						bomp(i,j);
						sleep(10);
					}
				}
			} catch (InterruptedException e) {
			}
		}
	}.start();
}

public void clicked(int aa,final int j) {//选取判定
	final int i=aa+10;
	if(list.size()==0||list==null){//如果没选择
		win.getSelect().setBounds(350+j*40, 220+aa*40, 40, 40);
		win.getSelect().setVisible(true);
			list.add(i);
			list.add(j);
			clicked=true;
	}
	else if(list.size()==2){//如果已经选中一个宝石
		win.getSelect().setVisible(false);
		    	if(((i==list.get(0)-1||i==list.get(0)+1)&&j==list.get(1))||    //上下运动
				((j==list.get(1)-1||j==list.get(1)+1)&&i==list.get(0))){  //左右运动
		    		new Thread(){
		    			public void run(){
		    				try {
		    					int a=grid.getGrid()[i][j];
		    					switcher(i, j, list.get(0), list.get(1),1);//图像交换
		    					grid.getGrid()[i][j]=grid.getGrid()[list.get(0)][list.get(1)];
		    					grid.getGrid()[list.get(0)][list.get(1)]=a;//值的交换
		    					sleep(200);
		    					System.out.println(list);
		    					if(list==null||list.size()==0){
		    						return;
		    					}
		    					boolean bb=bomp(i,j);
		    					boolean aa=bomp(list.get(0),list.get(1));//判定是否消除
		    					if(!aa&&!bb){//如果没消除再换回来
		    						a=grid.getGrid()[i][j];
		    						switcher(i, j, list.get(0), list.get(1),2);
		    						grid.getGrid()[i][j]=grid.getGrid()[list.get(0)][list.get(1)];
		    						grid.getGrid()[list.get(0)][list.get(1)]=a;
		    						sleep(500);
		    					}
		    					if(list.size()<2||list==null){
		    						return;
		    					}
		    					list.remove(1);
		    					list.remove(0);
		    					clicked=false;

		    				} catch (InterruptedException e) {
		    				}
				}
			}.start();
		}
		else{
			list.remove(1);
			list.remove(0);
			clicked=false;
		}
	}
}
public void switcher(final int ii1,final int jj1,final int ii2,final int jj2,final int sw){//图像交换
	new Thread(){
		public void run(){
			int time=0;
			int aaa=0;
			int bbb=0;
			if(sw==1){
				aaa=10;
				bbb=-10;
			}
			else{
				aaa=-10;
				bbb=10;
			}
			if(jj1==jj2){
				int i1=ii1<=ii2?ii1:ii2;
				int i2=ii1<=ii2?ii2:ii1;
				int j1=jj1;
				int j2=jj2;
				while(true){
						win.getBoard()[i1-10][j1].setBounds(win.getBoard()[i1-10][j1].getBounds().x,
								win.getBoard()[i1-10][j1].getBounds().y+aaa,40,40);
						win.getBoard()[i2-10][j2].setBounds(win.getBoard()[i2-10][j2].getBounds().x, 
								win.getBoard()[i2-10][j2].getBounds().y+bbb, 40, 40);
						try {
							sleep(30);
							time++;
						} catch (InterruptedException e) {
						}
						if(time>=4){
							showGame();
							break;
						}
			}
			}
			else{
				int j1=jj1<=jj2?jj1:jj2;
				int j2=jj1<=jj2?jj2:jj1;
				while(true){
					win.getBoard()[ii1-10][j1].setBounds(win.getBoard()[ii1-10][j1].getBounds().x+aaa,
							win.getBoard()[ii1-10][j1].getBounds().y,40, 40);
					win.getBoard()[ii2-10][j2].setBounds(win.getBoard()[ii2-10][j2].getBounds().x+bbb,
							win.getBoard()[ii2-10][j2].getBounds().y,40, 40);
					try {
						sleep(30);
						time++;
					} catch (InterruptedException e) {
					}
					if(time>=4){
						showGame();
						break;
					}
		}
			}
		}
	}.start();
}
public boolean bomp(int i1,int j1){//消除判定
	if(win.getFinity().isVisible()){
		return false;
	}
	boolean bb=false;
	int a=grid.getGrid()[i1][j1];
	int num=0;
	int a1,a2;
	for(a1=i1;a1<grid.getGrid().length;a1++){
		if(a!=grid.getGrid()[a1][j1]){
			a1-=1;
			break;
		}
		num++;
	}
	for(a2=i1-1;a2>=grid.getGrid().length-x;a2--){
		if(a!=grid.getGrid()[a2][j1]){
			a2+=1;
			break;
		}
		num++;
	}
	if(a1==20)a1=19;
	if(a2==9)a2=10;
	if(num>=3){
		for(int i=a2;i<=a1;i++){
			win.getBoard1()[i-10][j1].setVisible(true);
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		for(int i=a2;i<=a1;i++){
			win.getBoard1()[i-10][j1].setVisible(false);
		}
		goDown(a1,j1,num);
		bb=true;
		if(num==3){
			score+=10;
			tollthread.addlength(8);
		}
		else if(num==4){
			score+=15;
			tollthread.addlength(10);
		}
		else{
			score+=20;
			tollthread.addlength(12);
		}
		win.getScore().setText(Integer.toString(score));
		levelUp();
	}
	num=0;
	for(a1=j1;a1<x;a1++){
		if(a!=grid.getGrid()[i1][a1]){
			a1-=1;
			break;
		}
		num++;
	}
	for(a2=j1-1;a2>=0;a2--){
		if(a!=grid.getGrid()[i1][a2]){
			a2+=1;
			break;
		}
		num++;
	}
	if(a1==10)a1=9;
	if(a2==-1)a2=0;
	if(num>=3){
		for(int i=a2;i<=a1;i++){
			win.getBoard1()[i1-10][i].setVisible(true);
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		for(int i=a2;i<=a1;i++){
			win.getBoard1()[i1-10][i].setVisible(false);
		}
		for(int i=a2;i<=a1;i++){
			goDown(i1,i,1);
			bb=true;
		}
		if(num==3){
			score+=10;
			tollthread.addlength(8);
		}
		else if(num==4){
			score+=15;
			tollthread.addlength(10);
		}
		else{
			score+=20;
			tollthread.addlength(12);
		}
		win.getScore().setText(Integer.toString(score));
		levelUp();
	}
	showGame();
	return bb;
}
public void goDown(int i,int j,int n){//下落效果
	int ii1=i;
	for(int ii=0;ii<n;ii++){
		grid.getGrid()[ii1][j]=0;
		win.getBoard()[ii1-10][j].setimage(0);
		ii1--;
	}
	for(int ii=i;ii>=n;ii--){
		grid.getGrid()[ii][j]=grid.getGrid()[ii-n][j];
	}
	Random r=new Random();
	for(int ii=0;ii<n;ii++){
		grid.getGrid()[ii][j]=r.nextInt(7)+1;
	}
	for(int a=i;a>=10;a--){
		bomp(a,j);
	}
}
public void showGame(){//显示下半部分
	for(int i=grid.getGrid().length-10;i<grid.getGrid().length;i++){
		for(int j=0;j<grid.getGrid()[i].length;j++){
			win.getBoard()[i-10][j].setimage(grid.getGrid()[i][j]);
		}
	}
}
public boolean isClicked() {
	return clicked;
}
public int getX() {
	return x;
}
}
