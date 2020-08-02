package software;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


public class ClosetDialog extends JDialog {
   ArrayList<Clothe> closet;
   ///JTable 구성 요소
   String[] header = {"번호","옷 종류","색상코드","색"}; //테이블의 헤더
   String[][] contents; // 옷장 정보
   
   JTable table; // 옷 리스트 쭈르륵 출력할 테이블
   JScrollPane scroll; // 테이블에 붙여줄 스크롤
   
   //TableCellRenderer renderer = new MyTableCellRenderer();

   
   
   Frame2 parent; //######부모가 될 프레임의 이름
   public ClosetDialog(Frame parent, String title, boolean arg2, ArrayList<Clothe> closet) {
      super(parent, title, arg2);
      // TODO Auto-generated constructor stub
      this.parent = (Frame2) parent; //######부모가 될 프레임의 이름
      this.closet = closet;
      this.setSize(300,300);
      this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
         //table.setDefaultRenderer(Object.class, new MyTableCellRenderer());
         reloadcontents();
         
         init();
         this.setVisible(true);
      
   }
   
   //Jtable에 들어갈 내용을 미리 초기화 시켜준다.
   public void reloadcontents() { 
      contents = new String[closet.size()][4];
      for(int i = 0; i < closet.size();i++) {
         contents[i][0] = String.valueOf(i+1);
         contents[i][1] = closet.get(i).getSort();
         
         contents[i][2] = "#"+String.format("%06x",closet.get(i).getColor());
         contents[i][3] = String.valueOf(closet.get(i).getColor());
      }
   }
   
   private void init() {

      table = new JTable(contents, header); //이보다 앞에 contents를 초기화 해줄 필요가 있다.

      table.getColumnModel().getColumn(0).setPreferredWidth(1);
      table.getColumnModel().getColumn(3).setCellRenderer(new MyTableCellRenderer());
      scroll = new JScrollPane(table);
      
      this.add(scroll,BorderLayout.CENTER);
      
      this.addWindowListener(new WindowAdapter() {

         @Override //모달리스 ( 포커스가 유지 안되는 ) 창의 경우 이 구문을 통해 다이얼로그를 껏다 켯다할 수 있게 됨.
         public void windowClosing(WindowEvent arg0) {
            // TODO Auto-generated method stub
            super.windowClosing(arg0);
            if(parent!=null) parent.Closet_dlg = null; 
         } //창 종료 이벤트를 받아들이는 함수
         
         
         
      });
      
   }

}