package software;

import java.util.ArrayList;
import java.util.Random;

public class Recommend {

	txtTrans txt = new txtTrans();
	User user = new User("Somebody");
	ArrayList<CCodi> cody0609 = txt.CCodiDB;
	String[] clothe0609 = {
			"블루종", "야구점퍼", "코트", "가디건", "스웨터", "조끼", "터틀넥", "shirt", "블라우스", "체크셔츠",
			"티셔츠", "맨투맨", "나그랑", "후드티", "pants", "청바지", "스키니진", "슬랙스", "skirt", "미니스커트",
			"미디스커트", "One piece", "일반 원피스", "뷔스티에"
	};
	//CCodi 안에 String[] clotheList 
	
	public Recommend() {
		Clothe temp = Recommendation(user);
		
	}
	
	public Clothe Recommendation(User user1) {
		
		Clothe reco;
		ArrayList<Clothe> clotheArr = new ArrayList<Clothe>();

		for(int i=0; i<clothe0609.length; i++) {
			for(int j=0; j<user1.closet.size(); j++) {
				if(clothe0609[i].equals(user1.closet.get(j).getSort())) {
					continue;
				}
				else {
					clotheArr.add(new Clothe(clothe0609[i]));
					break;
				}
			}
		}
		
		Random rand = new Random();
		if(clotheArr.size()>0) {
			int random = rand.nextInt(clotheArr.size());
			reco = clotheArr.get(random);
			return reco;
		}
		else
			return new Clothe("NoName");
			
	}
	
}
