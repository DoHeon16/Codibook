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
   ///JTable ���� ���
   String[] header = {"��ȣ","�� ����","�����ڵ�","��"}; //���̺��� ���
   String[][] contents; // ���� ����
   
   JTable table; // �� ����Ʈ �޸��� ����� ���̺�
   JScrollPane scroll; // ���̺� �ٿ��� ��ũ��
   
   //TableCellRenderer renderer = new MyTableCellRenderer();

   
   
   Frame2 parent; //######�θ� �� �������� �̸�
   public ClosetDialog(Frame parent, String title, boolean arg2, ArrayList<Clothe> closet) {
      super(parent, title, arg2);
      // TODO Auto-generated constructor stub
      this.parent = (Frame2) parent; //######�θ� �� �������� �̸�
      this.closet = closet;
      this.setSize(300,300);
      this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
         //table.setDefaultRenderer(Object.class, new MyTableCellRenderer());
         reloadcontents();
         
         init();
         this.setVisible(true);
      
   }
   
   //Jtable�� �� ������ �̸� �ʱ�ȭ �����ش�.
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

      table = new JTable(contents, header); //�̺��� �տ� contents�� �ʱ�ȭ ���� �ʿ䰡 �ִ�.

      table.getColumnModel().getColumn(0).setPreferredWidth(1);
      table.getColumnModel().getColumn(3).setCellRenderer(new MyTableCellRenderer());
      scroll = new JScrollPane(table);
      
      this.add(scroll,BorderLayout.CENTER);
      
      this.addWindowListener(new WindowAdapter() {

         @Override //��޸��� ( ��Ŀ���� ���� �ȵǴ� ) â�� ��� �� ������ ���� ���̾�α׸� ���� �ִ��� �� �ְ� ��.
         public void windowClosing(WindowEvent arg0) {
            // TODO Auto-generated method stub
            super.windowClosing(arg0);
            if(parent!=null) parent.Closet_dlg = null; 
         } //â ���� �̺�Ʈ�� �޾Ƶ��̴� �Լ�
         
         
         
      });
      
   }

}