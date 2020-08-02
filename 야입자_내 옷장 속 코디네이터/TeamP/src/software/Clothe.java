package software;

public class Clothe implements Comparable<Clothe>{

	String sort;
	String index;
	int color;
	
	public Clothe(String sort) {
		this.sort=sort;
		index= "";//this.DBIndex = this.typeIndex + "/"+this.color.getRed()+"/"+this.color.getGreen()+"/"+this.color.getBlue()
		color=-1;
	}
	public Clothe(String sort, int color) {
		this.sort=sort;
		this.color=color;
	    this.index = sort+"/"+color;
	}
	public Clothe(String sort, String index, int color) {
		this.sort=sort;
		this.index=index;
		this.color=color;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = sort+"/"+color;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int compareTo(Clothe arg) {
		return this.getSort().compareTo(arg.getSort());
		
	}
	
}
