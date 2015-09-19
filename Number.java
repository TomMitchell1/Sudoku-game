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
	
	public void makeHidden(){
		visible=false;
	}
	
	public boolean isVisible(){
		return visible;
	}
	
	public boolean isSelected(){
		return selected;
	}
	public void modifyValue(int x){
		number=x;
	}
	public void modifySelected(){
		selected=!selected;
	}
	public void addExcluded(int a){
		excluded.add(a);
	}
	public void cleanExcluded(){
		excluded= new ArrayList<Integer>();
	}
	public ArrayList<Integer> getList(){
		return excluded;
	}
}
