package software;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
//import autocody.db.*;
//import autocody.al.*;

public class Frame2 extends JFrame implements ActionListener {

	
	txtTrans tT = new txtTrans();
	ClosetDialog Closet_dlg;
   JButton showC1, showC2, showC3, showC4, clotheschoice;
   JLabel[] time  = new JLabel[4];
   JLabel[] temp = new JLabel[4];
   JLabel[] weather = new JLabel[4];
   ImageIcon image1, image2, image3, image4;
   ImageIcon tImage = new ImageIcon("Image/슬랙스.png");
   int height, width;
   int cColor;
   int[] tColor = new int[5];
   double temperature;
   ArrayList<Clothe> cSet;
   Toolkit tool;
   Scanner scan;
   User user=new User("Test");
   Cody cody = new Cody(user);
   ColorMatch cMatch = new ColorMatch();
   CardLayout card=new CardLayout(0,0);
   JPanel mainpanel, addpanel, deletepanel, recompanel,Bigpanel, userpanel;
   JButton addbtn,deletebtn,recombtn,savebtn,topbtn,bottombtn,outerbtn,setbtn,colorbtn,backbtn1,backbtn2,backbtn3,checkdeletebtn,previousbtn,nextbtn,color1,color2,color3,color4,color5, userbtn, closetbtn, backbtn4, modifbtn;
   Image img,clothes;//완성된 옷 조합을 어떻게 가져올지,,
   JLabel callFile,clotheslist,recommandation, user_info, user_name;
   JTextField fileName,indexNum;
   String f_name="image/",cTemp;
   @SuppressWarnings("rawtypes")
   JComboBox outerlist,bottomlist,toplist,setlist,deletelist;
   String[] outer= {"선택","무스탕","숏패딩","롱패딩","야상패딩","야구점퍼","코트","후드집업"}; 
   String[] bottom= {"선택","청바지","스키니진","슬랙스","레깅스","미니스커트","미디스커트","롱스커트"};
   String[] top= {"선택","가디건","니트 스웨터","니트 터틀넥","니트 베스트","블라우스","체크셔츠","일반 셔츠","폴로 셔츠","슬리브","맨투맨","티셔츠","나그랑","후드티"};
   String[] set= {"선택","뷔스티에 원피스","일반 원피스"};

   @SuppressWarnings({ "unchecked", "rawtypes" })
   ////추가/////
   String[] header = {"번호","옷 종류","색상코드","색"}; //테이블의 헤더
   String[][] contents; // 옷장 정보
	
	JTable table; // 옷 리스트 쭈르륵 출력할 테이블
	JScrollPane scroll; // 테이블에 붙여줄 스크롤
   //deletelist 데이터 베이스를 이용해서 이제까지 저장했던 옷 정보 가져와서 배열로 만들기,,or jcheckbox 사용
   
    Frame2(){
      this("내 옷장 속 코디네이터");
   }
   
