package match3;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Win extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private MyLabel[][] board;
	private JLabel[][] board1;
	private JLayeredPane main;
	private JLabel score;
	private JLabel select;
	private JLabel roll;
	private JLabel finity;
	private JLabel action;
	private JLabel level;
	private JPanel p;
	public Win(Controller controller){
		this.controller=controller;
		main=new JLayeredPane();
		init();
	}
	private void init() {//初始化
		setTitle("宝石迷阵");
		setBounds(150,150,900, 750);
		setVisible(true);//创建窗口
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口
		setContentPane(createPanel());//添加内容
	}
	private  JLayeredPane createPanel() {
		ImageIcon background=new ImageIcon(Win.class.getResource("pic/back.jpg"));//调用本地图片，下同
		background.setImage(background.getImage().getScaledInstance(getWidth(), getHeight(), 1));//图片缩放
		JLabel back=new JLabel(background);
		back.setBackground(Color.BLACK);//窗口背景颜色
		back.setBounds(0, 0,900,750);//设置x,y,width,height
		main.add(back,JLayeredPane.DEFAULT_LAYER);//设置图层
		ImageIcon startimg=new ImageIcon(Win.class.getResource("pic/start.png"));
		JLabel start=new JLabel(startimg);
		start.addMouseListener(new MouseAdapter(){//设置按键

			public void mouseClicked(MouseEvent e) {
				controller.beforeStart();
				
			}//执行内容，重开一局
		});
		main.add(start,JLayeredPane.POPUP_LAYER);
		start.setBounds(198, 455,70,70);
		ImageIcon menuimg1=new ImageIcon(Win.class.getResource("pic/all.png"));
		menuimg1.setImage(menuimg1.getImage().getScaledInstance(350,250, 1));
		JLabel menu1=new JLabel(menuimg1);
		menu1.setBounds(-30, 440,350,250);
		main.add(menu1,JLayeredPane.MODAL_LAYER);
		ImageIcon boardimg1=new ImageIcon(Win.class.getResource("pic/action.png"));
		boardimg1.setImage(boardimg1.getImage().getScaledInstance(550,600, 1));
		JLabel board11=new JLabel(boardimg1);
		board11.setBounds(280, 50,550,600);
		main.add(board11,JLayeredPane.PALETTE_LAYER);
		p=new JPanel(new GridLayout(controller.getX(),controller.getX()));
		p.setOpaque(false);//是否透明，false表示透明
		board=new MyLabel[controller.getX()][controller.getX()];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j]=new MyLabel(i,j,controller);
				//board[i][j].setBounds(350+j*40, 320+i*40, 40, 40);
				//main.add(board[i][j],JLayeredPane.MODAL_LAYER);
				p.add(board[i][j]);
			}
		}
		p.setBounds(350, 220, 400,400);
		main.add(p,JLayeredPane.MODAL_LAYER);
		ImageIcon selectimg=new ImageIcon(Win.class.getResource("pic/1select.png"));
		selectimg.setImage(selectimg.getImage().getScaledInstance(40, 40, 1));
		select=new JLabel(selectimg);
		select.setBounds(350, 220, 40, 40);
		main.add(select,JLayeredPane.POPUP_LAYER);
		ImageIcon boardimg=new ImageIcon(Win.class.getResource("pic/guang.png"));
		boardimg.setImage(boardimg.getImage().getScaledInstance(40,40, 1));
		JPanel p1=new JPanel(new GridLayout(controller.getX(),controller.getX()));
		p1.setOpaque(false);
		board1=new JLabel[controller.getX()][controller.getX()];
		for (int i = 0; i < board1.length; i++) {
			for (int j = 0; j < board1[i].length; j++) {
				board1[i][j]=new JLabel(boardimg);
				board1[i][j].setVisible(false);
				p1.add(board1[i][j]);
			}
		}
		p1.setBounds(350, 220, 400,400);
		main.add(p1,JLayeredPane.DRAG_LAYER);
		createButton();
		createWord();
		return main;
	}
	
	private void createButton() {
		ImageIcon scoreimg1=new ImageIcon(Win.class.getResource("pic/1score.png"));
		JLabel scoreri1=new JLabel(scoreimg1);
		scoreri1.setBounds(10, 0, 149, 81);
		main.add(scoreri1,JLayeredPane.PALETTE_LAYER);
		ImageIcon scoreimg=new ImageIcon(Win.class.getResource("pic/score.png"));
		JLabel scoreri=new JLabel(scoreimg);
		scoreri.setBounds(0,81, 177, 94);
		main.add(scoreri,JLayeredPane.PALETTE_LAYER);
		JLabel scorer=new JLabel("score");
		scorer.setFont(new Font("Monospace",1,23));
		scorer.setForeground(Color.WHITE);
		scorer.setBounds(50,5, 100, 50);
		main.add(scorer,JLayeredPane.MODAL_LAYER);
		score=new JLabel("0");
		score.setFont(new Font("Monospace",1,23));
		score.setForeground(Color.GREEN);
		score.setBounds(60, 35, 200, 200);
		main.add(score,JLayeredPane.MODAL_LAYER);
		ImageIcon rollimg=new ImageIcon(Win.class.getResource("pic/roll.png"));
		rollimg.setImage(rollimg.getImage().getScaledInstance(750, 50, 1));
		JLabel roll1=new JLabel(rollimg);
		roll1.setBounds(100,660,750, 50);
		main.add(roll1,JLayeredPane.PALETTE_LAYER);
		ImageIcon rollerimg =new ImageIcon(Win.class.getResource("pic/1tool.png"));
		rollerimg.setImage(rollerimg.getImage().getScaledInstance(760, 50, 1));
		roll=new JLabel(rollerimg);
		roll.setBounds(110,660,760, 50);
		main.add(roll,JLayeredPane.MODAL_LAYER);
		
	}
	public void createWord(){
		level=new JLabel("EA");
		level.setFont(new Font("Sans",1,30));
		level.setForeground(Color.BLACK);
		level.setBounds(100, 500, 100, 100);
		main.add(level,JLayeredPane.POPUP_LAYER);
		ImageIcon menuimg=new ImageIcon(Win.class.getResource("pic/menu.png"));//menu
		JLabel menu=new JLabel(menuimg);
		menu.setBounds(30, 460, 70, 70);
		main.add(menu,JLayeredPane.POPUP_LAYER);
		ImageIcon quitimg=new ImageIcon(Win.class.getResource("pic/Quit.png"));//quit
		JLabel quit=new JLabel(quitimg);
		quit.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent e) {
				System.exit(0);
				
			}
		});
		quit.setBounds(200, 580, 50, 50);
		main.add(quit,JLayeredPane.POPUP_LAYER);
		ImageIcon finityimg=new ImageIcon(Win.class.getResource("pic/finity.png"));//finity
		finityimg.setImage(finityimg.getImage().getScaledInstance(150, 100, 1));
		finity=new JLabel(finityimg);
		finity.setBounds(445, 220, 150, 100);
		finity.setVisible(false);
		main.add(finity,JLayeredPane.POPUP_LAYER);
		ImageIcon actionimg=new ImageIcon(Win.class.getResource("pic/action22.png"));//action
		actionimg.setImage(actionimg.getImage().getScaledInstance(150, 100, 1));
		action=new JLabel(actionimg);
		action.setBounds(450, 330, 150, 100);
		action.setVisible(false);
		main.add(action,JLayeredPane.POPUP_LAYER);
	}
	public JLabel getLevel() {
		return level;
	}
	public JLabel getAction() {
		return action;
	}
	public JLabel getFinity() {
		return finity;
	}
	public JLabel getRoll() {
		return roll;
	}
	public JLabel getSelect() {
		return select;
	}
	public void setSelect(JLabel select) {
		this.select = select;
	}
	public JLabel getScore() {
		return score;
	}
	public MyLabel[][] getBoard() {
		return board;
	}
	public void setBoard(MyLabel[][] board) {
		this.board = board;
	}
public JLabel[][] getBoard1() {
	return board1;
}
public JPanel getP() {
	return p;
}
}
