package MyCustom;

import java.awt.AWTEventMulticaster;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class PanelGradient extends JComponent {

    private GradientType gradientType = GradientType.HORIZONTAL;
    private Color colorGradient = new Color(255, 255, 255);
    private int radius;
    public boolean isSelected;

    public PanelGradient() {
        setOpaque(true);
        setBackground(new Color(255, 255, 255));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isOpaque()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Insets inset = getInsets();
            int width = getWidth() - inset.left - inset.right;
            int height = getHeight() - inset.top - inset.bottom;
            int x1, x2, y1, y2;
            if (gradientType == GradientType.HORIZONTAL || gradientType == null) {
                x1 = inset.left;
                y1 = inset.top;
                x2 = inset.left + width;
                y2 = inset.top;
            } else if (gradientType == GradientType.VERTICAL) {
                x1 = inset.left;
                y1 = inset.top;
                x2 = inset.left;
                y2 = inset.top + height;
            } else if (gradientType == GradientType.DIAGONAL_1) {
                x1 = inset.left;
                y1 = inset.top + height;
                x2 = inset.left + width;
                y2 = inset.top;
            } else {
                x1 = inset.left;
                y1 = inset.top;
                x2 = inset.left + width;
                y2 = inset.top + height;
            }
            Point p1 = new Point(x1, y1);
            Point p2 = new Point(x2, y2);
            g2.setPaint(new GradientPaint(p1, getBackground(), p2, colorGradient));
            g2.fill(new RoundRectangle2D.Double(inset.left, inset.top, width, height, radius, radius));
            g2.dispose();
        }
        super.paintComponent(g);
    }

    public GradientType getGradientType() {
        return gradientType;
    }

    public void setGradientType(GradientType gradientType) {
        this.gradientType = gradientType;
        repaint();
    }

    public Color getColorGradient() {
        return colorGradient;
    }

    public void setColorGradient(Color colorGradient) {
        this.colorGradient = colorGradient;
        repaint();
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        repaint();
    }

    public static enum GradientType {
        VERTICAL, HORIZONTAL, DIAGONAL_1, DIAGONAL_2
    }

    public void button(String content) {
        this.setRadius(20);
        
        JLabel pnlContent = new JLabel(content);
        pnlContent.setPreferredSize(new Dimension(155, 30));
        pnlContent.setBorder(new EmptyBorder(0, 0, 0, 0));
        pnlContent.setFont(new Font("sansserif", 0, 15));
        pnlContent.setForeground(Color.BLACK);
        pnlContent.setHorizontalAlignment(JLabel.CENTER);
        pnlContent.setVerticalAlignment(JLabel.CENTER);

        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(pnlContent);
    }

    public void itemMenuTaskbar(String linkIcon, String content) {
        this.setRadius(15);
        this.setBackground(new Color(0, 204, 255));
        this.setColorGradient(new Color(51, 153, 255));

        JLabel lblIcon = new JLabel();
        lblIcon.setBorder(new EmptyBorder(0, 40, 0, 0));
//        lblIcon.setPreferredSize(new Dimension(150, 50));

        lblIcon.setIcon(new ImageIcon("image/SideBar/" + linkIcon));
//        this.add(lblIcon);

        JLabel pnlContent = new JLabel(content);
//        pnlContent.setPreferredSize(new Dimension(155, 30));
        pnlContent.setBorder(new EmptyBorder(0, 20, 0, 0));
        pnlContent.setFont(new Font("sansserif", 0, 16));
        pnlContent.setForeground(Color.BLACK);
//        this.add(pnlContent);

        // Thêm các thành phần vào panel chính
        this.setLayout(new BorderLayout());
        this.add(lblIcon, BorderLayout.WEST);
        this.add(pnlContent, BorderLayout.CENTER);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isSelected) {
                    setBackground(new Color(102, 255, 102));
                    setColorGradient(new Color(0, 204, 153));
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!isSelected) {
                    setBackground(new Color(0, 204, 255));
                    setColorGradient(new Color(51, 153, 255));
                }
            }
        });
    }
}
