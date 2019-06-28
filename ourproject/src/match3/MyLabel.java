package match3;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyLabel extends JLabel{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//private Controller controller;
//private int i;
//private int j;
private static boolean a=false;
public final static ImageIcon pic1=new ImageIcon(MyLabel.class.getResource("pic/1.png"));
public final static ImageIcon pic2=new ImageIcon(MyLabel.class.getResource("pic/2.png"));
public final static ImageIcon pic3=new ImageIcon(MyLabel.class.getResource("pic/3.png"));
public final static ImageIcon pic4=new ImageIcon(MyLabel.class.getResource("pic/4.png"));
public final static ImageIcon pic5=new ImageIcon(MyLabel.class.getResource("pic/5.png"));
public final static ImageIcon pic6=new ImageIcon(MyLabel.class.getResource("pic/6.png"));
public final static ImageIcon pic7=new ImageIcon(MyLabel.class.getResource("pic/7.png"));
static{
	pic1.setImage(pic1.getImage().getScaledInstance(40, 40, 1));
	pic2.setImage(pic2.getImage().getScaledInstance(40, 40, 1));
	pic3.setImage(pic3.getImage().getScaledInstance(40, 40, 1));
	pic4.setImage(pic4.getImage().getScaledInstance(40, 40, 1));
	pic5.setImage(pic5.getImage().getScaledInstance(40, 40, 1));
	pic6.setImage(pic6.getImage().getScaledInstance(40, 40, 1));
	pic7.setImage(pic7.getImage().getScaledInstance(40, 40, 1));
}
public MyLabel(){
}
public MyLabel(final int i,final int j,final Controller controller){
	super();
	//this.controller=controller;
	//this.i=i;
	//this.j=j;
	//setBorder(new LineBorder(Color.LIGHT_GRAY));
	setIcon(null);
	addMouseListener(new MouseAdapter(){

//		public void mouseClicked(MouseEvent e) {
//			controller.clicked(i,j);
//		}
		public void mousePressed(MouseEvent e) {
			if(!a&&!controller.isClicked()){
					controller.clicked(i, j);
					a=true;
			}
		}
		public void mouseEntered(MouseEvent e) {//鼠标经过事件,触发一次
			if(a&&controller.isClicked()){
				a=false;
				controller.clicked(i, j);
			}
		}
	});
}
public void setimage(int i) {
	switch(i){
	case 1:
		setIcon(pic1);
		break;
	case 2:
		setIcon(pic2);
		break;
	case 3:
		setIcon(pic3);
		break;
	case 4:
		setIcon(pic4);
		break;
	case 5:
		setIcon(pic5);
		break;
	case 6:
		setIcon(pic6);
		break;
	case 7:
		setIcon(pic7);
		break;
	default:
		setIcon(null);
	}
}
}
