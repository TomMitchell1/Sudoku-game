import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class Time {
	private int minutes;
	private int seconds;
	public Time(){
		
		
	}
	
	public int getMinutes(){
		return minutes;
	}
	
	public int getSeconds() {
		return seconds;
	}
	
	public void clearTimer(){
		minutes=0;
		seconds=0;
	}
	
	
	public void incrementTime(){
		//System.out.println("incrementing time");
		seconds++;
		if(seconds==60){
			minutes++;
			seconds=0;
		}
	}
	public void draw(Graphics2D g2){
		Color brown = new Color(230, 204, 128);
		g2.setColor(brown); 
		g2.fillRect(531, 350, 200, 100);
		
		g2.setColor(Color.BLACK);
		g2.drawLine(531, 350, 731, 350);
		g2.drawLine(531, 450, 731, 450);
		g2.drawLine(531, 350, 531, 450);
		g2.drawLine(731, 350, 731, 450);
		
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g2.drawString("Time", 605, 382);
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		
		if(minutes<10 && seconds<10){
			g2.drawString(("0"+minutes+":0"+seconds), 588, 422);
		} else if(minutes<10){
			g2.drawString(("0"+minutes+":"+seconds), 588, 422);
		} else if(seconds<10){
			g2.drawString((minutes+":0"+seconds), 588, 422);
		} else {
			g2.drawString((minutes+":"+seconds), 588, 422);
		}
	}
}
