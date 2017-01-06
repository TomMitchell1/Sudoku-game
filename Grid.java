import java.util.ArrayList;


public class Grid {
	private Number[] grid;
	private static final int NumbersInBox=9;
	private static final int NumbersInSudoku=81;
	public Grid(){
		grid= new Number[NumbersInSudoku];
		int i=0;
		while(i<NumbersInSudoku){
			grid[i]=new Number(0);
			i++;
		}
	}
	
	public Number getNumber(int i){
		return grid[i];
	}
	//Does the input row already contain x
	public boolean rowContains(int x,int row){
		int i=0;
		int rowConstant=NumbersInBox*row;
		boolean contains=false;
		while (i<NumbersInBox){
			if(grid[rowConstant+i].getNumber()==x){
				contains=true;
			}
			i++;
		}
		return contains;
	}
	//Does the input column contain the value y
	public boolean colContains(int y,int col){
		int i=0;
		int colConstant=0;
		boolean contains=false;
		while(i<NumbersInBox){
			colConstant=NumbersInBox*i;
			if(grid[colConstant+col].getNumber()==y){
				contains=true;
				
			}
			i++;
		}
		return contains;
	}
	
	//Does the box specified by the row and the column input already include x?
	public boolean boxContains(int row,int col,int x){
		boolean contains=false;
		int box=0;
		int bconst=0;
		int i=0;
		int j=0;
		//Determine the box
		if(row<3){
			if(col<3) box=0;
			else if(col<6) box=1;
			else box=2;
		}else if(row<6){
			if(col<3) box=3;
			else if(col<6) box=4;
			else box=5;
		}else{
			if(col<3) box=6;
			else if(col<6) box=7;
			else box=8;
		}
		
		if(box<3){
			bconst=0;
		} else if(box<6){
			bconst=3*NumbersInBox;
			box=box-3;
		}else {
			bconst=6*NumbersInBox;
			box=box-6;
		}	
		
		while(j<3){
			i=0;
			while(i<3){
				if(grid[bconst+NumbersInBox*j+i+3*box].getNumber()==x){
					contains=true;
				}
				i++;
			}
			j++;
		}
		return contains;
	}
	
	//Is the value already in the excluded list for that number?
	public boolean exContains(int i,int a){
		boolean contains=false;
		int size=grid[i].getList().size();
		ArrayList<Integer> list=grid[i].getList();
		int j=0;
		while(j<size){
			if(a==list.get(j)){
				contains=true;
			}
			j++;
		}
		return contains;
	}
	//Stop selecting a number when the user clicks on the same 
	public void removedSelected(){
		int i=0;
		while(i<NumbersInSudoku){
			if(grid[i].isSelected()){
				grid[i].modifySelected();
			}
			i++;
		}
	}
}
