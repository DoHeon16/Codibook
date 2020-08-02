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
   ImageIcon tImage = new ImageIcon("Image/������.png");
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
   Image img,clothes;//�ϼ��� �� ������ ��� ��������,,
   JLabel callFile,clotheslist,recommandation, user_info, user_name;
   JTextField fileName,indexNum;
   String f_name="image/",cTemp;
   @SuppressWarnings("rawtypes")
   JComboBox outerlist,bottomlist,toplist,setlist,deletelist;
   String[] outer= {"����","������","���е�","���е�","�߻��е�","�߱�����","��Ʈ","�ĵ�����"}; 
   String[] bottom= {"����","û����","��Ű����","������","���뽺","�̴Ͻ�ĿƮ","�̵�ĿƮ","�ս�ĿƮ"};
   String[] top= {"����","�����","��Ʈ ������","��Ʈ ��Ʋ��","��Ʈ ����Ʈ","���콺","üũ����","�Ϲ� ����","���� ����","������","������","Ƽ����","���׶�","�ĵ�Ƽ"};
   String[] set= {"����","�߽�Ƽ�� ���ǽ�","�Ϲ� ���ǽ�"};

   @SuppressWarnings({ "unchecked", "rawtypes" })
   ////�߰�/////
   String[] header = {"��ȣ","�� ����","�����ڵ�","��"}; //���̺��� ���
   String[][] contents; // ���� ����
	
	JTable table; // �� ����Ʈ �޸��� ����� ���̺�
	JScrollPane scroll; // ���̺� �ٿ��� ��ũ��
   //deletelist ������ ���̽��� �̿��ؼ� �������� �����ߴ� �� ���� �����ͼ� �迭�� �����,,or jcheckbox ���
   
    Frame2(){
      this("�� ���� �� �ڵ������");
   }
   
   Frame2(String name){
      
      setTitle(name);
      tool=Toolkit.getDefaultToolkit();
      Dimension screen=tool.getScreenSize();
      height=screen.height;
      width=screen.width;
      Image img=tool.getImage("image/�׸�1.JPG");
      this.setIconImage(img);
      this.setSize(width/2,height/2);
      this.setLocation(width/4,height/4);
      init();
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
   }
   //�����߰���
   public void reloadcontents1() { 
		contents = new String[user.closet.size()][4];
		for(int i = 0; i < user.closet.size();i++) {
			contents[i][0] = String.valueOf(i+1);
			contents[i][1] = user.closet.get(i).getSort();
			
			contents[i][2] = "#"+String.format("%06x",user.closet.get(i).getColor());
			contents[i][3] = String.valueOf(user.closet.get(i).getColor());
		}
	}
   //���� �߰��� �޼ҵ�
   public void readWeather() {
	   Scanner scan;
	      try {
	         File forload  = new File("Weather.txt");
	         scan = new Scanner(forload);
	         int i = 0;
	         while(scan.hasNextLine()) {
	            String str = scan.nextLine();
	            String temp2[] = str.split("#"); //������ ���� �� ����/�����ڵ�
//	            System.out.println("1");
	            weather[i].setIcon(new ImageIcon(temp2[0]));
	            int j = Integer.parseInt(temp2[1])-3;
	            time[i].setText(Integer.toString(j)+"��~"+temp2[1]+"��");
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
	      user_info=new JLabel("����� : ");
	      user_info.setFont(new Font("����ǹ��� ����",Font.BOLD,28));
	      user_info.setSize(300, 30);
	      user_info.setLocation(200, 80);
	      user_name=new JLabel("��ַ�");
	      user_name.setFont(new Font("����ǹ��� ����",Font.BOLD,28));
	      user_name.setSize(300, 30);
	      user_name.setLocation(300, 80);
	      closetbtn=new JButton("�� ���� ����");
	      closetbtn.setSize(120, 40);
	      closetbtn.setBackground(new Color(0xfdb2a4));
	      closetbtn.setLocation(220, 140);
	      modifbtn=new JButton("����� ���");
	      modifbtn.setBackground(new Color(0xfdb2a4));
	      modifbtn.setSize(120, 30);
	      modifbtn.setLocation(480, 230);
	      backbtn4=new JButton("���ư���");
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
      addbtn=new JButton("�� �߰�");
      addbtn.setBackground(new Color(0xf2f2f2));
      addbtn.setSize(100, 40);
      addbtn.setLocation(60, 50);
      deletebtn=new JButton("�� ����");
      deletebtn.setBackground(new Color(0xf2f2f2));
      deletebtn.setSize(100, 40);
      deletebtn.setLocation(260, 50);
      recombtn=new JButton("�ڵ� ��õ");
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
      callFile=new JLabel("���� �ҷ�����(Ȯ���ڱ��� �����ּ���) -> ");
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
      topbtn=new JButton("����");
      topbtn.setSize(80, 30);
      topbtn.setLocation(30, 130);
      bottombtn=new JButton("����");
      bottombtn.setSize(80, 30);
      bottombtn.setLocation(30, 180);
      outerbtn=new JButton("�ƿ���");
      outerbtn.setSize(80, 30);
      outerbtn.setLocation(30, 230);
      setbtn=new JButton("��Ʈ");
      setbtn.setSize(80, 30);
      setbtn.setLocation(30, 280);
      colorbtn=new JButton("����");
      colorbtn.setSize(80, 30);
      colorbtn.setLocation(30,80);
      backbtn1=new JButton("���ư���");
      backbtn1.setBackground(new Color(0x00adb5));
      backbtn1.setSize(100, 30);
      backbtn1.setLocation(500, 230);
      savebtn=new JButton("����");
      savebtn.setBackground(new Color(0x00adb5));
      savebtn.setSize(100, 30);
      savebtn.setLocation(500, 280);
      color1=new JButton();
      color1.setSize(60, 30);
      color1.setLocation(130,80);
      //���� ���� ��� ����
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
      
      clotheschoice=new JButton("���� �̹���");
      clotheschoice.setSize(190, 190);
      clotheschoice.setLocation(280, 125);
      addpanel.add(clotheschoice);
      
      deletepanel=new JPanel();
      //deletepanel.setBackground(new Color(0xECCEF5));
      deletepanel.setLayout(null);
      clotheslist=new JLabel("�� ����Ʈ �ҷ�����");
      clotheslist.setFont(new Font("����ǹ��� ����",Font.BOLD,28));
      clotheslist.setSize(300, 30);
      clotheslist.setLocation(200, 30);
      ///////////////
      reloadcontents1();
    table = new JTable(contents, header); //�̺��� �տ� contents�� �ʱ�ȭ ���� �ʿ䰡 �ִ�.
	table.getColumnModel().getColumn(0).setPreferredWidth(1);
	table.getColumnModel().getColumn(3).setCellRenderer(new MyTableCellRenderer());
	scroll = new JScrollPane(table);
	scroll.setSize(300,200);
	scroll.setLocation(150,80);
// �޺�//  deletelist=new JComboBox();//����Ʈ �ҷ�����
   //  deletelist.setSize(300, 30);
   //  deletelist.setLocation(150, 80);
      checkdeletebtn=new JButton("����");
      checkdeletebtn.setBackground(new Color(0xeef6ff));
      checkdeletebtn.setSize(100, 30);
      checkdeletebtn.setLocation(500, 280);
      backbtn2=new JButton("���ư���");
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
      recommandation=new JLabel("������ ��õ��");
      recommandation.setFont(new Font("����ǹ��� ����",Font.BOLD,28));
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
      
      
      backbtn3=new JButton("���ư���");
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

      
	    userbtn=new JButton("����� ����");
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
	     
	      

		     time[0]=new JLabel("9�� ~ 12��");
		     time[1]=new JLabel("12�� ~ 15��");
		     time[2]=new JLabel("15�� ~ 18��");
		     time[3]=new JLabel("18�� ~ 21��");
		     temp[0]=new JLabel("-1��C");
		     temp[1]=new JLabel("-1��C");
		     temp[2]=new JLabel("-1��C");
		     temp[3]=new JLabel("-1��C");
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
		    
		     ////�߰� �޼ҵ�
		     readWeather();
		     
		      addpanel.setBackground(new Color(0x99CCFF));
		      deletepanel.setBackground(new Color(0xCCCCCC));
		      userpanel.setBackground(new Color(0xf2d5e3));
		      recompanel.setBackground(new Color(0xFFCCCC));
		      addbtn.setFont(new Font("����ǹ��� ����",Font.BOLD,18));
		      deletebtn.setFont(new Font("����ǹ��� ����",Font.BOLD,18));
		      recombtn.setFont(new Font("����ǹ��� ����",Font.BOLD,18));
		      userbtn.setFont(new Font("����ǹ��� ����",Font.BOLD,18));
		      backbtn1.setFont(new Font("����ǹ��� ����",Font.BOLD,18));
		      backbtn2.setFont(new Font("����ǹ��� ����",Font.BOLD,18));
		      backbtn3.setFont(new Font("����ǹ��� ����",Font.BOLD,18));
		      backbtn4.setFont(new Font("����ǹ��� ����",Font.BOLD,18));
		      savebtn.setFont(new Font("����ǹ��� ����",Font.BOLD,18));
		      topbtn.setFont(new Font("����ǹ��� ����",Font.BOLD,18));
		      bottombtn.setFont(new Font("����ǹ��� ����",Font.BOLD,18));
		      outerbtn.setFont(new Font("����ǹ��� ����",Font.BOLD,18));
		      setbtn.setFont(new Font("����ǹ��� ����",Font.BOLD,18));
		      colorbtn.setFont(new Font("����ǹ��� ����",Font.BOLD,18));
		      checkdeletebtn.setFont(new Font("����ǹ��� ����",Font.BOLD,18));
		      closetbtn.setFont(new Font("����ǹ��� ����",Font.BOLD,18));
		      modifbtn.setFont(new Font("����ǹ��� ����",Font.BOLD,18));
		      callFile.setFont(new Font("����ǹ��� ����",Font.BOLD,18));
		      clotheschoice.setFont(new Font("����ǹ��� ����",Font.BOLD,18));
      
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
      if(str.equals("�� �߰�")) {
         card.show(Bigpanel,"2");
      }
      else if(str.equals("�� ����")){
         card.show(Bigpanel, "3");
      }
      else if(str.equals("�ڵ� ��õ")) {
    	  doCody();
         card.show(Bigpanel, "4");
         
      }
      else if(str.equals("����")) {
    	  
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
        	  System.out.println("���� ���� ����");
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
      else if(str.equals("����")&&toplist.getSelectedIndex()==1&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="�����";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
//              for(int i=0; i<user.closet.size(); i++) {
//                 System.out.println(user.closet.get(i).getSort()+ user.closet.get(i).getColor());
//              }
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==2&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="������";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==3&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="��Ʋ��";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
             
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==4&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="����Ʈ";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);

      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==5&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="���콺";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==6&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="üũ����";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp=""; 
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==7&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="�Ϲ� ����";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp=""; 
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==8&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="���� ����";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp=""; 
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==9&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="������";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp=""; 
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==10&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="������";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp=""; 
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==11&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="Ƽ����";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==12&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="���׶�";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==13&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="�ĵ�Ƽ";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==1&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="û����";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==2&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="��Ű����";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==3&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="������";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==4&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="���뽺";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==5&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="�̴Ͻ�ĿƮ";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==6&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="�̵�ĿƮ";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==7&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp="�ս�ĿƮ";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==1&&setlist.getSelectedIndex()==0) {
         cTemp="������";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==2&&setlist.getSelectedIndex()==0) {
         cTemp="���е�";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==3&&setlist.getSelectedIndex()==0) {
         cTemp="���е�";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==4&&setlist.getSelectedIndex()==0) {
         cTemp="�߻��е�";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==5&&setlist.getSelectedIndex()==0) {
         cTemp="�߱�����";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==6&&setlist.getSelectedIndex()==0) {
         cTemp="��Ʈ";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==7&&setlist.getSelectedIndex()==0) {
         cTemp="�ĵ�����";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==1) {
         cTemp="�߽�Ƽ�� ���ǽ�";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==2) {
         cTemp="�Ϲ� ���ǽ�";
         JOptionPane.showMessageDialog(savebtn, "����Ǿ����ϴ�.");
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
         //������ ���̽��� �����ϴ� ����(�Լ�)
          user.addClothe(cTemp,cColor);
              cTemp="";
              cColor=0;
              tT.saveCloset(user);
      }
      else if(str.equals("����")&&toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         cTemp=null;
         JOptionPane.showMessageDialog(savebtn, "��Ŀ� �°� �ٽ� �����Ͽ� �ֽʽÿ�.");
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
      else if(str.equals("���ư���")) {
         card.show(Bigpanel, "1");
      }
      else if(str.equals("����")) {
    	 if(!indexNum.getText().equals("")) {
    		 int a = Integer.parseInt(indexNum.getText());
       	  if(a>0&&a<=user.closet.size()) {
       		 
       		 user.closet.remove(a-1);
       		 tT.saveCloset(user);
       		 reloadcontents1();
       		 JOptionPane.showMessageDialog(checkdeletebtn, "�����Ǿ����ϴ�.");
       	  }
       	  else {
       		  JOptionPane.showMessageDialog(checkdeletebtn, "�ùٸ� �ε����� �Է��ϼ���.");
       	  }
    	 }
    	 else
      		  JOptionPane.showMessageDialog(checkdeletebtn, "�ùٸ� �ε����� �Է��ϼ���.");

    	 
         //�����ؼ� �����ϴ� ����
      }
//      else if(str.equals("<<")) {
//         //�� �̹��� �ٲٱ�
//    	  doCody();
//      }
      else if(str.equals(">>")) {
         //�� �̹��� �ٲٱ�
    	  doCody();
    	
      }
      else if(str.equals("����")) {
         toplist.setEnabled(true);
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         bottomlist.setSelectedIndex(0);
         outerlist.setSelectedIndex(0);
         setlist.setSelectedIndex(0);
      }
      else if(str.equals("����")) {
         bottomlist.setEnabled(true);
         outerlist.setEnabled(false);
         setlist.setEnabled(false);
         toplist.setEnabled(false);
         outerlist.setSelectedIndex(0);
         setlist.setSelectedIndex(0);
         toplist.setSelectedIndex(0);
      }
      else if(str.equals("�ƿ���")) {
         outerlist.setEnabled(true);
         setlist.setEnabled(false);
         toplist.setEnabled(false);      
         bottomlist.setEnabled(false);
         setlist.setSelectedIndex(0);
         toplist.setSelectedIndex(0);
         bottomlist.setSelectedIndex(0);
      }
      else if(str.equals("��Ʈ")) {
         setlist.setEnabled(true);
         toplist.setEnabled(false);      
         bottomlist.setEnabled(false);
         outerlist.setEnabled(false);
         toplist.setSelectedIndex(0);
         bottomlist.setSelectedIndex(0);
         outerlist.setSelectedIndex(0);
      }

       else if(str.equals("����� ����")) {
           card.show(Bigpanel, "5");
        }
        else if(str.equals("����� ���")) {
           String name=JOptionPane.showInputDialog("����� ������� �̸��� �Է��ϼ���.");
           if(name!=null) {
              user_name.setText(name);
           }
        }
        else if(str.equals("�� ���� ����")){
           //���̾�α� ��ü ����
        	 if(Closet_dlg == null) {
                 Closet_dlg= new ClosetDialog(this,"�� ����",false,user.openCloset()); 
              }else {
                 Closet_dlg.requestFocus();
              }
        }

      if(toplist.getSelectedIndex()==1&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/�����.png"));
      }
      else if(toplist.getSelectedIndex()==2&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/������.png"));
      }
      else if(toplist.getSelectedIndex()==3&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/��Ʋ��.png"));
      }
      else if(toplist.getSelectedIndex()==4&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/����Ʈ.png"));
      }
      else if(toplist.getSelectedIndex()==5&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/���콺.png"));
      }
      else if(toplist.getSelectedIndex()==6&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/üũ����.png"));
      }
      else if(toplist.getSelectedIndex()==7&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/�Ϲ� ����.png"));
      }
      else if(toplist.getSelectedIndex()==8&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/���� ����.png"));
      }
      else if(toplist.getSelectedIndex()==9&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/������.png"));
      }
      else if(toplist.getSelectedIndex()==10&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/������.png"));
      }
      else if(toplist.getSelectedIndex()==11&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/Ƽ����.png"));
      }
      else if(toplist.getSelectedIndex()==12&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/���׶�.png"));
      }
      else if(toplist.getSelectedIndex()==13&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/�ĵ�Ƽ.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==1&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/û����.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==2&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/��Ű����.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==3&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/������.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==4&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/���뽺.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==5&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/�̴Ͻ�ĿƮ.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==6&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/�̵�ĿƮ.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==7&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/�ս�ĿƮ.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==1&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/������.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==2&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/���е�.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==3&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/���е�.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==4&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/�߻��е�.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==5&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/�߱�����.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==6&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/��Ʈ.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==7&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(new ImageIcon("image/�ĵ�����.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==1) {
         clotheschoice.setIcon(new ImageIcon("image/�߽�Ƽ�� ���ǽ�.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==2) {
         clotheschoice.setIcon(new ImageIcon("image/�Ϲ� ���ǽ�.png"));
      }
      else if(toplist.getSelectedIndex()==0&&bottomlist.getSelectedIndex()==0&&outerlist.getSelectedIndex()==0&&setlist.getSelectedIndex()==0) {
         clotheschoice.setIcon(null);
      //   clotheschoice.setText("���� �̹���");
      }

        
      if(e.getSource()==color1) {
          cColor=tColor[0];
          clotheschoice.setBackground(new Color(tColor[0]));
          JOptionPane.showMessageDialog(color1, "������ ���õǾ����ϴ�");
       }
       else if(e.getSource()==color2) {
           cColor=tColor[1];
           clotheschoice.setBackground(new Color(tColor[1]));
           JOptionPane.showMessageDialog(color2, "������ ���õǾ����ϴ�");
       }
       else if(e.getSource()==color3) {
           cColor=tColor[2];
           clotheschoice.setBackground(new Color(tColor[2]));
           JOptionPane.showMessageDialog(color3, "������ ���õǾ����ϴ�");
       }
       else if(e.getSource()==color4) {
           cColor=tColor[3];
           clotheschoice.setBackground(new Color(tColor[3]));
           JOptionPane.showMessageDialog(color4, "������ ���õǾ����ϴ�");
       }
       else if(e.getSource()==color5) {
           cColor=tColor[4];
           clotheschoice.setBackground(new Color(tColor[4]));
           JOptionPane.showMessageDialog(color5, "������ ���õǾ����ϴ�");
       }
   }
   
   
}