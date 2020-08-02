package software;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
public class MyTableCellRenderer extends DefaultTableCellRenderer {
     private static final long serialVersionUID = 1L;
     @Override

      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

           // Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (!isSelected) {

                   if (column == 3) {
                      Color c = new Color(Integer.parseInt((String) value));
                          l.setForeground(c);
                          l.setBackground(c);
                          //cell.setBackground(c);
                   

                   } else {
                         l.setBackground(Color.white);
                         // cell.setBackground(Color.white);

                   }

            }
//         return this;
            return l;

      }
}