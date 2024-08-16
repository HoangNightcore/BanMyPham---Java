package MyCustom;

import static Main.Main.changLNF;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

public class MyTable extends JTable {

    public MyTable() {
//        changLNF("Windows");
        //======CUSTOM TABLE=======
        this.setFocusable(false);
        this.setIntercellSpacing(new Dimension(0, 0));
        this.setRowHeight(25);
        this.setSelectionBackground(new Color(50, 154, 114));
        this.setSelectionForeground(Color.white);
        this.setFont(new Font("Arial", Font.PLAIN, 16));

        JTableHeader header = this.getTableHeader();
        // Lấy tên của Look and Feel hiện tại
        String lafName = UIManager.getLookAndFeel().getName();

        // Thiết lập màu nền cho JTableHeader dựa trên LAF
        if (lafName.equals("Nimbus")) {
            header.setBackground(new Color(135, 206, 250)); // Màu xanh dương nhạt cho Nimbus
        } else if (lafName.equals("Windows")) {
            header.setBackground(new Color(242, 153, 74)); // Màu cam nhạt cho Windows
        }
//        header.setBackground(new Color(242, 153, 74));
        header.setBackground(new Color(135, 206, 250)); // Màu xanh dương nhạt
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setOpaque(false);
        header.setForeground(Color.black);
        header.setReorderingAllowed(false);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        //======/CUSTOM TABLE/=======
    }

    public MyTable(DefaultTableModel dtm) {
        this();
        this.setModel(dtm);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

//        this.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        // Lặp qua tất cả các cột của bảng và đặt renderer căn giữa cho mỗi cột
        for (int i = 0; i < this.getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        //SORT HEADER TABLE
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dtm);
        this.setRowSorter(sorter);
    }
}