   Frame2(String name){
      
      setTitle(name);
      tool=Toolkit.getDefaultToolkit();
      Dimension screen=tool.getScreenSize();
      height=screen.height;
      width=screen.width;
      Image img=tool.getImage("image/그림1.JPG");
      this.setIconImage(img);
      this.setSize(width/2,height/2);
      this.setLocation(width/4,height/4);
      init();
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
   }
   //새로추가한
   public void reloadcontents1() { 
		contents = new String[user.closet.size()][4];
		for(int i = 0; i < user.closet.size();i++) {
			contents[i][0] = String.valueOf(i+1);
			contents[i][1] = user.closet.get(i).getSort();
			
			contents[i][2] = "#"+String.format("%06x",user.closet.get(i).getColor());
			contents[i][3] = String.valueOf(user.closet.get(i).getColor());
		}
	}
   //새로 추가한 메소드
   public void readWeather() {
	   Scanner scan;
	      try {
	         File forload  = new File("Weather.txt");
	         scan = new Scanner(forload);
	         int i = 0;
	         while(scan.hasNextLine()) {
	            String str = scan.nextLine();
	            String temp2[] = str.split("#"); //옷정보 구성 옷 종류/색상코드
//	            System.out.println("1");
	            weather[i].setIcon(new ImageIcon(temp2[0]));
	            int j = Integer.parseInt(temp2[1])-3;
	            time[i].setText(Integer.toString(j)+"시~"+temp2[1]+"시");
	            temp[i].setText(temp2[2]);
	            temperature += Double.parseDouble(temp2[2]);
	            i++;
	         }
	         temperature /= (double)4;
	         
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
   }
   public void init() {
     

	  
	  tT.loadCloset(user);
	  
	  
	  
	  showC1=new JButton(image1);
	  showC2=new JButton(image2);
	  showC3=new JButton(image3);
	  showC4=new JButton(image4);
	      
	      userpanel=new JPanel();
	      userpanel.setLayout(null);
	      user_info=new JLabel("사용자 : ");
	      user_info.setFont(new Font("배달의민족 연성",Font.BOLD,28));
	      user_info.setSize(300, 30);
	      user_info.setLocation(200, 80);
	      user_name=new JLabel("김롸롸");
	      user_name.setFont(new Font("배달의민족 연성",Font.BOLD,28));
	      user_name.setSize(300, 30);
	      user_name.setLocation(300, 80);
	      closetbtn=new JButton("내 옷장 보기");
	      closetbtn.setSize(120, 40);
	      closetbtn.setBackground(new Color(0xfdb2a4));
	      closetbtn.setLocation(220, 140);
	      modifbtn=new JButton("사용자 등록");
	      modifbtn.setBackground(new Color(0xfdb2a4));
	      modifbtn.setSize(120, 30);
	      modifbtn.setLocation(480, 230);
	      backbtn4=new JButton("돌아가기");
	      backbtn4.setBackground(new Color(0xfdb2a4));
	      backbtn4.setSize(120, 30);
	      backbtn4.setLocation(480, 280);
	      userpanel.add(user_info);
	      userpanel.add(user_name);
	      userpanel.add(closetbtn);
	      userpanel.add(modifbtn);
	      userpanel.add(backbtn4);
	      closetbtn.addActionListener(this);
	      modifbtn.addActionListener(this);
	      backbtn4.addActionListener(this);  
//	  

	  
      mainpanel=new JPanel();
 //     mainpanel.setBackground(new Color(0xFF9999));
      mainpanel.setLayout(null);
      mainpanel.setBackground(new Color(0x89bad9));
      addbtn=new JButton("옷 추가");
      addbtn.setBackground(new Color(0xf2f2f2));
      addbtn.setSize(100, 40);
      addbtn.setLocation(60, 50);
      deletebtn=new JButton("옷 삭제");
      deletebtn.setBackground(new Color(0xf2f2f2));
      deletebtn.setSize(100, 40);
      deletebtn.setLocation(260, 50);
      recombtn=new JButton("코디 추천");
      recombtn.setBackground(new Color(0xf2f2f2));
      recombtn.setSize(100, 40);
      recombtn.setLocation(460,50);
      mainpanel.add(addbtn);
      mainpanel.add(deletebtn);
      mainpanel.add(recombtn);
      addbtn.addActionListener(this);
      deletebtn.addActionListener(this);
      recombtn.addActionListener(this);
      
      addpanel=new JPanel();
      //addpanel.setBackground(new Color(0xF5A9BC));
      addpanel.setLayout(null);
      callFile=new JLabel("파일 불러오기(확장자까지 적어주세요) -> ");
      callFile.setSize(300, 30);
      callFile.setLocation(60, 30);
      fileName=new JTextField(50);
      fileName.setSize(200, 30);
      fileName.setLocation(350, 30);
//      File imgfile = new File(f_name+fileName.getText());
//      try {
//         scan=new Scanner(imgfile);
//      } catch (FileNotFoundException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
      topbtn=new JButton("상의");
      topbtn.setSize(80, 30);
      topbtn.setLocation(30, 130);
      bottombtn=new JButton("하의");
      bottombtn.setSize(80, 30);
      bottombtn.setLocation(30, 180);
      outerbtn=new JButton("아우터");
      outerbtn.setSize(80, 30);
      outerbtn.setLocation(30, 230);
      setbtn=new JButton("세트");
      setbtn.setSize(80, 30);
      setbtn.setLocation(30, 280);
      colorbtn=new JButton("색상");
      colorbtn.setSize(80, 30);
      colorbtn.setLocation(30,80);
      backbtn1=new JButton("돌아가기");
      backbtn1.setBackground(new Color(0x00adb5));
      backbtn1.setSize(100, 30);
      backbtn1.setLocation(500, 230);
      savebtn=new JButton("저장");
      savebtn.setBackground(new Color(0x00adb5));
      savebtn.setSize(100, 30);
      savebtn.setLocation(500, 280);
      color1=new JButton();
      color1.setSize(60, 30);
      color1.setLocation(130,80);
      //색상 정보 얻는 구문
      color1.setEnabled(false);
      color2=new JButton();
      color2.setSize(60, 30);
      color2.setLocation(220,80);
      color2.setEnabled(false);
      color3=new JButton();
      color3.setSize(60, 30);
      color3.setLocation(310,80);
      color3.setEnabled(false);
      color4=new JButton();
      color4.setSize(60, 30);
      color4.setLocation(400,80);
      color4.setEnabled(false);
      color5=new JButton();
      color5.setSize(60, 30);
      color5.setLocation(490,80);
      color5.setEnabled(false);
      toplist=new JComboBox(top);
      toplist.setSize(100, 30);
      toplist.setLocation(130, 130);
      toplist.setEnabled(false);
      bottomlist=new JComboBox(bottom);
      bottomlist.setSize(100, 30);
      bottomlist.setLocation(130, 180);
      bottomlist.setEnabled(false);
      outerlist=new JComboBox(outer);
      outerlist.setSize(100, 30);
      outerlist.setLocation(130,230);
      outerlist.setEnabled(false);
      setlist=new JComboBox(set);
      setlist.setSize(100, 30);
      setlist.setLocation(130, 280);
      setlist.setEnabled(false);

      addpanel.add(callFile);
      addpanel.add(fileName);
      addpanel.add(colorbtn);
      addpanel.add(topbtn);
      addpanel.add(bottombtn);
      addpanel.add(outerbtn);
      addpanel.add(setbtn);
      addpanel.add(backbtn1);
      addpanel.add(savebtn);
      addpanel.add(toplist);
      addpanel.add(bottomlist);
      addpanel.add(outerlist);
      addpanel.add(setlist);

      addpanel.add(color1);
      addpanel.add(color2);
      addpanel.add(color3);
      addpanel.add(color4);
      addpanel.add(color5);
      colorbtn.addActionListener(this);
      topbtn.addActionListener(this);
      bottombtn.addActionListener(this);
      outerbtn.addActionListener(this);
      setbtn.addActionListener(this);
      toplist.addActionListener(this);
      bottomlist.addActionListener(this);
      setlist.addActionListener(this);
      outerlist.addActionListener(this);
      savebtn.addActionListener(this);
      backbtn1.addActionListener(this);
      color1.addActionListener(this);
      color2.addActionListener(this);
      color3.addActionListener(this);
      color4.addActionListener(this);
      color5.addActionListener(this);
      
      clotheschoice=new JButton("예시 이미지");
      clotheschoice.setSize(190, 190);
      clotheschoice.setLocation(280, 125);
      addpanel.add(clotheschoice);
      
      deletepanel=new JPanel();
      //deletepanel.setBackground(new Color(0xECCEF5));
      deletepanel.setLayout(null);
      clotheslist=new JLabel("옷 리스트 불러오기");
      clotheslist.setFont(new Font("배달의민족 연성",Font.BOLD,28));
      clotheslist.setSize(300, 30);
      clotheslist.setLocation(200, 30);
      ///////////////
      reloadcontents1();
    table = new JTable(contents, header); //이보다 앞에 contents를 초기화 해줄 필요가 있다.
	table.getColumnModel().getColumn(0).setPreferredWidth(1);
	table.getColumnModel().getColumn(3).setCellRenderer(new MyTableCellRenderer());
	scroll = new JScrollPane(table);
	scroll.setSize(300,200);
	scroll.setLocation(150,80);
// 콤보//  deletelist=new JComboBox();//리스트 불러오기
   //  deletelist.setSize(300, 30);
   //  deletelist.setLocation(150, 80);
      checkdeletebtn=new JButton("삭제");
      checkdeletebtn.setBackground(new Color(0xeef6ff));
      checkdeletebtn.setSize(100, 30);
      checkdeletebtn.setLocation(500, 280);
      backbtn2=new JButton("돌아가기");
      backbtn2.setBackground(new Color(0xeef6ff));
      backbtn2.setSize(100, 30);
      backbtn2.setLocation(500,230);
      indexNum = new JTextField(10);
      indexNum.setSize(50, 40);
      indexNum.setLocation(500, 100);
      deletepanel.add(indexNum);
      deletepanel.add(clotheslist);
  //    deletepanel.add(deletelist);
      deletepanel.add(scroll);
      deletepanel.add(backbtn2);
      deletepanel.add(checkdeletebtn);
      checkdeletebtn.addActionListener(this);
      backbtn2.addActionListener(this);
      
      recompanel=new JPanel();
      //recompanel.setBackground(new Color(0xF78181));
      recompanel.setLayout(null);
      recompanel.setBackground(new Color(0));
      recommandation=new JLabel("오늘의 추천템");
      recommandation.setFont(new Font("배달의민족 연성",Font.BOLD,28));
      recommandation.setSize(300, 30);
      recommandation.setLocation(220, 30);
//      previousbtn=new JButton("<<");
//      previousbtn.setSize(50, 50);
 //     previousbtn.setLocation(30, 150);
      nextbtn=new JButton(">>");
      nextbtn.setSize(50, 50);
      nextbtn.setBackground(new Color(0xfdb2a4));
      nextbtn.setLocation(560, 150);
      
      showC1.setSize(tImage.getIconWidth(), tImage.getIconHeight());
      showC1.setLocation(90, 80);
      
      showC2.setSize(tImage.getIconWidth(), tImage.getIconHeight());
      showC2.setLocation(202, 80);
      
      showC3.setSize(tImage.getIconWidth(), tImage.getIconHeight());
      showC3.setLocation(314, 80);
      
      showC4.setSize(tImage.getIconWidth(), tImage.getIconHeight());
      showC4.setLocation(426, 80);
      
      
      backbtn3=new JButton("돌아가기");
      backbtn3.setBackground(new Color(0xfdb2a4));
      backbtn3.setSize(100, 30);
      backbtn3.setLocation(500, 280);
      
      recompanel.add(recommandation);
 //     recompanel.add(previousbtn);
      recompanel.add(nextbtn);
      recompanel.add(backbtn3);
      recompanel.add(showC1);
      recompanel.add(showC2);
      recompanel.add(showC3);
      recompanel.add(showC4);

     

    //  show.setSize(image.getIconWidth(), image.getIconHeight());

     // showC.setVisible(true);
 //     previousbtn.addActionListener(this);
      nextbtn.addActionListener(this);
      backbtn3.addActionListener(this);
    //  recompanel.add(showC);

      
	    userbtn=new JButton("사용자 정보");
	      userbtn.setSize(120,40);
	      userbtn.setLocation(480, 280);
	      userbtn.setBackground(new Color(0xf2f2f2));
	      mainpanel.add(addbtn);
	      mainpanel.add(deletebtn);
	      mainpanel.add(recombtn);
	      mainpanel.add(userbtn);
	      addbtn.addActionListener(this);
	      deletebtn.addActionListener(this);
	      recombtn.addActionListener(this);
	      userbtn.addActionListener(this);
	     
	      

		     time[0]=new JLabel("9시 ~ 12시");
		     time[1]=new JLabel("12시 ~ 15시");
		     time[2]=new JLabel("15시 ~ 18시");
		     time[3]=new JLabel("18시 ~ 21시");
		     temp[0]=new JLabel("-1˚C");
		     temp[1]=new JLabel("-1˚C");
		     temp[2]=new JLabel("-1˚C");
		     temp[3]=new JLabel("-1˚C");
		     weather[0]=new JLabel();
		     weather[1]=new JLabel();
		     weather[2]=new JLabel();
		     weather[3]=new JLabel();
		     time[0].setSize(100, 40);
		    time[1].setSize(100, 40);
		    time[2].setSize(100, 40);
		    time[3].setSize(100, 40);
		    temp[0].setSize(100, 40);
		    temp[1].setSize(100, 40);
		    temp[2].setSize(100, 40);
		    temp[3].setSize(100, 40);
		    weather[0].setSize(100, 100);
		    weather[1].setSize(100, 100);
		    weather[2].setSize(100, 100);
		    weather[3].setSize(100, 100);
		     time[0].setLocation(70, 90);
		     time[1].setLocation(200, 90);
		     time[2].setLocation(330, 90);
		     time[3].setLocation(460,90);
		     temp[0].setLocation(90, 130);
		     temp[1].setLocation(220, 130);
		     temp[2].setLocation(350, 130);
		     temp[3].setLocation(480, 130);
		     weather[0].setLocation(55,170);
		     weather[1].setLocation(185,170);
		     weather[2].setLocation(315,170);
		     weather[3].setLocation(445,170);
		     weather[0].setIcon(new ImageIcon("image/clear.jpg"));
		     weather[1].setIcon(new ImageIcon("image/clear.jpg"));
		     weather[2].setIcon(new ImageIcon("image/clear.jpg"));
		     weather[3].setIcon(new ImageIcon("image/clear.jpg"));
		     mainpanel.add(time[0]);
		     mainpanel.add(time[1]);
		     mainpanel.add(time[2]);
		     mainpanel.add(time[3]);
		     mainpanel.add(temp[0]);
		     mainpanel.add(temp[1]);
		     mainpanel.add(temp[2]);
		     mainpanel.add(temp[3]);
		     mainpanel.add(weather[0]);
		     mainpanel.add(weather[1]);
		     mainpanel.add(weather[2]);
		     mainpanel.add(weather[3]);
		    
		     ////추가 메소드
		     readWeather();
		     
		      addpanel.setBackground(new Color(0x99CCFF));
		      deletepanel.setBackground(new Color(0xCCCCCC));
		      userpanel.setBackground(new Color(0xf2d5e3));
		      recompanel.setBackground(new Color(0xFFCCCC));
		      addbtn.setFont(new Font("배달의민족 연성",Font.BOLD,18));
		      deletebtn.setFont(new Font("배달의민족 연성",Font.BOLD,18));
		      recombtn.setFont(new Font("배달의민족 연성",Font.BOLD,18));
		      userbtn.setFont(new Font("배달의민족 연성",Font.BOLD,18));
		      backbtn1.setFont(new Font("배달의민족 연성",Font.BOLD,18));
		      backbtn2.setFont(new Font("배달의민족 연성",Font.BOLD,18));
		      backbtn3.setFont(new Font("배달의민족 연성",Font.BOLD,18));
		      backbtn4.setFont(new Font("배달의민족 연성",Font.BOLD,18));
		      savebtn.setFont(new Font("배달의민족 연성",Font.BOLD,18));
		      topbtn.setFont(new Font("배달의민족 연성",Font.BOLD,18));
		      bottombtn.setFont(new Font("배달의민족 연성",Font.BOLD,18));
		      outerbtn.setFont(new Font("배달의민족 연성",Font.BOLD,18));
		      setbtn.setFont(new Font("배달의민족 연성",Font.BOLD,18));
		      colorbtn.setFont(new Font("배달의민족 연성",Font.BOLD,18));
		      checkdeletebtn.setFont(new Font("배달의민족 연성",Font.BOLD,18));
		      closetbtn.setFont(new Font("배달의민족 연성",Font.BOLD,18));
		      modifbtn.setFont(new Font("배달의민족 연성",Font.BOLD,18));
		      callFile.setFont(new Font("배달의민족 연성",Font.BOLD,18));
		      clotheschoice.setFont(new Font("배달의민족 연성",Font.BOLD,18));
      
      Bigpanel=new JPanel(card);
      Bigpanel.setBackground(new Color(0xF79F81));
      Bigpanel.add(mainpanel,"1");
      Bigpanel.add(addpanel,"2");
      Bigpanel.add(deletepanel,"3");
      Bigpanel.add(recompanel,"4");
      Bigpanel.add(userpanel, "5");
      
      Container contain = this.getContentPane();
      contain.add(Bigpanel, BorderLayout.CENTER);
   }
   
   public void doCody() {
 	  cSet=new ArrayList<Clothe>();
 	  cSet=cody.makeCody(temperature+user.difTemp*3);
 	  
      switch(cSet.size()) {
      case 2:
    	  showC1.setVisible(false);
    	  showC2.setVisible(true);
    	  showC3.setVisible(true);
    	  showC4.setVisible(false);
    	  
     	 image2=new ImageIcon("Image/"+cSet.get(0).getSort()+".png");
     	 image3=new ImageIcon("Image/"+cSet.get(1).getSort()+".png");
     	 showC2.setBackground(new Color(cSet.get(0).getColor()));
     	 showC3.setBackground(new Color(cSet.get(1).getColor()));
     	 
     	 showC2.setIcon(image2);
     	 showC3.setIcon(image3);
     	 
     	
     	 break;
      case 3:
    	  showC1.setVisible(true);
    	  showC2.setVisible(true);
    	  showC3.setVisible(true);
    	  showC4.setVisible(false);

     	 
     	 showC1.setBackground(new Color(cSet.get(0).getColor()));
     	 showC2.setBackground(new Color(cSet.get(1).getColor()));
     	 showC3.setBackground(new Color(cSet.get(2).getColor()));
     	 
     	 image1=new ImageIcon("Image/"+cSet.get(0).getSort()+".png");
     	 image2=new ImageIcon("Image/"+cSet.get(1).getSort()+".png");
     	 image3=new ImageIcon("Image/"+cSet.get(2).getSort()+".png");
     	 
     	 showC1.setIcon(image1);
      	 showC2.setIcon(image2);
      	 showC3.setIcon(image3);
      	 
      	

     	 break;
      case 4:
     	     	
    	  showC1.setVisible(true);
    	  showC2.setVisible(true);
    	  showC3.setVisible(true);
    	  showC4.setVisible(true);
    	  
     	 showC1.setBackground(new Color(cSet.get(0).getColor()));
     	 showC2.setBackground(new Color(cSet.get(1).getColor()));
     	 showC3.setBackground(new Color(cSet.get(2).getColor()));
     	 showC4.setBackground(new Color(cSet.get(3).getColor()));
     	 
     	 image1=new ImageIcon("Image/"+cSet.get(0).getSort()+".png");
     	 image2=new ImageIcon("Image/"+cSet.get(1).getSort()+".png");
     	 image3=new ImageIcon("Image/"+cSet.get(2).getSort()+".png");
     	 image4=new ImageIcon("Image/"+cSet.get(3).getSort()+".png");
     	 
      	 showC1.setIcon(new ImageIcon("Image/"+cSet.get(0).getSort()+".png"));
      	 showC2.setIcon(image2);
      	 showC3.setIcon(image3);
      	 showC4.setIcon(image4);

      	
     	 break;
      }
   }



   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      String str=e.getActionCommand();
      if(str.equals("옷 추가")) {
         card.show(Bigpanel,"2");
      }
      else if(str.equals("옷 삭제")){
         card.show(Bigpanel, "3");
      }
      else if(str.equals("코디 추천")) {
    	  doCody();
         card.show(Bigpanel, "4");
         
      }
      else if(str.equals("색상")) {
    	  
    	  String FileName="";
    	  
    	  FileName="Image/"+fileName.getText();
          ColorExtractor CE;
          int color=0;

          try {
        	  CE = new ColorExtractor();
        	  color = CE.Extract(FileName).getRGB();
        //	  System.out.println(color);
        	  tColor=cMatch.showSimilars2(color);
          } catch (IOException e1) {
        	  // TODO Auto-generated catch block
        	  e1.printStackTrace();
        	  System.out.println("색상 추출 실패");
          }
		color=0;
		
		color1.setBackground(new Color(tColor[0]));
		color2.setBackground(new Color(tColor[1]));
		color3.setBackground(new Color(tColor[2]));
		color4.setBackground(new Color(tColor[3]));
		color5.setBackground(new Color(tColor[4]));

         color1.setEnabled(true);
         color2.setEnabled(true);
         color3.setEnabled(true);
         color4.setEnabled(true);
         color5.setEnabled(true);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==1&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="가디건";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         toplist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
//              for(int i=0; i<user.closet.size(); i++) {
//                 System.out.println(user.closet.get(i).getSort()+ user.closet.get(i).getColor());
//              }
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==2&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="스웨터";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         toplist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==3&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="터틀넥";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         toplist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
             
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==4&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="베스트";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         toplist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);

      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==5&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="블라우스";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         toplist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==6&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="체크셔츠";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         toplist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp=""; 
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==7&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="일반 셔츠";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         toplist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp=""; 
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==8&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="폴로 셔츠";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         toplist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp=""; 
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==9&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="슬리브";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         toplist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp=""; 
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==10&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="맨투맨";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         toplist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp=""; 
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==11&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="티셔츠";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         toplist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==12&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="나그랑";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         toplist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==13&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="후드티";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         toplist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==1&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="청바지";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         bottomlist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==2&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="스키니진";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         bottomlist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==3&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="슬랙스";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         bottomlist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==4&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="레깅스";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         bottomlist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==5&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="미니스커트";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         bottomlist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==6&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="미디스커트";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         bottomlist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==7&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="롱스커트";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         bottomlist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==1&&setlist.getSelectedIndex()==0) {
         cTemp="무스탕";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         outerlist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==2&&setlist.getSelectedIndex()==0) {
         cTemp="숏패딩";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         outerlist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==3&&setlist.getSelectedIndex()==0) {
         cTemp="롱패딩";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         outerlist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==4&&setlist.getSelectedIndex()==0) {
         cTemp="야상패딩";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         outerlist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==5&&setlist.getSelectedIndex()==0) {
         cTemp="야구점퍼";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         outerlist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==6&&setlist.getSelectedIndex()==0) {
         cTemp="코트";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         outerlist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==7&&setlist.getSelectedIndex()==0) {
         cTemp="후드집업";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         outerlist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==1) {
         cTemp="뷔스티에 원피스";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         setlist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==2) {
         cTemp="일반 원피스";
         JOptionPane.showMessageDialog(savebtn, "저장되었습니다.");
         setlist.setSelectedIndex(0);
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
         //데이터 베이스에 저장하는 구문(함수)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("저장")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp=null;
         JOptionPane.showMessageDialog(savebtn, "양식에 맞게 다시 선택하여 주십시오.");
         toplist.setEnabled(false);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         color1.setEnabled(false);
         color2.setEnabled(false);
         color3.setEnabled(false);
         color4.setEnabled(false);
         color5.setEnabled(false);
      }
      else if(str.equals("돌아가기")) {
         card.show(Bigpanel, "1");
      }
      else if(str.equals("삭제")) {
    	 if(!indexNum.getText().equals("")) {
    		 int a = Integer.parseInt(indexNum.getText());
       	  if(a>0&&a<=user.closet.size()) {
       		 
       		 user.closet.remove(a-1);
       		 tT.saveCloset(user);
       		 reloadcontents1();
       		 JOptionPane.showMessageDialog(checkdeletebtn, "삭제되었습니다.");
       	  }
       	  else {
       		  JOptionPane.showMessageDialog(checkdeletebtn, "올바른 인덱스를 입력하세요.");
       	  }
    	 }
    	 else
      		  JOptionPane.showMessageDialog(checkdeletebtn, "올바른 인덱스를 입력하세요.");

    	 
         //선택해서 삭제하는 구문
      }
//      else if(str.equals("<<")) {
//         //옷 이미지 바꾸기
//    	  doCody();
//      }
      else if(str.equals(">>")) {
         //옷 이미지 바꾸기
    	  doCody();
    	
      }
      else if(str.equals("상의")) {
         toplist.setEnabled(true);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         bottomlist.setSelectedIndex(0);
         outerlist.setSelectedIndex(0);
         setlist.setSelectedIndex(0);
      }
      else if(str.equals("하의")) {
         bottomlist.setEnabled(true);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         toplist.setEnabled(false);
         outerlist.setSelectedIndex(0);
         setlist.setSelectedIndex(0);
         toplist.setSelectedIndex(0);
      }
      else if(str.equals("아우터")) {
         outerlist.setEnabled(true);
         setlist.setEnabled(false);
         toplist.setEnabled(false);      
         bottomlist.setEnabled(false);
         setlist.setSelectedIndex(0);
         toplist.setSelectedIndex(0);
         bottomlist.setSelectedIndex(0);
      }
      else if(str.equals("세트")) {
         setlist.setEnabled(true);
         toplist.setEnabled(false);      
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         toplist.setSelectedIndex(0);
         bottomlist.setSelectedIndex(0);
         outerlist.setSelectedIndex(0);
      }

