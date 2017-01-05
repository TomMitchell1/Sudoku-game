import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.Timer;


public class Sudoku {
	
	private static SudokuPane sudoku;
	private static int selected;
	private static Timer t;
	
	public static void main(String[] args){
		JFrame frame=new JFrame();
		selected=0;
		sudoku = new SudokuPane();
		sudoku.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				int col=0;
				int row=0;
				int i=0;
				if(arg0.getX()>100 && arg0.getX()<460){
					if(arg0.getY()>120 && arg0.getY()<480){
						col=((arg0.getX()-100)/40)%9;
						row=((arg0.getY()-120)/40)%9;
						i=col+9*row;
						if(!sudoku.getGrid().getNumber(i).isSelected()){
							sudoku.getGrid().removedSelected();
							
						}
						sudoku.getGrid().getNumber(i).modifySelected();	
						if(!sudoku.getGrid().getNumber(i).isSelected()){
							selected=-1;
						} else {
							selected=i;
						}
						sudoku.repaint();
					} else {
						selected=-1;
					}
				}
				
				if(arg0.getX()>531 && arg0.getX()<731){
					if(arg0.getY()>150 && arg0.getY()<200){
						//Then the start game button has been pressed
						sudoku.startNewGame();
						if(t!=null){
							t.stop();
							sudoku.getTime().clearTimer();
						}
						t=new Timer(1000,new ActionListener(){
							public void actionPerformed(ActionEvent arg0) {
								
								sudoku.getTime().incrementTime();
								sudoku.repaint();
							}
						});
						sudoku.repaint();
						t.start();
						
					}	
				}
				if(arg0.getX()>531 && arg0.getX()<731){
					if(arg0.getY()>250 && arg0.getY()<300){
						//Restart the same grid
						sudoku.restart();
						sudoku.repaint();
					}
					
				}	
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
		});
		
		frame.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				if(selected!=-1){
					//system.out.println(arg0.getKeyCode());
					if(arg0.getKeyCode()>47 && arg0.getKeyCode()<58){
						if(!sudoku.getGrid().getNumber(selected).isVisible()){
							sudoku.getGrid().getNumber(selected).modifyValue(Character.getNumericValue(arg0.getKeyChar()));
							//System.out.println("new value of"+selected+"is"+sudoku.getGrid().getNumber(selected).getNumber());
							if(sudoku.isCompleted()){
								t.stop();
							}
							sudoku.repaint();
						}	
					} else if(arg0.getKeyCode()==8){
						if(!sudoku.getGrid().getNumber(selected).isVisible()){
							sudoku.getGrid().getNumber(selected).modifyValue(0);
							sudoku.repaint();
						}	
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				
				
			}
			
		});
		
		frame.setLayout(new BorderLayout());
		
		frame.add(sudoku,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(800,600);
		frame.setVisible(true);
	}
}
