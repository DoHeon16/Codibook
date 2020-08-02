package software;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Cody {
	
	User user;
	ColorMatch cMatch = new ColorMatch();
	ArrayList<String[]> closet0610 = new ArrayList<String[]>();
	ArrayList<String[]> closet0005 = new ArrayList<String[]>();
	ArrayList<String[]> closet1000 = new ArrayList<String[]>();
	ArrayList<String> recommend = new ArrayList<String>();
	String fName;
	Random rand = new Random();
	boolean isDone = false;
	
	public Cody(User user1) {
		this.user=user1;
		init(closet0610, "closet0609");
		init(closet0005, "closet0005");
		init(closet1000, "closet1000.txt");
	}
	public void init(ArrayList<String[]> list, String name) {
		this.fName = name;
		File file = new File(fName);
		try {
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()) {
				String str = scan.nextLine();
				String temp[] = str.split("/");
				list.add(temp);
			}scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ArrayList<Clothe> makeCody(double temp) {
		ArrayList<Clothe> temp_ = new ArrayList<Clothe>();

		if(temp<5) {
			do {
				int a=0;
				//	ArrayList<Clothe> temp_ = new ArrayList<Clothe>();
					do{
						temp_.clear();
						a = rand.nextInt(closet0005.size());
						for(int i=0; i<closet0005.get(a).length; i++) {
							//System.out.println("222");
							for(int j=0; j<user.closet.size(); j++) {
								if(user.closet.get(j).getSort().equals(closet0005.get(a)[i])) {
									int cNum=0;
									int k=j;
									while(true) {
										if(j+1<user.closet.size()) {
											if(user.closet.get(j+1).getSort().equals(user.closet.get(j).getSort())){
												cNum++;
												j++;
											}else
												break;
										}else
											break;
										
									}
									if(cNum==0) {
										temp_.add(user.closet.get(k));
									}
									else if(cNum==1) {
										int cc=rand.nextInt(30)%2;
										temp_.add(user.closet.get(k+cc));
										break;
									}
									else if(cNum>1) {
										int cc = rand.nextInt(100)%(cNum+1);
										temp_.add(user.closet.get(k+cc));
										break;
									}
									
								}
								else {
									recommend.add(user.closet.get(j).getSort());
								}
							}
						}
					}while(temp_.size()!=closet0005.get(a).length);

						switch(temp_.size()) {
						case 0:
							System.out.println("Something strange happened");
						case 2:
							if(cMatch.colorMatch(0, 0, temp_.get(0).getColor(), temp_.get(1).getColor())) {
								return temp_;
							}
							else
								break;
						case 3:
							if(cMatch.colorMatch(0, temp_.get(0).getColor(), temp_.get(1).getColor(), temp_.get(2).getColor())) {
								return temp_;
							}
							else
								break;
						case 4:
							if(cMatch.colorMatch(temp_.get(0).getColor(), temp_.get(1).getColor(), temp_.get(2).getColor(), temp_.get(3).getColor())) {
								return temp_;
							}
							else
								break;
							
						}	
			}while(true);
			
				
				
				
			}
		
		else if(temp>=5&&temp<10) {
			int a=0;
			do {
				do{
					temp_.clear();
					a=0;
					a = rand.nextInt(closet0610.size());
					for(int i=0; i<closet0610.get(a).length; i++) {
				
						
						for(int j=0; j<user.closet.size(); j++) {
							if(user.closet.get(j).getSort().equals(closet0610.get(a)[i])) {	
								int cNum=0;
								int k=j;
								while(true) {
									if(j+1<user.closet.size()) {
										if(user.closet.get(j+1).getSort().equals(user.closet.get(j).getSort())){
											cNum++;
											j++;
										}else
											break;
									}else
										break;
									
								}
								if(cNum==0) {
									temp_.add(user.closet.get(k));
								}
								else if(cNum==1) {
									int cc=rand.nextInt(30)%2;
									temp_.add(user.closet.get(k+cc));
									break;
								}
								else if(cNum>1) {
									int cc = rand.nextInt(100)%(cNum+1);
									temp_.add(user.closet.get(k+cc));
									break;
								}
							}
						}
					
					}
				
				}while(temp_.size()!=closet0610.get(a).length);

				switch(temp_.size()) {
				case 0:
					System.out.println("Something strange happened");
				case 2:
					if(cMatch.colorMatch(0, 0, temp_.get(0).getColor(), temp_.get(1).getColor())) {
						return temp_;
					}break;
				case 3:
					if(cMatch.colorMatch(0, temp_.get(0).getColor(), temp_.get(1).getColor(), temp_.get(2).getColor())) {
						return temp_;
					}break;
				case 4:
					if(cMatch.colorMatch(temp_.get(0).getColor(), temp_.get(1).getColor(), temp_.get(2).getColor(), temp_.get(3).getColor())) {
						return temp_;
					}break;
					
				}	
			}while(true);
			
		}
		else if(temp>10) {
			int a=0;
			do {
				do{
					temp_.clear();
					a=0;
					a = rand.nextInt(closet1000.size());
					for(int i=0; i<closet1000.get(a).length; i++) {
						for(int j=0; j<user.closet.size(); j++) {
							if(user.closet.get(j).getSort().equals(closet1000.get(a)[i])) {
								int cNum=0;
								int k=j;
								while(true) {
									if(j+1<user.closet.size()) {
										if(user.closet.get(j+1).getSort().equals(user.closet.get(j).getSort())){
											cNum++;
											j++;
										}else
											break;
									}else
										break;
									
								}
								if(cNum==0) {
									temp_.add(user.closet.get(k));
								}
								else if(cNum==1) {
									int cc=rand.nextInt(30)%2;
									temp_.add(user.closet.get(k+cc));
									break;
								}
								else if(cNum>1) {
									int cc = rand.nextInt(100)%(cNum+1);
									temp_.add(user.closet.get(k+cc));
									break;
								}
							}
						}
					
					}
				
				}while(temp_.size()!=closet1000.get(a).length);

				switch(temp_.size()) {
				case 0:
					System.out.println("Something strange happened");
				case 2:
					if(cMatch.colorMatch(0, 0, temp_.get(0).getColor(), temp_.get(1).getColor())) {
						return temp_;
					}break;
				case 3:
					if(cMatch.colorMatch(0, temp_.get(0).getColor(), temp_.get(1).getColor(), temp_.get(2).getColor())) {
						return temp_;
					}break;
				case 4:
					if(cMatch.colorMatch(temp_.get(0).getColor(), temp_.get(1).getColor(), temp_.get(2).getColor(), temp_.get(3).getColor())) {
						return temp_;
					}break;
					
				}				
			}while(true);
				
				
		}
		
		
		
		return temp_;
		
	}
	
	public Clothe recom() {
		int[] arr = new int[30];
		Clothe temp;
		String max;
		String[] arr1 = {"�����", "���׶�", "���뽺", "�ս�ĿƮ", "���е�", "������", "������", "�̴Ͻ�ĿƮ","�̵�ĿƮ", "����Ʈ", "�߽�Ƽ�� ���ǽ�",
				"���콺", "�����", "���е�", "������", "��Ű����", "������", "������", "�߱�����", "�߻��е�", "�Ϲ� ���ǽ�", "û����", "üũ ����",
				"��Ʈ", "��Ʋ��", "���� ����" , "�ĵ� ����", "�ĵ�Ƽ", "shirt"
		};
		int biggest=0;
		for(int j=0; j<30; j++) {
			arr[j]=0;
		}
		for(int i=0; i<recommend.size(); i++) {
			if(recommend.get(i).equals("�����")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("���׶�")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("���뽺")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("�ս�ĿƮ")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("���е�")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("������")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("������")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("�̴Ͻ�ĿƮ")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("�̵�ĿƮ")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("����Ʈ")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("�߽�Ƽ�� ���ǽ�")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("���콺")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("�����")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("���е�")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("������")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("��Ű����")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("������")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("������")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("�߱�����")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("�߻��е�")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("�Ϲ� ���ǽ�")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("û����")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("üũ����")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("��Ʈ")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("��Ʋ��")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("Ƽ����")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("���� ����")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("�ĵ�����")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("�ĵ�Ƽ")) {
				arr[0]+=1;
			}
			else if(recommend.get(i).equals("shirt")) {
				arr[0]+=1;
			}
			
		}
		int index=0;
		for(int i=0; i<30; i++) {
			if(biggest<arr[i]) {
				biggest=arr[i];
				index=i;
			}
		}
		temp=new Clothe(arr1[index], 0);
		
		return temp;
		
	}
	
	public Clothe checkColor() {
		Clothe temp = new Clothe("����", 0);
		
		return temp;
	}
}
