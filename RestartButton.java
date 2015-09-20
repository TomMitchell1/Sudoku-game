import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JComponent;


public class RestartButton extends JComponent {
	
	public RestartButton(){
		
	}
	//Draw the restart button onto the window
	public void draw(Graphics2D g2){
		Color brown = new Color(230, 204, 128);
		g2.setColor(brown);
		g2.fillRect(531, 250, 200, 50);
		
		g2.setColor(Color.BLACK);
		g2.drawLine(531, 250, 731, 250);
		g2.drawLine(531, 300, 731, 300);
		g2.drawLine(531, 250, 531, 300);
		g2.drawLine(731, 250, 731, 300);
		
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
		g2.drawString("Restart Game", 570, 282);
	}
}
