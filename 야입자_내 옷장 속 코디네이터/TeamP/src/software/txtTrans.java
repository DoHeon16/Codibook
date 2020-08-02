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
//1. CCodiDB ���ο� �� ���� ���� ����
//2. ���� ���� txt ���Ϸ� ���� �� �ҷ�����
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
   
   // readDB(); txt ���Ϸ� ����Ǿ��ִ� �� ���� �����͸� ���α׷��� �̿��� �� �ֵ��� 
   // txtTrans class ���� CCodiDB ��� ����Ʈ�� �����ϰ��ִ�.
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
      ////����� �� ������ ������ ����
      try {
         File forwrite = new File("Closetinfo.txt"); //�ؽ�Ʈ���ϸ��� ������ ���Ƿ� ������ �� �ְ� ���߿� ����
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
      //����� ���� �ҷ����� 
      Scanner scan;
      try {
         File forload  = new File("Closetinfo.txt");
         scan = new Scanner(forload);
         while(scan.hasNextLine()) {
            String str = scan.nextLine();
            String temp[] = str.split("/"); //������ ���� �� ����/�����ڵ�

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