       else if(str.equals("사용자 정보")) {
           card.show(Bigpanel, "5");
        }
        else if(str.equals("사용자 등록")) {
           String name=JOptionPane.showInputDialog("등록할 사용자의 이름을 입력하세요.");
           if(name!=null) {
              user_name.setText(name);
           }
        }
        else if(str.equals("내 옷장 보기")){
           //다이얼로그 객체 생성
        	 if(Closet_dlg == null) {
                 Closet_dlg= new ClosetDialog(this,"내 옷장",false,user.openCloset()); 
              }else {
                 Closet_dlg.requestFocus();
              }
        }

      if(toplist.getSelectedIndex()==1&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/가디건.png"));
      }
      else if(toplist.getSelectedIndex()==2&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/스웨터.png"));
      }
      else if(toplist.getSelectedIndex()==3&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/터틀넥.png"));
      }
      else if(toplist.getSelectedIndex()==4&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/베스트.png"));
      }
      else if(toplist.getSelectedIndex()==5&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/블라우스.png"));
      }
      else if(toplist.getSelectedIndex()==6&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/체크셔츠.png"));
      }
      else if(toplist.getSelectedIndex()==7&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/일반 셔츠.png"));
      }
      else if(toplist.getSelectedIndex()==8&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/폴로 셔츠.png"));
      }
      else if(toplist.getSelectedIndex()==9&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/슬리브.png"));
      }
      else if(toplist.getSelectedIndex()==10&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/맨투맨.png"));
      }
      else if(toplist.getSelectedIndex()==11&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/티셔츠.png"));
      }
      else if(toplist.getSelectedIndex()==12&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/나그랑.png"));
      }
      else if(toplist.getSelectedIndex()==13&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/후드티.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==1&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/청바지.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==2&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/스키니진.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==3&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/슬랙스.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==4&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/레깅스.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==5&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/미니스커트.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==6&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/미디스커트.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==7&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/롱스커트.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==1&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/무스탕.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==2&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/숏패딩.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==3&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/롱패딩.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==4&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/야상패딩.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==5&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/야구점퍼.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==6&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/코트.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==7&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/후드집업.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==1) {
         clotheschoice.setIcon(new ImageIcon("image/뷔스티에 원피스.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==2) {
         clotheschoice.setIcon(new ImageIcon("image/일반 원피스.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(null);
      //   clotheschoice.setText("예시 이미지");
      }

        
      if(e.getSource()==color1) {
          cColor=tColor[0];
          clotheschoice.setBackground(new Color(tColor[0]));
          JOptionPane.showMessageDialog(color1, "색상이 선택되었습니다");
       }
       else if(e.getSource()==color2) {
           cColor=tColor[1];
           clotheschoice.setBackground(new Color(tColor[1]));
           JOptionPane.showMessageDialog(color2, "색상이 선택되었습니다");
       }
       else if(e.getSource()==color3) {
           cColor=tColor[2];
           clotheschoice.setBackground(new Color(tColor[2]));
           JOptionPane.showMessageDialog(color3, "색상이 선택되었습니다");
       }
       else if(e.getSource()==color4) {
           cColor=tColor[3];
           clotheschoice.setBackground(new Color(tColor[3]));
           JOptionPane.showMessageDialog(color4, "색상이 선택되었습니다");
       }
       else if(e.getSource()==color5) {
           cColor=tColor[4];
           clotheschoice.setBackground(new Color(tColor[4]));
           JOptionPane.showMessageDialog(color5, "색상이 선택되었습니다");
       }
   }
   
   
}