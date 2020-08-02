package software;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Test extends JFrame{
	
	public Test(int a, int b, int c, int d, int e){
		
		ArrayList<color> colorSet = new ArrayList<color>();
		
		color temp1 = new color();
	
		temp1.temp[0]=0xCCFF00;
			for(int i=0; i<3; i++) {
				temp1.temp[i+1]=temp1.temp[i]+0x33;	
			
		}
		colorSet.add(temp1);
		
		
	
		
		setTitle("Team Project"); // 프레임의 타이틀 달기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		Container contentPane = getContentPane(); // 컨텐트팬 알아내기
		contentPane.setBackground(Color.white);
		setSize(800, 300); // 프레임 크기 300x150 설정

		
		contentPane.setLayout(new FlowLayout() ); // 컨텐트팬에 FlowLayout
		// 배치관리자 달기
		
				
		Button[] btn = new Button[5];
		for(int i=0; i<5; i++) {
			btn[i]=new Button("Color"+(i+1));
			btn[i].setPreferredSize(new Dimension(100, 100));
			//btn[i].setBackground(new Color(colorSet.get(0).temp[i]));
			contentPane.add(btn[i]);
		}
		
		btn[0].setBackground(new Color(a));
		btn[1].setBackground(new Color(b));
		btn[2].setBackground(new Color(c));
		btn[3].setBackground(new Color(d));
		btn[4].setBackground(new Color(e));

		
		
		setVisible(true); // 화면에 프레임 출력
		
		
	}
	
}
