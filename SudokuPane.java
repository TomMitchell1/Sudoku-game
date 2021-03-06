import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;


public class SudokuPane extends JComponent {

	private static Grid grid;
	private static StartButton s;
	private static RestartButton r;
	private static Time t;
	private boolean started;
	private static final int NumbersInBox = 9;
	private static final int NumbersInSudoku = 81;
	public SudokuPane(){
		grid= new Grid();
		s=new StartButton();
		r=new RestartButton();
		started=false;
		t=new Time();
	}
	
	// Creates a new sudoku when the player presses the new
	// game button on the inteface.
	public void startNewGame(){
		started=true;
		ArrayList<Integer> list;
		list= new ArrayList<Integer>();
		//Fill ArrayList
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		grid=new Grid();
		//System.out.println("BEGINNING GENERATION\n");
		generateSudoku(list);
		//System.out.println("DONE GENERATION\n");
		//printSudoku();
		//System.out.println("\nHIDING VALUES");
		hideNumbers();
		//System.out.println("DONE HIDING\n");
		//printSudoku();
	}
	
	
	public StartButton getStartButton(){
		return s;
	}
	
	/*
	 * This function generates a sudoku that the player has to solve.
	 * The sudoku is generated using a backtracking algorithm, that
	 * will try and place numbers till no numbers would be able to 
	 * go in a square because that would create duplicate numbers in
	 * a box, row or column. The algorithm then backtracks and tries 
	 * to find a different series of numbers that would be able to
	 * generate a correct sudoku.
	 */
	public static void generateSudoku(ArrayList<Integer> numberList){
		ArrayList<Integer> list= new ArrayList<Integer>(numberList);
		int i=0;
		int x=0;
		int t=0;
		int test=0;
		int p=0;
		int row=0;
		int col=0;
		boolean isPlaced=false;
		boolean stuck=true;
		Random randomGenerator = new Random();
		
		while (i<NumbersInSudoku){
			//System.out.print(i);
			row=i/NumbersInBox;
			col=i%NumbersInBox;
			isPlaced=false;
			if(col==8){
				if(list.size()==0){
					//From a backtrack
					//System.out.println("Error");
				}
				test=list.get(0);
				if(!grid.colContains(test, col)&&!grid.boxContains(row, col, test)&&!grid.exContains(i, test)){
					grid.getNumber(i).modifyValue(list.get(0));
				} else {
					i=backtrack(list, i);	
				}
				list= new ArrayList<Integer>(numberList);
			} else {
				while(!isPlaced){
					p=0;
					stuck=true;
					while(p<list.size()){
						test=list.get(p);
						if(!grid.rowContains(test, row)&&!grid.colContains(test, col)&&!grid.boxContains(row, col, test)&&!grid.exContains(i, test)){
							stuck=false;
						}
						p++;
					}	
					if(stuck==true){
						i=backtrack(list,i);
						isPlaced=true;
					} else {
						t= randomGenerator.nextInt(list.size());
						x=list.get(t);
						if(!grid.rowContains(x, row)&&!grid.colContains(x, col)&&!grid.boxContains(row, col, x)&&!grid.exContains(i, x)){
							//Therefore i can be placed there
							grid.getNumber(i).modifyValue(x);
							isPlaced=true;
							list.remove((Integer)x);
						}
					}
				}
			}	
			i++;
		}
	}

	// Prints the sudoku to stdout for debugging purposes
	public static void printSudoku(){
		int i=0;
		int j=0;
		int c=0;
		boolean doneRow=false;
		boolean rowCompleted=false;
		while(j<NumbersInBox){
			if((j==0|| j==3|| j==6) && (doneRow==false)){
				while(c<12 ){
					System.out.print("-");
					c++;
				}
				doneRow=true;
				c=0;
				System.out.println("-");
			} else {
				if(i==0 || i==3 || i==6){
					System.out.print("|");
				}
				
				if(grid.getNumber(i+NumbersInBox*j).getNumber()==0){
					System.out.print(" ");
				} else {
					System.out.print(grid.getNumber(i+NumbersInBox*j).getNumber());
				}
				i++;
				if(i==NumbersInBox){
					rowCompleted=true;
				}
			}	
			
			if(rowCompleted){
				j++;
				i=0;
				rowCompleted=false;
				doneRow=false;
				System.out.println("|");
			}
		}
		c=0;
		while(c<12){
			System.out.print("-");
			c++;
		}
		System.out.println("-");
	}
	
