import java.util.ArrayList;


public class Number {
	private int number;
	private boolean visible;
	private boolean selected;
	private ArrayList<Integer> excluded;
	
	public Number(int x){
		visible =true;
		number=x;
		selected=false;
		excluded= new ArrayList<Integer>();
	}
	
	public int getNumber(){
		return number;
	}
	
	public void makeVisible(){
		visible =true;
	}
	
	//Make the number on of the hidden numbers at the start of the game
	//The user has to figure out what the number is
	public void makeHidden(){
		visible=false;
	}
	
	public boolean isVisible(){
		return visible;
	}
	
	public boolean isSelected(){
		return selected;
	}
	
	//Changes the value of that box in generation of the sudoku or when the user modifies a box value
	public void modifyValue(int x){
		number=x;
	}
	
	//Changes the value of selected if the user clicks on the box
	public void modifySelected(){
		selected=!selected;
	}
	
	//Adds a number to the excluded list when it cannot be placed in the box of the sudoku
	public void addExcluded(int a){
		excluded.add(a);
	}
	
	//Clear the excluded list for that number that conatains the values that it cannot take
	public void cleanExcluded(){
		excluded= new ArrayList<Integer>();
	}
	public ArrayList<Integer> getList(){
		return excluded;
	}
}
