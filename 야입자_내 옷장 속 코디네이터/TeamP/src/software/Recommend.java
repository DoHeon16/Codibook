package software;

import java.util.ArrayList;
import java.util.Random;

public class Recommend {

	txtTrans txt = new txtTrans();
	User user = new User("Somebody");
	ArrayList<CCodi> cody0609 = txt.CCodiDB;
	String[] clothe0609 = {
			"�����", "�߱�����", "��Ʈ", "�����", "������", "����", "��Ʋ��", "shirt", "���콺", "üũ����",
			"Ƽ����", "������", "���׶�", "�ĵ�Ƽ", "pants", "û����", "��Ű����", "������", "skirt", "�̴Ͻ�ĿƮ",
			"�̵�ĿƮ", "One piece", "�Ϲ� ���ǽ�", "�߽�Ƽ��"
	};
	//CCodi �ȿ� String[] clotheList 
	
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
