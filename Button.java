import java.awt.Graphics2D;

import javax.swing.JComponent;


public interface Button {
	String s;
	int x;
	int y;
	public Button(String a,int newX,int newY){
		x=newX;
		y=newY;
		s=a;
	}
	public void paintComponent(Graphics2D g2);
	
}
