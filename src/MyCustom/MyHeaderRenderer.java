/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyCustom;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author acer
 */
public class MyHeaderRenderer extends DefaultTableCellRenderer {

    public MyHeaderRenderer() {
        setOpaque(true); // Đảm bảo renderer là có khả năng vẽ nền
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Gọi phương thức của lớp cha để thực hiện các thiết lập mặc định
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Thiết lập màu nền cho header
        setBackground(Color.BLUE);
//        setForeground(Color.WHITE); // Có thể bạn muốn thiết lập màu chữ tương thích với màu nền

        // Trả về component được render
        return this;
    }
}
