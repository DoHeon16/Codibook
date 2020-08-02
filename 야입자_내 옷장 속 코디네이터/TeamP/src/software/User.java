package software;

import java.util.ArrayList;
import java.util.Scanner;

public class User{

	Scanner scan = new Scanner(System.in);
	String name;
	ArrayList<Clothe> closet;
	int difTemp;
	public User() {
		closet = new ArrayList<Clothe>();
	}
	
	public User(String name) {
		closet = new ArrayList<Clothe>();
		difTemp=0;
	}
	
	public void addClothe(String sort, int color) {
		boolean isSaved = false;
		for(int i=0; i<closet.size(); i++) {
			if(closet.get(i).getSort().equals(sort)) {
				closet.add(i+1, new Clothe(sort, color));
				isSaved=true;
				break;
			}
		}
		if(!isSaved) {
			closet.add(new Clothe(sort, color));
		}

	}
	 ///JIN 추가 요청 메소드 //
	   public ArrayList<Clothe> openCloset() {
	      return closet;
	   }
	   public void setdTemp(int dif) {
		   this.difTemp=dif;
	   }


}
