import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;


public class StartButton extends JComponent {
	
	public StartButton(){
		
	}
	

	public void draw(Graphics2D g2){
		// Draw instructions here
		
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
		g2.fillRect(531, 150, 200, 50);
		
		g2.setColor(Color.BLACK);
		g2.drawLine(531, 150, 731, 150);
		g2.drawLine(531, 200, 731, 200);
		g2.drawLine(531, 150, 531, 200);
		g2.drawLine(731, 150, 731, 200);
		
		g2.drawString("New Game", 580, 182);
	}
}
