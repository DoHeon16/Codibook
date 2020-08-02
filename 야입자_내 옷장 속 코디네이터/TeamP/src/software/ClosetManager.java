package software;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClosetManager {

	ArrayList<Cody> closet0005;
	ArrayList<Cody> closet0610;
	ArrayList<Cody> closet1115;
	ArrayList<Cody> closet1620;
	
	public ClosetManager() {
		
	}
	
	public void addCody() {
		
	}
	public String changeTemp(int temp) {
		if(temp>=0&&temp<5)
			return "0005";
		else if(temp>=5&&temp<10)
			return "0609";
		else
			return "not yet";
	}
	public void init(int temp) {
		String fName = "closet"+changeTemp(temp)+".txt";
		try {
			File file = new File(fName);
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()) {
				String str = scan.nextLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