	public static int backtrack(ArrayList<Integer> list,int i){
		int j=i-1;
		grid.getNumber(i).cleanExcluded();
		grid.getNumber(j).addExcluded(grid.getNumber(j).getNumber());
		list.add(grid.getNumber(j).getNumber());
		grid.getNumber(j).modifyValue(0);
		return j-1;
	}
	/*
	 * Determine the numbers that are to be hidden from the user
	 * The user has to figure out these numbers to make a solution 
	 * to the sudoku
	 */
	public static void hideNumbers(){
		int i=0;
		int j=0;
		int n=0;
		boolean contains=false;
		Random randomGenerator = new Random();
		ArrayList<Integer> list =new ArrayList<Integer>();
		while (i<20){
			j=0;
			contains=false;
			n=randomGenerator.nextInt(39);
			
			while(j<list.size()){
				if(list.get(j)==n){
					contains=true;
				}
				j++;
			}
			
			if(!contains){
				list.add(n);
				grid.getNumber(n).modifyValue(0);
				grid.getNumber(n).makeHidden();
				grid.getNumber(80-n).modifyValue(0);
				grid.getNumber(80-n).makeHidden();
				i++;
			}
			
			
		}
	}
	
	//Draw the sudoku on the window
	public void paintComponent(Graphics g) {
		Graphics2D g2= (Graphics2D)g;
		Rectangle2D.Double base;
		Rectangle2D.Double background = new Rectangle2D.Double(0,0,800,600);
		
		Color brown = new Color(230, 204, 128);
		Color green = new Color(148, 184, 77);
		
		g2.setColor(green);
		g2.fill(background);
		
		int i=0;
		int col=0;
		if(!isCompleted()){
			while(col<NumbersInBox){
				base = new Rectangle2D.Double(100+40*col,120+i*40, 40, 40);
				Color myColor = new Color(255,0,0,64);
				g2.setColor(Color.BLACK);
				g2.draw(base);
				base = new Rectangle2D.Double(100+40*col+1,120+i*40+1, 39, 39);
				g2.setColor(brown);
				if(started){
					if(grid.getNumber(NumbersInBox*i+col).isSelected()){
						g2.setColor(myColor);
					}
				}	
				g2.fill(base);
				i++;
				if(i==NumbersInBox){
					col++;
					i=0;
				}
			}
			g2.setColor(Color.BLACK);
			//Drawing Thinker Lines
			//Vertical Lines
			g2.drawLine(101, 120, 101, 480);
			g2.drawLine(221, 120, 221, 480);
			g2.drawLine(341, 120, 341, 480);
			g2.drawLine(461, 120, 461, 480);
		
			//Horizontal Lines
			g2.drawLine(100, 121, 460, 121);
			g2.drawLine(100, 241, 460, 241);
			g2.drawLine(100, 361, 460, 361);
			g2.drawLine(100, 481, 461, 481);
			drawNumbers(g2);
		} else {
			g2.setColor(brown);
			base = new Rectangle2D.Double(100,120,360,360);
			g2.fill(base);
			g2.setColor(Color.BLACK);
			g2.drawLine(100, 120, 100, 480);
			g2.drawLine(101, 120, 101, 480);
			g2.drawLine(460, 120, 460, 480);
			g2.drawLine(461, 120, 461, 480);
			g2.drawLine(100, 120, 460, 120);
			g2.drawLine(100, 121, 460, 121);
			g2.drawLine(100, 480, 461, 480);
			g2.drawLine(100, 481, 461, 481);
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 40)); 
			g2.drawString("Congratulations",125,250);
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			g2.drawString("You finished",190,300);
			g2.drawString("the sudoku!", 195, 350);
			if(t.getMinutes()<10){
				if(t.getSeconds()<10){
					g2.drawString("Your time was 0" + t.getMinutes()+ ":" +"0"+ t.getSeconds(), 132, 430);
				} else {
					g2.drawString("Your time was 0" + t.getMinutes()+ ":" + t.getSeconds(), 132, 430);
				}
			} else {
				if(t.getSeconds()<10){
					g2.drawString("Your time was " + t.getMinutes()+ ":" +"0"+ t.getSeconds(), 132, 430);
				} else {
					g2.drawString("Your time was " + t.getMinutes()+ ":" + t.getSeconds(), 132, 430);
				}
			}
			
		}
		s.draw(g2);
		r.draw(g2);
		t.draw(g2);
		
	}
	
	public Grid getGrid(){
		return grid;
		
	}
	
	public Time getTime(){
		return t;
	}
	
	//Draws the numbers into the boxes in the window
	public void drawNumbers(Graphics g2){
		int i=0;
		int col=0;
		int row=0;
		if(started && !isCompleted()){
			//System.out.println("redrawing numbers");
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
			while(i<NumbersInSudoku){
				col=i/NumbersInBox;
				row=i%NumbersInBox;
				if(grid.getNumber(i).isVisible()){
					g2.setColor(Color.BLACK);
					g2.drawString(""+grid.getNumber(i).getNumber(), 115+40*row, 148+40*col);
				} else {
					if(grid.getNumber(i).getNumber()!=0){
						g2.setColor(new Color(0,0,255));
						g2.drawString(""+grid.getNumber(i).getNumber(), 115+40*row, 148+40*col);
					}
				}
				i++;
			}
		}	
	}
	
	//Restarts the current sudoku when the user presses the restart button
	public void restart(){
		int i=0;
		
		while(i<NumbersInSudoku){
			if(!grid.getNumber(i).isVisible()){
				grid.getNumber(i).modifyValue(0);
			}
			i++;
		}
	}
	
	//Determines if the sudoku has been completed by the user
	public boolean isCompleted(){
		boolean completed=true;
		
		ArrayList<Integer> originalList;
		ArrayList<Integer> list;
		originalList= new ArrayList<Integer>();
		//Fill ArrayList
		originalList.add(1);
		originalList.add(2);
		originalList.add(3);
		originalList.add(4);
		originalList.add(5);
		originalList.add(6);
		originalList.add(7);
		originalList.add(8);
		originalList.add(9);
		
		int row=0;
		int col=0;
		int i=0;
		//Check each row contains all numbers
		list= new ArrayList<Integer>(originalList);
		//System.out.println("Size of list is "+list.size());
		while(i<NumbersInSudoku && completed==true){
			col=0;
			while(col<NumbersInBox){
				if(list.contains(grid.getNumber(i).getNumber())){
					list.remove((Integer) grid.getNumber(i).getNumber());
					//System.out.println("Size of list is "+ list.size()+" after removed");
				}
				col++;
				i++;
			}
			
			if(list.size()==0){
				//System.out.println("Size of list is "+list.size());
				list= new ArrayList<Integer>(originalList);
			} else {
				completed=false;
			}
			
		}
		
		i=0;
		
		//Check each column contains all numbers
		list= new ArrayList<Integer>(originalList);
		//System.out.println("Size of list is in col "+list.size()+ " col num is "+i);
		while(i<NumbersInBox && completed==true){
			row=0;
			while(row<NumbersInBox){
				if(list.contains(grid.getNumber(NumbersInBox*row +i).getNumber())){
					list.remove((Integer) grid.getNumber(NumbersInBox*row+i).getNumber());
					//System.out.println("Size of list is "+ list.size()+" after removed");
				}
				row++;
						
			}
			i++;
			if(list.size()==0){
				//System.out.println("Size of list is "+list.size());
				list= new ArrayList<Integer>(originalList);
			} else {
				completed=false;
			}
		}
		
		//Determine if all the boxes have the right numbers
		
		//Box 1
		if(completed){
			list= new ArrayList<Integer>(originalList);
			row=0;
			col=0;
		
			while(row<3){
				col=0;
				while(col<3){
					if(list.contains(grid.getNumber(NumbersInBox*row +col).getNumber())){
						list.remove((Integer) grid.getNumber(NumbersInBox*row+col).getNumber());
					}
					col++;
				}
				row++;
			}
			if(list.size()!=0){
				completed=false;
			}
		}
		//Box 2
		if(completed){
			list= new ArrayList<Integer>(originalList);
			col=3;
			row=0;
			while(row<3){
				col=3;
				while(col<6){
					if(list.contains(grid.getNumber(NumbersInBox*row +col).getNumber())){
						list.remove((Integer) grid.getNumber(NumbersInBox*row+col).getNumber());
					}
					col++;
				}
				row++;
			}
			if(list.size()!=0){
				completed=false;
			}
		}	
		
		//Box 3
		if(completed){
			list= new ArrayList<Integer>(originalList);
			col=6;
			row=0;
			while(row<3){
				col=6;
				while(col<NumbersInBox){
					if(list.contains(grid.getNumber(NumbersInBox*row +col).getNumber())){
						list.remove((Integer) grid.getNumber(NumbersInBox*row+col).getNumber());
					}
					col++;
				}
				row++;
			}
			if(list.size()!=0){
				completed=false;
			}
		}
		//Box 4
		if(completed){
			list= new ArrayList<Integer>(originalList);
			col=0;
			row=3;
			while(row<6){
				col=0;
				while(col<3){
					if(list.contains(grid.getNumber(NumbersInBox*row +col).getNumber())){
						list.remove((Integer) grid.getNumber(NumbersInBox*row+col).getNumber());
					}
					col++;
				}
				row++;
			}
			if(list.size()!=0){
				completed=false;
			}
		}
		
		//Box 5
		if(completed){
			list= new ArrayList<Integer>(originalList);
			col=3;
			row=3;
			while(row<6){
				col=3;
				while(col<6){
					if(list.contains(grid.getNumber(NumbersInBox*row +col).getNumber())){
						list.remove((Integer) grid.getNumber(NumbersInBox*row+col).getNumber());
					}
					col++;
				}
				row++;
			}
			if(list.size()!=0){
				completed=false;
			}		
		}
		
		//Box 6
		if(completed){
			list= new ArrayList<Integer>(originalList);
			col=6;
			row=3;
			while(row<6){
				col=6;
				while(col<NumbersInBox){
					if(list.contains(grid.getNumber(NumbersInBox*row +col).getNumber())){
						list.remove((Integer) grid.getNumber(NumbersInBox*row+col).getNumber());
					}
					col++;
				}
				row++;
			}
			if(list.size()!=0){
				completed=false;
			}	
		}
		
		//Box 7
		if(completed){
			list= new ArrayList<Integer>(originalList);
			col=0;
			row=6;
			while(row<NumbersInBox){
				col=0;
				while(col<3){
					if(list.contains(grid.getNumber(NumbersInBox*row +col).getNumber())){
						list.remove((Integer) grid.getNumber(NumbersInBox*row+col).getNumber());
					}
					col++;
				}
				row++;
			}
			if(list.size()!=0){
				completed=false;
			}
		}
		
		//Box 8
		if(completed){
			list= new ArrayList<Integer>(originalList);
			col=3;
			row=6;
			while(row<NumbersInBox){
				col=3;
				while(col<6){
					if(list.contains(grid.getNumber(NumbersInBox*row +col).getNumber())){
						list.remove((Integer) grid.getNumber(NumbersInBox*row+col).getNumber());
					}
					col++;
				}
				row++;
			}
			if(list.size()!=0){
				completed=false;
			}
		}
		
		//Box 9
		if(completed){
			list= new ArrayList<Integer>(originalList);
			col=6;
			row=6;
			while(row<NumbersInBox){
				col=3;
				while(col<NumbersInBox){
					if(list.contains(grid.getNumber(NumbersInBox*row +col).getNumber())){
						list.remove((Integer) grid.getNumber(NumbersInBox*row+col).getNumber());
					}
					col++;
				}
				row++;
			}
			if(list.size()!=0){
				completed=false;
			}
		}
		
		//System.out.println(completed);
		return completed;
	}
}