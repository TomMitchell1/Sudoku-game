import java.util.ArrayList;


public class Grid {
	private Number[] grid;
	public Grid(){
		grid= new Number[81];
		int i=0;
		while(i<81){
			grid[i]=new Number(0);
			i++;
		}
	}
	
	public Number getNumber(int i){
		return grid[i];
	}
	
	public boolean rowContains(int x,int row){
		int i=0;
		int rowConstant=9*row;
		boolean contains=false;
		while (i<9){
			if(grid[rowConstant+i].getNumber()==x){
				contains=true;
			}
			i++;
		}
		return contains;
	}
	public boolean colContains(int y,int col){
		int i=0;
		int colConstant=0;
		boolean contains=false;
		while(i<9){
			colConstant=9*i;
			if(grid[colConstant+col].getNumber()==y){
				contains=true;
				
			}
			i++;
		}
		return contains;
	}
	public boolean boxContains(int row,int col,int x){
		boolean contains=false;
		int box=0;
		int bconst=0;
		int i=0;
		int j=0;
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
			bconst=27;
			box=box-3;
		}else {
			bconst=54;
			box=box-6;
		}	
		
		while(j<3){
			i=0;
			while(i<3){
				if(grid[bconst+9*j+i+3*box].getNumber()==x){
					contains=true;
				}
				i++;
			}
			j++;
		}
		return contains;
	}
	
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
	
	public void removedSelected(){
		int i=0;
		while(i<81){
			if(grid[i].isSelected()){
				grid[i].modifySelected();
			}
			i++;
		}
	}
}
