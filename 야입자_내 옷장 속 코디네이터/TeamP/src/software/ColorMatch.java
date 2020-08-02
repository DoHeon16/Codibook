package software;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class ColorMatch {

	Random rand = new Random();
	
	public ColorMatch() {
		
	}
	//각각 R,G,B값을 가져오는 함수
	public int getR(int color) {
		return color/0xF0000;
	}
	public int getG(int color) {
		return color/0xF00%0xF00;
	}
	public int getB(int color) {
		return color%0xF00;
	}
	
	//안전 색상으로 변환
	public int toSafeColor(int color) {
		int r, g, b;
		r=getR(color);
		g=getG(color);
		b=getB(color);
		
		if(0<=r&&r<0x22)
			r=0;
		else if(0x22<=r&&r<=0x55)
			r=0x33;
		else if(0x55<=r&&r<=0x88)
			r=0x66;
		else if(0x88<=r&&r<=0xBB)
			r=0x99;
		else if(0xBB<=r&&r<=0xEE)
			r=0xCC;
		else
			r=0xFF;
		
		if(0<=g&&g<0x22)
			g=0;
		else if(0x22<=g&&g<=0x55)
			g=0x33;
		else if(0x55<=g&&g<=0x88)
			g=0x66;
		else if(0x88<=g&&g<=0xBB)
			g=0x99;
		else if(0xBB<=g&&g<=0xEE)
			g=0xCC;
		else
			g=0xFF;
		
		if(0<=b&&b<0x22)
			b=0;
		else if(0x22<=b&&b<=0x55)
			b=0x33;
		else if(0x55<=b&&b<=0x88)
			b=0x66;
		else if(0x88<=b&&b<=0xBB)
			b=0x99;
		else if(0xBB<=b&&b<=0xEE)
			b=0xCC;
		else
			b=0xFF;
		
		return r*0xF0000+g*0xF00+b;
	}
	
	//같은 색상인지 확인하는 함수
	public boolean isSameColor(int color1, int color2) {
		if(getR(color1)==getR(color2)&&getG(color1)==getG(color2))
			return true;
		else if(getR(color1)==getR(color2)&&getB(color1)==getB(color2))
			return true;
		else if(getG(color1)==getG(color2)&&getB(color1)==getB(color2))
			return true;
		else
			return false;
	}
	
	public int BofColor(int color) {
		return (getR(color)+getG(color)+getB(color))/3;
	}
	
	//같은 명도인지 확인하는 함수
	public boolean isSameB(int color1, int color2) {
		
		if(BofColor(color1)==BofColor(color2))
			return true;
		else
			return false;
	}
	
	//같은 채도인지 확인하는 함수
	public boolean isSameS(int color1, int color2) {

		Color color = new Color(color1);
		Color color_ = new Color(color2);
		float[] temp = new float[3];
		float[] temp_ = new float[3];
		
		Color.RGBtoHSB(getR(color1), getG(color1), getB(color1), temp);
		Color.RGBtoHSB(color_.getRed(), color_.getGreen(), color_.getBlue(), temp_);
		if(temp[1]==temp_[1])
			return true;
		else
			return false;
		
		
	}
	//무채색인지 판별
	public boolean isAchro(int color) {
		Color color1 = new Color(color);
		float[] temp = new float[3];
		color1.getColorComponents(temp);
		for(int i=0; i<2; i++) {
			if(temp[i]==temp[i+1]) {
				continue;
			}
			else {
				return false;
			}
		}
		return true;
	}
	
	//명도 채도 색상 조절하는 함수들
	public int downB(int color) {
		int temp = color-0x333333;
		if((getR(color)>=getG(color))&&(getR(color)>=getB(color))) {
			if(getR(temp)==getR(color))
				return temp;
			else
				return color;
		}
		else if((getG(color)>=getR(color))&&(getG(color)>=getB(color))) {
			if(getG(temp)==getG(color))
				return temp;
			else
				return color;
		}
		else if((getB(color)>=getG(color))&&(getB(color)>=getR(color))) {
			if(getB(color)==getB(temp))
				return temp;
			else
				return color;
		}
		else
			return color;
	
		
	}
	
	public int upB(int color) {
		int temp = color+0x333333;
		if((getR(color)>=getG(color))&&(getR(color)>=getB(color))) {
			if(getR(temp)==getR(color))
				return temp;
			else
				return color;
		}
		else if((getG(color)>=getR(color))&&(getG(color)>=getB(color))) {
			if(getG(temp)==getG(color))
				return temp;
			else
				return color;
		}
		else if((getB(color)>=getG(color))&&(getB(color)>=getR(color))) {
			if(getB(color)==getB(temp))
				return temp;
			else
				return color;
		}
		else
			return color;
		
	
	}
	
	public int upS(int color) {
		if((getR(color)>=getG(color))&&(getR(color)>=getB(color))) {
			return color-0x003333;
		}
		else if((getG(color)>=getR(color))&&(getG(color)>=getB(color))) {
			return color-0x330033;
		}
		else if((getB(color)>=getG(color))&&(getB(color)>=getR(color))) {
			return color-0x333300;
		}
		else
			return color;
	}
	
	public int downS(int color) {
		if((getR(color)>=getG(color))&&(getR(color)>=getB(color))) {
			return color+0x003333;
		}
		else if((getG(color)>=getR(color))&&(getG(color)>=getB(color))) {
			return color+0x330033;
		}
		else if((getB(color)>=getG(color))&&(getB(color)>=getR(color))) {
			return color+0x333300;
		}
		else
			return color;
	}
	
	public int upH(int color) {
		if((getR(color)>=getG(color))&&(getR(color)>=getB(color))) {
			return color+0x330000;
		}
		else if((getG(color)>=getR(color))&&(getG(color)>=getB(color))) {
			return color+0x003300;
		}
		else if((getB(color)>=getG(color))&&(getB(color)>=getR(color))) {
			return color+0x000033;
		}
		else
			return color;
	}
	
	public int downH(int color) {
		if((getR(color)>=getG(color))&&(getR(color)>=getB(color))) {
			return color-0x330000;
		}
		else if((getG(color)>=getR(color))&&(getG(color)>=getB(color))) {
			return color-0x003300;
		}
		else if((getB(color)>=getG(color))&&(getB(color)>=getR(color))) {
			return color-0x000033;
		}
		else
			return color;
	}
	
	
	
	public boolean tonONton(int a, int b, int c, int d) {
	//같은 색상에 명도와 채도만 다르게 배색
		int tmp[] = new int[4];
		tmp[0]=a;
		tmp[1]=b;
		tmp[2]=c;
		tmp[3]=d;
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		for(int i=0; i<4; i++) {
			if(tmp[i]!=0xFFFFFF&&tmp[i]!=0x000000) {
				temp.add(tmp[i]);
			}
		}
		
		for(int j=0; j<temp.size()-1; j++) {
			if(isSameColor(temp.get(j), temp.get(j+1))) {
				continue;
			}
			else
				return false;
		}
		for(int k=0; k<temp.size()-1; k++) {
			if(!isSameB(temp.get(k), temp.get(k+1)))
				return true;
			else
				break;
		}
		for(int z=0; z<temp.size()-1; z++) {
			if(!isSameS(temp.get(z), temp.get(z+1)))
				return true;
			else
				break;
		}
		
		return false;
	}
	
	public boolean tonINton(int a, int b, int c, int d) {
	//같은 명도와 채도에 색상만 달리해서 배색
		
		ArrayList<Integer> temp = new ArrayList<Integer>();
		if(a==0x000000||a==0xFFFFFF)
			temp.add(a);
		if(b==0x000000||b==0xFFFFFF)
			temp.add(b);
		if(c==0x000000||c==0xFFFFFF)
			temp.add(c);
		if(d==0x000000||d==0xFFFFFF)
			temp.add(d);
		
		for(int i=0; i<temp.size()-1; i++) {
			if(isSameS(temp.get(i), temp.get(i+1))==true)
				continue;
			else {
				return false;
			}		
		}
		return true;
	}
	
	//아니 진짜 이건 어떻게 구현해야될지 감이 안옴.....
/*	public boolean Gradation(int a, int b, int c, int d) {
		//동색 계열 색상을 명도와 채도에 따라 점차적으로 배치 
		
		return false;
	}*/
	
	public boolean Seperation(int a, int b, int c, int d) {
		//강렬한 유채색 배색 사이 무채색이나 중간색 하나
		ArrayList<Integer> temp = new ArrayList<Integer>();//유채색 집합
		ArrayList<Integer> temp2 = new ArrayList<Integer>();//무채색 집합
		if(!isAchro(a))
			temp.add(a);
		else
			temp2.add(a);

		if(!isAchro(b))
			temp.add(b);
		else
			temp2.add(b);
		
		if(!isAchro(c))
			temp.add(c);
		else
			temp2.add(c);
		
		if(!isAchro(d))
			temp.add(d);
		else
			temp2.add(d);
		
		if(temp2.size()==1) {
				//temp2.add(0xFFFFFF);
				if(tonONton(temp.get(0), temp.get(1), temp.get(2), temp2.get(0)))
					return true;
				if(tonINton(temp2.get(0), temp.get(0), temp.get(1), temp.get(2)))
					return true;
				else
					return false;//여기 뒤에 더 추가해야됨!!!!!!!
		}
		else
			return false;
		
	}
	
	public boolean Accent(int a, int b, int c, int d) {
		//전체적으로 단조로운 코디네이션 위(무채색들 사이)에 악센트 칼라 하나
		ArrayList<Integer> temp = new ArrayList<Integer>();
		ArrayList<Integer> temp2 = new ArrayList<Integer>();
		if(isAchro(a))
			temp.add(a);
		else
			temp2.add(a);

		if(isAchro(b))
			temp.add(b);
		else
			temp2.add(b);
		
		if(isAchro(c))
			temp.add(c);
		else
			temp2.add(c);
		
		if(isAchro(d))
			temp.add(d);
		else
			temp2.add(d);
		
		if(temp.size()<3) {
			if(temp2.size()==1)
				return true;
			else {
				for(int i=0; i<temp2.size()-1; i++) {
					if(isSameColor(temp2.get(i), temp2.get(i+1)))
						continue;
					else {
						return false;
					}
				}
				return true;
			}
			
		}
		else 
			return true;//죄다 무채색일 경우 일단 모르겠고 true라고 하자
		
		
	}
	
	public int[] showSimilars1(int color) {
		int temp[] =new int[5];
	
	//	while(upB(color)<0xFFFFFF&&upB(color)>0x000000) {
		//	color=upB(color);
			//System.out.println(color);
	//	}
		for(int i=0; i<5; i++) {
			temp[i]=color;
			System.out.println(color);
			color=upB(color);
		}
		return temp;
		
	}
	
	public int[] showSimilars2(int color) {
	//	color=toSafeColor(color);
		int temp[] = new int[5];
		if(getR(color)==0||getR(color)==0xFF||getB(color)==0||getB(color)==0xFF) {
			if(getR(color)==0&&getB(color)==0) {
				temp[0]=color;
				temp[4]=color;
				temp[1]=color+0x33;
				temp[2]=color+0x330000;
				temp[3]=color+0x330033;
			}
			else if(getR(color)==0&&getB(color)==0xFF) {
				temp[0]=color;
				temp[4]=color;
				temp[1]=color-0x33;
				temp[2]=color+0x330000;
				temp[3]=color+0x330000-0x33;
			}
			else if(getR(color)==0xFF&&getB(color)==0) {
				temp[0]=color;
				temp[4]=color;
				temp[1]=color-0x330000;
				temp[2]=color+0x33;
				temp[3]=color-0x330000+0x33;
			}
			else if(getR(color)==0xFF&&getB(color)==0xFF) {
				temp[0]=color;
				temp[4]=color;
				temp[1]=color-0x330000;
				temp[2]=color-0x33;
				temp[3]=color-330033;
			}
			else if(getB(color)==0xFF&&getR(color)!=0&&getR(color)!=0xFF) {
				temp[0]=color;
				temp[4]=color;
				temp[1]=color-0x330000;
				temp[2]=color-0x33;
				temp[3]=color+0x330000;
			}
			else if(getB(color)==0&&getR(color)!=0&&getR(color)!=0xFF) {
				temp[0]=color;
				temp[4]=color;
				temp[1]=color-0x330000;
				temp[2]=color+0x33;
				temp[3]=color+0x330000;
			}
			else if(getR(color)==0xFF&&getB(color)!=0&&getB(color)!=0xFF) {
				temp[0]=color;
				temp[4]=color;
				temp[1]=color-0x33;
				temp[2]=color-0x330000;
				temp[3]=color+0x33;
			}
			else if(getR(color)==0&&getB(color)!=0&&getB(color)!=0xFF) {
				temp[0]=color;
				temp[4]=color;
				temp[1]=color-0x33;
				temp[2]=color+0x330000;
				temp[3]=color+0x33;
			}
		}
		else {
			temp[3]=color-0x330000;
			temp[1]=color+0x33;
			temp[2]=color;
			temp[4]=color-0x33;
			temp[0]=color+0x330000;
		}
		
		return temp;
	}
	
	public boolean colorMatch(int a, int b, int c, int d) {
		boolean isDone = false;
		int random = rand.nextInt(4);
		
			switch(random) {
			case 0:
				if(tonONton(a, b, c, d)) {
//					System.out.println("tonOnton");
					return true;
				}
				else {
//					System.out.println("실패");
				}
				break;
			case 1:
				if(Accent(a, b, c, d)) {
//					System.out.println("Accent");
//					isDone=true;
					return true;
				}
				else {
//					System.out.println("실패");
				}
				break;
			case 2:
				if(tonINton(a, b, c, d)) {
//					System.out.println("tonINton");
//					isDone=true;
					return true;
				}
				else {
//					System.out.println("실패");
				}
				break;
			case 3:
				if(Seperation(a, b, c, d)) {
//					System.out.println("Seperation");
//					isDone=true;
					return true;
				}
				else {
//					System.out.println("실패");
				}
				break;
			}
			
		

		return false;
		
	}
	
}
