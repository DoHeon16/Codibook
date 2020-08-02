package software;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
//1. CCodiDB 내부에 옷 조합 정보 저장
//2. 옷장 정보 txt 파일로 저장 및 불러오기
//3.
public class txtTrans {
   

   String inputFileName;
   String ouputFileName;
   ArrayList<CCodi> CCodiDB;
   public txtTrans() {
	  this.inputFileName = "closet0609.txt";
      CCodiDB = new ArrayList<CCodi>();
     // readDB(inputFileName);
      // TODO Auto-generated constructor stub
   }
   
   // readDB(); txt 파일로 저장되어있는 옷 조합 데이터를 프로그램이 이용할 수 있도록 
   // txtTrans class 내부 CCodiDB 어레이 리스트에 저장하고있다.
   public void readDB(String inputFile) {
	      Scanner scan;
	      try {
	         File file = new File(inputFile);
	         scan = new Scanner(file);
	         while(scan.hasNextLine()) {
	            String str = scan.nextLine();
	            String temp[] = str.split("/");
	            int index = temp.length;
	            switch(index) {
	            case 2:
	               CCodi ccodi = new CCodi(temp[0],temp[1]);
	               this.CCodiDB.add(ccodi);
	               break;
	            case 3:
	               CCodi ccodi2 = new CCodi(temp[0],temp[1],temp[2]); 
	               this.CCodiDB.add(ccodi2);
	               break;
	            case 4:
	               CCodi ccodi3 = new CCodi(temp[0],temp[1],temp[2],temp[3]); 
	               this.CCodiDB.add(ccodi3);
	               break;
	            }
	            
	         }
	      } catch (FileNotFoundException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	   //   scan.close();
	         
	   }
   
   public void saveCloset(User user) {
      ////옷장안 옷 정보를 저장할 예정
      try {
         File forwrite = new File("Closetinfo.txt"); //텍스트파일명은 유저가 임의로 저장할 수 있게 나중에 변경
         BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(forwrite));
         bufferedWriter = new BufferedWriter(new FileWriter(forwrite));
         //bufferedWriter.close();
         if(forwrite.isFile() && forwrite.canWrite()) {
            for(int i = 0; i< user.openCloset().size();i++) {
               bufferedWriter.write(user.openCloset().get(i).getIndex());
               bufferedWriter.newLine();
            }
            bufferedWriter.close();
         }
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   public void loadCloset(User user) {
      //옷장안 정보 불러오기 
      Scanner scan;
      try {
         File forload  = new File("Closetinfo.txt");
         scan = new Scanner(forload);
         while(scan.hasNextLine()) {
            String str = scan.nextLine();
            String temp[] = str.split("/"); //옷정보 구성 옷 종류/색상코드

            user.addClothe(temp[0],Integer.parseInt(temp[1]));
         }
         
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public void setInput(String s) {
      this.inputFileName = s;
   }
   public void setOutput(String s) {
      this.ouputFileName =s;
   }
   

}